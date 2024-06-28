package com.matematik.antremani.view.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.matematik.antremani.Constant;
import com.matematik.antremani.databinding.FragmentGameClassicBinding;
import com.matematik.antremani.impl.ModeImpl;
import com.matematik.antremani.model.GameSettings;
import com.matematik.antremani.util.GameUtil;
import com.matematik.antremani.util.WorkUtil;
import com.matematik.antremani.viewmodel.SharedViewModel;
import com.matematik.antremani.viewmodel.view.game.GameClassicViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class GameClassicFragment extends Fragment implements ModeImpl {

    private SharedViewModel sharedViewModel;

    private GameClassicViewModel viewModel;

    private FragmentGameClassicBinding binding;

    @Inject
    public GameUtil gameUtil;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameClassicBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(v, savedInstanceState);

        init(); // Initialize işlemleri yapıldıu

        observers(); // Dinleyiciler açıldı

    }

    @Override
    public void init() {

        /**
         *
         * NavHostFragment.findNavController(this).navigate(R.id.gameSymbolFragment);
         *
         * Ekranın tepsindeki geri tuşu işlevini Util ler ile hepsinde kullanılabilir bir şey yap
         * Örn userutil içerisinde exitdialog olsun onu çağırsın evete basınca navigate etsin ana ekrana vs
         *
         * İpucu ile yanlış 2 buton gizlendiğinde app reset atarsa?
         * Süre bittikten sonra süre bonusu almışsa ve app reset atarsa?
         *
         * Soru devam ediyorken daha kolaya basarsa bug ?
         *
         * Oyun devam ederken bonus kullanılırsa?
         *
         *
         * Doğru cevap verince sağ üsttekinin değişmesi
         * Oyun devam ederken arka plana atılırsa (gecikme süresi kadar progressbar süresi azaltılmalı)
         * Örneğin 10 saniye var arka plana atıldı arka planda 5 saniye geçti döndüğünde 5 saniyesi kalmalı veya bitmişse bitmiş vs
         *
         * Eğer dialog varken app reset atarsa (arka plan işlev sınırı)
         * dialog true olacak app tekrar açıldığınd dialog gösterilecek ama aynı zamanda start game de çalışacak ?
         */

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class); // SharedViewModel tanımlandı

        viewModel = new ViewModelProvider(this).get(GameClassicViewModel.class); // ViewModel tanımlandı

        binding.setViewModel(viewModel); // DataBinding ile XML'e viewmodel gönderildi

        binding.setBonusListener(viewModel.getBonusListener()); // Bonus listener gönderildi ViewModel üzerinden onClick tetikleniyor

        binding.setTool(viewModel.getTool()); // DataBinding ile XML'e Tool Model[LiveData] gönderiliyor (include'a göndermek için)

        binding.setLifecycleOwner(this); // LiveData değişiklikleri için yaşam döngüsüne bağlandı

//        gameClassicViewModel.checkGameSettingsArgs(getArguments());
        Bundle args = new Bundle();
        args.putSerializable("gameSettings", new GameSettings(
                Constant.TYPE_LEVEL,
                Constant.MODE_CLASSIC,
                Constant.TIME_FIXED,
                5,
                20,
                true,
                true,
                true,
                true
        ));
        viewModel.checkArgsAndStart(args); // Test için bu oluşturuldu normal halinde bundle'a kadar sil ve üstündeki getArguments'ı paramatre olarak ver

    }


    @Override
    public void observers() {

//        sharedViewModel.adShowed.observe(getViewLifecycleOwner(), adShowed -> {
//
//            if (adShowed) {
//
//                sharedViewModel.defaultAdShowed(); // Diğer fragmentlarda'da observe edileceği için default değere [False] ayarlanıyor
//
//                // Reklam gösterimi bitti şimdi hangi bonus seçildi ise onun işlemine gidilecek viewmodel'da
//
//            }
//
//        });

        /**
         * Eğer yüklenmiş bir interstitial ad var ise reklam gösterimi tetikleniyor
         * Eğer reklam & reklam yüklemesi yok ise, reklam isteği tetikleniyor
         */
//        viewModel.useBonus.observe(getViewLifecycleOwner(), bonus -> { if (bonus != null) sharedViewModel.showOrDownloadAd(getClass().getSimpleName());});

        viewModel.userDialog.observe(getViewLifecycleOwner(), dialog -> {

            // Eğer bonus ile reklam izlendi ise o zaman wronganswer default olarak false'a dönecek

            if (dialog != null) {

                gameUtil.showDialog(
                        getChildFragmentManager(),
                        dialog.matches(Constant.DIALOG_WRONGANSWER),
                        viewModel.getGameSettingsValue().isTypeLevel(),
                        viewModel.getGameSettingsValue().getGameMode(),
                        viewModel.getGameStatusValue().getCorrectAnswerCount(),
                        viewModel::restartProcess,
                        viewModel::dismissProcess,
                        viewModel::exitProcess
                );

            }

        });

//        viewModel.isGoToMainScreen.observe(getViewLifecycleOwner(), change -> { if (change) workUtil.goToMainScreen(binding.getRoot()); });

    }

}