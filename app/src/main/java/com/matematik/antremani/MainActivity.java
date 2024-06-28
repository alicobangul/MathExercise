package com.matematik.antremani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.os.Bundle;
import com.matematik.antremani.databinding.ActivityMainBinding;
import com.matematik.antremani.util.AppUtil;
import com.matematik.antremani.viewmodel.SharedViewModel;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private SharedViewModel sharedViewModel;

    @Override
    public void onBackPressed() {

//        // Custom ayar ekranı açıkken geriye basılırsa ana ekrana git
//        if (Objects.requireNonNull(navController.getCurrentDestination()).getId() == R.id.customFragment) navController.navigate(R.id.mainFragment);
//
//        else {
//
//            /**
//             * Custom harici bir ekran açık : MainFrag / OptionFrag / TrueFalseFrag
//             * Herhangi bir dialog kutusu açık değil
//             */
//
//            if(!sharedViewModel.getDialogVisibility()) {
//
//                dialog = new Dialog(this); // Dialog kutusu aç
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Dialog arka planı ayarla
//                dialog.setCancelable(false); // Ekranda boş bir yere tıklandığında kapatılamaz
//
////                DialogExitBinding dialogBinding = DialogExitBinding.inflate(getLayoutInflater()); // Çıkış dialog kutusu şişirildi
////
////                dialog.setContentView(dialogBinding.getRoot()); // Dialog kutusu görünümü eklendi
////
////                dialogBinding.btnExitOk.setOnClickListener(v -> {
////
////                    dialog.dismiss(); // Dialog kapat
////
////                    // Bu kısım düzenlenecek
//////                    // Açık olan fragment'a göre geçiş yapılıyor
//////                    switch(navController.getCurrentDestination().getId()) {
//////                        case R.id.optionFragment:
//////                            navController.navigate(R.id.action_optionFragment_to_mainFragment); // OptionFrag -> MainFrag geçiş
//////                            break;
//////                        case R.id.trueFalseFragment:
//////                            navController.navigate(R.id.action_trueFalseFragment_to_mainFragment); // TrueFalseFrag -> MainFrag geçiş
//////                            break;
//////                        default:
//////                            android.os.Process.killProcess(android.os.Process.myPid()); // Açık uygulamalardan pid ile uygulamayı sonlandır [MainFrag açık]
//////                            break;
//////                    }
////
////                });
////
////                dialogBinding.btnExitCancel.setOnClickListener(v -> { if(dialog.isShowing()) dialog.dismiss(); }); // Bütün dialog kutularında iptal tuşu dialog kapatıyor
////
////                dialog.show(); // Dialog göster
//
//            }
//
//        }

    }

    public void unutmadan() {

        /**
         * Bu yeni mod eklendiğinde ne yapılması gerektiği açıklaması
         * mods.xml e fixed tip süresi de eklenmeli
         * Utils içindeki getModeFragmentId eklenmeli
         * GameUtil deki getDefaultModeTime fonksiyonuna oyun modunun fixed süresini eklemeyi unutma
         * Fragmentı oluşturulmalı
         * Customgame ayar kısmına modu eklemeyi unutma
         * Main ekrandaki level seçme dialog kutusuna ve level modu başlatma dialog kutusuna eklemeyi unutma
         */

        /**
         * <h2>MOD Gösterimi</h2>
         * <ul>
         * <li>Yeni mod ekleneceği zaman: </li>
         * <ul>
         *     <li>Sözkonusu view'ın tag özelliğine "MODE_MODISMI" eklenmeli</li>
         *     <li>Yeni mod tag ismi + shared kodu ile {@link com.matematikantrenmani.antremani.R.array.arrayMode} içerisine eklenmeli</li>
         *     <li>{@link com.matematikantrenmani.antremani.R.layout#fragment_custom CustomFragment},
         *     {@link com.matematikantrenmani.antremani.R.layout#dialog_mode_level LevelDialog},
         *     {@link com.matematikantrenmani.antremani.R.layout#dialog_mode_selected ModDialog} içerisine mod eklenmeli
         *     </li>
         * </ul>
         * </ul>
         * @modeCode Mod leveli SharedPrefences'ta bu tanımlama ile kayıtlı
         */

        /**
         * Appteki tüm işlemleri Log kullan
         */

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init(); // Atamalar yapılıyor

        listener(); // Dinleyiciler açılıyor

        utilInit(); // Uygulama güncellemesi kontrol ediliyor

    }

    private void init() {

        // ViewModel bağlandı
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

    }

    private void listener() {

        sharedViewModel.fragmentChangeListener(((NavHostFragment) Objects.requireNonNull(getSupportFragmentManager().findFragmentById(R.id.fragmentContainer))).getNavController());

    }

    private void utilInit() {

        AppUtil appUtil = new AppUtil(this);

        appUtil.initAds();

        appUtil.checkInAppUpdate();

    }

}