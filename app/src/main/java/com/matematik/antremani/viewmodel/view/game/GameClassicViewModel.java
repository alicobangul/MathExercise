package com.matematik.antremani.viewmodel.view.game;

import android.os.Bundle;
import android.os.CountDownTimer;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.matematik.antremani.Constant;
import com.matematik.antremani.impl.BonusImpl;
import com.matematik.antremani.impl.OptionImpl;
import com.matematik.antremani.impl.ViewModelImpl;
import com.matematik.antremani.model.GameSettings;
import com.matematik.antremani.model.GameStatus;
import com.matematik.antremani.model.view.game.GameClassicScreen;
import com.matematik.antremani.model.view.ToolScreen;
import com.matematik.antremani.repository.MathRepositoryImpl;
import com.matematik.antremani.tool.game.CheckAnswer;
import com.matematik.antremani.tool.game.CreateQuestion;
import com.matematik.antremani.util.GameUtil;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class GameClassicViewModel extends ViewModel implements ViewModelImpl, BonusImpl, OptionImpl {

    private final MathRepositoryImpl mathRepository;

    private final CheckAnswer checkAnswer;

    private final SavedStateHandle state;

    private GameUtil gameUtil;

    private CountDownTimer timer = null;

    @Inject
    public GameClassicViewModel(MathRepositoryImpl mathRepository, CheckAnswer checkAnswer, GameUtil gameUtil, SavedStateHandle state) {
        this.mathRepository = mathRepository;
        this.checkAnswer = checkAnswer;
        this.gameUtil = gameUtil;
        this.state = state;
    }

    public MutableLiveData<Boolean> isGoToMainScreen = new MutableLiveData<>(false);
    public MutableLiveData<String> useBonus = new MutableLiveData<>(null);
    public MutableLiveData<String> userDialog = new MutableLiveData<>(null);

    @NonNull
    private MutableLiveData<GameSettings> getGameSettings() { return state.getLiveData("_gameSettings", new GameSettings()); }
    public GameSettings getGameSettingsValue() { return getGameSettings().getValue(); }
    public void setGameSettings(GameSettings gameSettings) { getGameSettings().setValue(gameSettings); }

    public MutableLiveData<GameClassicScreen> getScreen() { return state.getLiveData("_screen", new GameClassicScreen()); }
    public GameClassicScreen getScreenValue() { return getScreen().getValue(); }
    public void setScreen (GameClassicScreen screen) { getScreen().setValue(screen); }
    public void updateScreen() { setScreen(getScreenValue()); }

    public MutableLiveData<ToolScreen> getTool() { return state.getLiveData("_tool", new ToolScreen(getGameSettingsValue().getTime())); }
    public ToolScreen getToolValue() { return getTool().getValue(); }
    public void setTool (ToolScreen tool) { getTool().setValue(tool); }
    public void updateTool() { setTool(getToolValue()); }

    @NonNull
    private MutableLiveData<CreateQuestion> getCreateQuestion() { return state.getLiveData("_question", new CreateQuestion()); }
    public CreateQuestion getCreateQuestionValue() { return getCreateQuestion().getValue(); }
    public void setCreateQuestion(CreateQuestion createQuestion) { getCreateQuestion().setValue(createQuestion); }

    @NonNull
    private MutableLiveData<GameStatus> getGameStatus() { return state.getLiveData("_gameStatus", new GameStatus()); }
    public GameStatus getGameStatusValue() { return getGameStatus().getValue(); }
    public void updateCorrectCount() {
        getGameStatusValue().updateCorrectAnswerCount();
        getToolValue().setLevelOrCorrectText(String.valueOf(getGameStatusValue().getCorrectAnswerCount()));
        updateTool();
    }




    private void correctAnswer() {

        // her doğru cevapta okey ama oyun devam eden bir oyun ise ?
        // Bu durumda isgameactive ve boolean dialogshow kısımlarını ayrı ayrı olarak ele almalı
        // Süre bitme durumunda bonusların olayları?
        // Sonra bonuslar
        // sonra app reset atma vs senaryolarındaki aksiyonlar
        // viewmodel içerisindeki oncleared ayarla

        getGameStatusValue().defaultGameStatus(); // GameStatus varsayılan ayarlara getirildi

        // Sayaç durduruluyor (tekrar çalıştırmak için)
        if (timer != null) timer.cancel();


        // Level modu ise level artırıldı
        if (getGameSettingsValue().isTypeLevel()) mathRepository.upgradeLevel(getGameSettingsValue().getGameMode());

        else updateCorrectCount(); // Custom mod ise doğru cevap sayısı artırıldı

        // Eğer zaman modu bonus ise, her doğru cevapta şuanki sürenin üstüne oyun moduna göre süre ekle
        if (getGameSettingsValue().getTimeMode().matches(Constant.TIME_BONUS)) {

            // Progressbar max ayarı güncellendi
            getToolValue().setTime(getGameSettingsValue().getTime());

            // Progressbar progress değeri max olarak ayarlandı
            getToolValue().setCurrentTime(getGameSettingsValue().getTime());

            updateTool(); // Tool ui güncellendi

        }

        startGame(); // Oyun başlatıldı

    }

    private void wrongAnswer() {

        if (timer != null) timer.cancel(); // Sayaç durduruldu

        getGameStatusValue().setIsWrongAnswerSelected(true); // Yanlış cevap bilgisi modele işlendi

        userDialog.setValue(Constant.DIALOG_WRONGANSWER); // Yanlış cevap dialog tetiklendi

    }

    public void timeEndDialog() { userDialog.setValue(Constant.DIALOG_TIMEEND); }

    public void restartGame() {

        // Progressbar max ayarı güncellendi [Oyunun ilk ayarları getirildi]
        getToolValue().setTime(getGameSettingsValue().getTime());

        // Progressbar progress değeri max olarak ayarlandı [Oyunun ilk ayarları getirildi]
        getToolValue().setCurrentTime(getGameSettingsValue().getTime());

        // Tool UI güncellendi
        updateTool();

        // Tekrar başlayacağı için oyun süresi bitmedi olarak ayarlandı
        getGameStatusValue().setIsTimeEnd(false);

        // Yanlış cevap ayarı default'a ayarlandı
        getGameStatusValue().setIsWrongAnswerSelected(false);

        // Oyun başladı
        startGame();

    }



    public void startGame() {

        uiDifficultySettings(); // GameTool içerisindeki difficulty ayarla

        createQuestion(); // Soru oluştur

        startTimer(); // Sayacı başlat

    }

    public void uiDifficultySettings() {

        String count;


        if (getGameSettingsValue().isTypeLevel()) count = mathRepository.getModeLevel(getGameSettingsValue().getGameMode());

        else count = String.valueOf(getGameStatusValue().getCorrectAnswerCount());

        getToolValue().setLevelOrCorrectText(count + " / " + gameUtil.learnDifficultyLevel(count));

        updateTool();

    }

    public void createQuestion() {

        getCreateQuestionValue().createBasicQuestion(mathRepository.getModeLevel(getGameSettingsValue().getGameMode()));

        setScreen(new GameClassicScreen(
                getCreateQuestionValue().getQuestionText(),
                getCreateQuestionValue().getOptionFirst(),
                getCreateQuestionValue().getOptionSecond(),
                getCreateQuestionValue().getOptionThird(),
                getCreateQuestionValue().getOptionFourth()
        ));

    }

    private void startTimer() {

        if (!getGameSettingsValue().getTimeMode().matches(Constant.TIME_NOTIME)) {

            // Eğer sabit mod ise gamesettings'teki time'ı al, eğer sayaç veya bonus modu ise tool içerisindeki currenttime'ı al
            int time = (getGameSettingsValue().getTimeMode().matches(Constant.TIME_FIXED)) ? getGameSettingsValue().getTime() : getToolValue().getCurrentTime();

            timer = new CountDownTimer(time * 1000L,1000) {

                @Override
                public void onTick(long time) {
                    getToolValue().setCurrentTime((int) Math.round((double) time / 1000));
                    updateTool();
                }

                @Override
                public void onFinish() {
                    getToolValue().setCurrentTime(0);
                    updateTool();
                    getGameStatusValue().setIsTimeEnd(true);
                }

            };

            timer.start();

        }

        getGameStatusValue().setIsGameActive(true); // Oyun aktif

    }


    @Override
    public void checkArgsAndStart(Bundle argument) {

        GameSettings gameSettings = (argument != null) ? (GameSettings) argument.get("gameSettings") : null;

        if (gameSettings == null) goToMainScreen();

        else {

            setGameSettings(gameSettings);

            setCreateQuestion(new CreateQuestion(gameSettings));

            startGame();

        }

    }

    @Override
    public BonusImpl getBonusListener() { return this; }

    @Override
    public void goToMainScreen() { isGoToMainScreen.setValue(true); }

    @Override
    public void restartProcess() { restartGame(); }

    @Override
    public void dismissProcess() { if (userDialog.getValue() != null) userDialog.setValue(null); }

    @Override
    public void exitProcess() { goToMainScreen(); }


    @Override
    public void optionControl(String input) {

        if (getGameStatusValue().getIsTimeEnd()) timeEndDialog();

        else if (getGameStatusValue().getIsWrongAnswerSelected()) wrongAnswer();

        else {

            if (checkAnswer.checkBasicQuestion(getCreateQuestionValue(),input)) correctAnswer();

            else wrongAnswer();

        }

    }



    @Override
    public void bonusListener(String bonusTag) { useBonus.setValue(bonusTag); }

    @Override
    public void bonusEasierListener() {

        //        if (!getGameStatusValue().getIsWrongAnswerSelected()) startGame();

    }

    @Override
    public void bonusClueListener() {

//        if (!getGameStatusValue().getIsWrongAnswerSelected()) startGame();

    }

    @Override
    public void bonusAddTimeListener() {

//        if (!getGameStatusValue().getIsWrongAnswerSelected()) startGame();

    }

    @Override
    public void bonusNoTimeListener() {
//        if (!getGameStatusValue().getIsWrongAnswerSelected()) startGame();

    }



    @Override
    protected void onCleared() {
        super.onCleared();
        // Viewmodel kaldırılıyorken
        if (timer != null) timer.cancel();
    }
}
