package com.matematik.antremani.viewmodel.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.matematik.antremani.Constant;
import com.matematik.antremani.databinding.SnackbarSettingsQuestionBinding;
import com.matematik.antremani.impl.Game;
import com.matematik.antremani.R;
import com.matematik.antremani.model.view.CustomScreen;
import com.matematik.antremani.model.GameSettings;
import com.matematik.antremani.tool.SingleLiveEvent;
import com.matematik.antremani.util.Util;
import com.matematik.antremani.util.WorkUtil;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class CustomViewModel extends ViewModel {

    private Game.Shared implShared;

    private Boolean isSaveSettings = false;

    private final SavedStateHandle state;

    private final Util util;

    // In-App Update işleminin kontrol edildiği SingleLiveEvent
    public final SingleLiveEvent<Boolean> saveSettingsQuestion = new SingleLiveEvent<>();

    @Inject
    public CustomViewModel(SavedStateHandle savedStateHandle, Util util) {
        this.state = savedStateHandle;
        this.util = util;
    }

    public void setImplShared(Game.Shared implShared) { this.implShared = implShared; }

    public MutableLiveData<CustomScreen> getCustomSettings() { return state.getLiveData("_customSettings", new CustomScreen()); }
    private @NonNull CustomScreen getCustomSettingsValue() { return Objects.requireNonNull(getCustomSettings().getValue()); }
    private void updateSettings() { getCustomSettings().setValue(getCustomSettingsValue()); }

    public void gameModeChangedListener(View view, @NonNull Boolean checked) {

        if (checked) {

            getCustomSettingsValue().setGameMode(view.getTag().toString());

            updateSettings();

        }

    }

    public void timeModeChangedListener(View view, @NonNull Boolean checked) {

        if (checked) {

            getCustomSettingsValue().setTimeMode(view.getTag().toString());

            updateSettings();

        }

    }

    public void timeChangedListener(View view, Integer progress, Boolean fromUser) {

        // Eğer zaman ayarlama etkin ise ayarlanabilir aksi halde değer değişmeyecek

        if(getCustomSettingsValue().getIsTimeEnabled()) getCustomSettingsValue().setTime(progress);

        updateSettings();

    }

    public void numberRangeTextListener(@NonNull Editable editable) {

        String rangeText = editable.toString().replace(" ", "");

        if (rangeText.isEmpty()) getCustomSettingsValue().defaultNumberRange(); // Herhangi bir sayı yazmadı default olarak 20 ayarlanıyor

        else {

            try {

                if (getCustomSettingsValue().getGameMode().matches(Constant.MODE_PASSWORD)) {

                    if (Integer.parseInt(rangeText) < 200) getCustomSettingsValue().defaultPasswordModeNumberRange();

                    else getCustomSettingsValue().setNumberRange(rangeText);

                }
                else {

                    if (Integer.parseInt(rangeText) < 20) getCustomSettingsValue().defaultNumberRange();

                    else getCustomSettingsValue().setNumberRange(rangeText);

                }

            }
            catch (NumberFormatException exception) {

                // Gelen veri sayısal değil bu yüzden range default değere ayarlanıyor

                if (getCustomSettingsValue().getGameMode().matches(Constant.MODE_PASSWORD)) getCustomSettingsValue().defaultPasswordModeNumberRange();

                else getCustomSettingsValue().defaultNumberRange();

                updateSettings();

            }

        }

    }

    public void operatorCheckedListener(@NonNull View view, Boolean isChecked) {

        CustomScreen screen = getCustomSettingsValue();

        switch (view.getTag().toString()) {

            case Constant.OPERATOR_ADDITION -> screen.setAddition(isChecked);

            case Constant.OPERATOR_SUBTRACTION -> screen.setSubtraction(isChecked);

            case Constant.OPERATOR_MULTIPLY -> screen.setMultiply(isChecked);

            case Constant.OPERATOR_DIVISION -> screen.setDivision(isChecked);

        }

        // Eğer tüm işlemlerin tik işareti kaldırıldıysa toplama işlemi aktif oluyor

        if (!screen.getAddition() && !screen.getSubtraction() && !screen.getMultiply() && !screen.getDivision()) getCustomSettingsValue().setAddition(true);

    }

    public void startCustomGame(View view) { saveSettingsQuestion(view); }

    public void saveSettingsQuestion(View view) {

//        Snackbar questionSnackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG);
//
//        SnackbarSettingsQuestionBinding saveSnackbarBinding = SnackbarSettingsQuestionBinding.inflate(util.getActivityWithView(view).getLayoutInflater());
//        Snackbar.SnackbarLayout customSnackbarLayout = ((Snackbar.SnackbarLayout) questionSnackbar.getView().getRootView());
//        customSnackbarLayout.setPadding(0,0,0,0);
//        customSnackbarLayout.addView(saveSnackbarBinding.getRoot());
//        questionSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
//
//        saveSnackbarBinding.txtUserSnackbarMsg.setText(util.getActivityWithView(view).getString(R.string.saveSettings));
//
//        saveSnackbarBinding.txtNo.setOnClickListener(v -> questionSnackbar.dismiss());
//
//        saveSnackbarBinding.txtYes.setOnClickListener(v -> {
//
//            isSaveSettings = true;
//
//            questionSnackbar.dismiss();
//
//        });
//
//        questionSnackbar.addCallback(new Snackbar.Callback() {
//
//            @Override
//            public void onDismissed(Snackbar transientBottomBar, int event) {
//                super.onDismissed(transientBottomBar, event);
//
//                CustomScreen customScreen = getCustomSettingsValue();
//
//                implShared.getSharedViewModel().setCustomScreen((isSaveSettings) ? customScreen : null);
//
//                GameSettings gameSettings = new GameSettings(
//                        Constant.TYPE_CUSTOM,
//                        customScreen.getGameMode(),
//                        customScreen.getTimeMode(),
//                        customScreen.getTime(),
//                        Integer.parseInt(customScreen.getNumberRange()),
//                        customScreen.getAddition(),
//                        customScreen.getSubtraction(),
//                        customScreen.getMultiply(),
//                        customScreen.getDivision()
//                );
//
//                Bundle arg = new Bundle();
//                arg.putSerializable("gameSettings", gameSettings);
//
//                workUtil.changeScreen(view, util.getModeFragmentId(customScreen.getGameMode()), arg);
//
//            }
//        });
//
//        questionSnackbar.show();

    }

    public void restoreSettingsQuestion(View view) {

//        Snackbar questionSnackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
//        SnackbarSettingsQuestionBinding saveSnackbarBinding = SnackbarSettingsQuestionBinding.inflate(util.getActivityWithView(view).getLayoutInflater());
//        Snackbar.SnackbarLayout customSnackbarLayout = ((Snackbar.SnackbarLayout) questionSnackbar.getView().getRootView());
//        customSnackbarLayout.setPadding(0,0,0,0);
//        customSnackbarLayout.addView(saveSnackbarBinding.getRoot());
//        questionSnackbar.getView().setBackgroundColor(Color.TRANSPARENT);
//        saveSnackbarBinding.txtUserSnackbarMsg.setText(util.getActivityWithView(view).getString(R.string.restoreSettings));
//
//        saveSnackbarBinding.txtNo.setOnClickListener(v -> {
//
//            implShared.getSharedViewModel().setCustomScreen(null);
//
//            questionSnackbar.dismiss();
//
//        });
//
//        saveSnackbarBinding.txtYes.setOnClickListener(v -> {
//
//            getCustomSettings().setValue(implShared.getSharedViewModel().getCustomScreen().getValue());
//
//            questionSnackbar.dismiss();
//
//        });
//
//        questionSnackbar.show();

    }

}
