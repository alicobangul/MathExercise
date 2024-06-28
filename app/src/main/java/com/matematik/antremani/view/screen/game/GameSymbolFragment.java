package com.matematik.antremani.view.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentGameSymbolBinding;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameSymbolFragment extends Fragment {

    private FragmentGameSymbolBinding binding;
    private HashMap<String, Boolean> hashSymbolDesignViews;
    private List<Integer> arrayNumberText;
    private List<Integer> arrayOperatorText;
    private List<Integer> arrayEqualsText;
    private HashMap<Integer, SoftReference<EditText>> hashAllViews;
    private final MutableLiveData<Boolean> liveCreateViews = new MutableLiveData<>(false);

    private Runnable run;
    private Handler hand;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameSymbolBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.includeGameTool.btnBonusEasier.setOnClickListener(v -> { run.run(); });

        /**
         * Belki gelen soruya göre oluşturulan viewların clickable focusable gibi özellikleri false yapılabilir
         * Böylece klavyeden next next basarak hızlıca ilerler kutulara
         */

        /**
         * Şuanda soru oluşturma hazırlanacak ve oluşturulan sorularda bir yerde tutulacak edittextler hazırlanıyorken [runnable içi] o oluşturulan soru havuzunu kullanacak
         * Yeni soru yerine, daha kolay şeklinde bir bonus yap
         * Şuanda rxjava ile hızlıca dizileri vs hesaplama yapılacak
         * Textlerin düzenlemesi ?
         * Gereksiz dizileri kaldır
         * Ekrana view'lar bir animasyonla gelsin örneğin solgunken açılsın yani transparanken zamanla görünür olsun gibi
         *
         * Her sembolde aynı süre olmasın kimi semboller daha basit çünkü
         */

        hashAllViews = new HashMap<>();
        hashSymbolDesignViews = new HashMap<>();
        arrayNumberText = Arrays.asList(1,3,5,11,13,15,21,23,25,31,33,35,41,43,45,51,53,55,61,63,65);
        arrayOperatorText = Arrays.asList(2,6,8,10,12,22,26,28,30,32,42,46,48,50,52,62);
        arrayEqualsText = Arrays.asList(4,14,16,18,20,24,34,36,38,40,44,54,56,58,60,64);

        /**
         * Karmaşıklık önlemek için spam dizi eklendi
         * Yeni model tasarlandığında zaman sadece arrayGameSymbol dizisine açık kalması gereken view'ların sırası dizi şeklinde eklenmesi yeterlidir.
         * Eğer spam dizi eklenmeseydi arrayGameSymbol dizisinde dizayn elemanları 1'er eksik count ile yazılmalıydı [bir dizinin ilk elemanı 0'dır]
         * Bu durumda:
         *   Seçenek A-) Spam satırı eklenmez, arrayGameSymbol dizisinde tasarımlar 1'er eksik count ile yazılır, geliştiricide kafa karışıklığı yaratabilir
         *   Seçenek B-) Spam satırı eklenmez, arrayGameSymbol bu şekilde bırakılır ancak döngü aşamasında (iç içe for'da [view tespiti] +1/-1 eklenir)
         * + Seçenek C-) Spam satırı eklenir, arrayGameSymbol'e eklenen dizi sırası ile soruların sırası aynı kalır
         */

        Integer[][] arrayQuestionOrder = {
                {0}, // Spam dizi
                {1,2,3,4,5}, // Soru-1 --> [1.Satır]
                {11,12,13,14,15}, // Soru-2 --> [2.Satır]
                {21,22,23,24,25}, // Soru-3 --> [3.Satır]
                {31,32,33,34,35}, // Soru-4 --> [4.Satır]
                {41,42,43,44,45}, // Soru-5 --> [5.Satır]
                {51,52,53,54,55}, // Soru-6 --> [6.Satır]
                {61,62,63,64,65}, // Soru-7 --> [7.Satır]
                {1,6,11,16,21}, // Soru-8 --> [1.Sütun 1.Soru]
                {21,26,31,36,41}, // Soru-9 --> [1.Sütun 2.Soru]
                {41,46,51,56,61}, // Soru-10 --> [1.Sütun 3.Soru]
                {3,8,13,18,23}, // Soru-11 --> [2.Sütun 1.Soru]
                {23,28,33,38,43}, // Soru-12 --> [2.Sütun 2.Soru]
                {43,48,53,58,63}, // Soru-13 --> [2.Sütun 3.Soru]
                {5,10,15,20,25}, // Soru-14 --> [3.Sütun 1.Soru]
                {25,30,35,40,45}, // Soru-15 --> [3.Sütun 2.Soru]
                {45,50,55,60,65}, // Soru-16 --> [3.Sütun 3.Soru]
        };

        Integer[][] arrayGameSymbol = {
                {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16}, // Symbol_Table [Bölüm sonu sorusu o yüzden random +1 eklenecek]
                {1,4,7,8,9,10,14,15,16}, // Symbol_Eight
                {4,8,9,10,14,15,16}, // Symbol_H
                {1,7,10,11,12,13,14}, // Symbol_Tetris
                {1,3,5,7,8,10,12,14,16}, // Symbol_Chain
                {1,2,6,7,8,10,11,13,14,16}, // Symbol_Pacman
                {1,3,4,5,7,8,16}, // Symbol_Loophole
                {1,2,3,4,5,6,7,11,12,13}, // Symbol_Comb
                {1,4,7,11,12,13}, // Symbol_Couple
                {1,4,7,10,11,13,14}, // Symbol_F_Couple
                {1,3,7,8,11,12,13,15,16}, // Symbol_Dollar
                {1,3,5,7,10,11,12,13,14}, // Symbol_Paperclip
        };

        liveCreateViews.observe(getViewLifecycleOwner(), create -> {

            if (create) {

                for (int viewCount = 1; viewCount < 66; viewCount++) {

                    GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                    params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                    params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
                    params.topMargin = 10;
                    params.leftMargin = 6;

                    EditText editInput = new EditText(requireActivity(), null, 0, R.style.gameSymbolText);
                    editInput.setLayoutParams(params);
                    editInput.setVisibility(View.INVISIBLE);


                    if(hashSymbolDesignViews.get(viewCount+"_hide") != null) {

                        editInput.setId(viewCount);
                        editInput.setVisibility(View.VISIBLE);

                        if (Boolean.TRUE.equals(hashSymbolDesignViews.get(viewCount + "_hide"))) editInput.setText("");
                        else {

                            if (arrayNumberText.contains(viewCount)) editInput.setText(String.valueOf(new Random().nextInt(300)));
                            else if (arrayOperatorText.contains(viewCount)) editInput.setText("+");
                            else if (arrayEqualsText.contains(viewCount)) {
                                editInput.setText("=");
                                editInput.setEnabled(false);
                            }

                        }

                    }
                    hashAllViews.put(viewCount, new SoftReference<>(editInput));
                    binding.gridGameSymbol.addView(editInput);

                }

            }

        });


        run = () -> {

            binding.gridGameSymbol.removeAllViews();
            hashSymbolDesignViews.clear();
            int rndSelectSymbol = new Random().nextInt(arrayGameSymbol.length);
            if (rndSelectSymbol == 0) rndSelectSymbol++; // Tablo bölüm sonu sorusu olduğu için, random denk gelirse değiştirilecek

            /**
             * 0 - 1.sayı gizlenecek [1.sayı 0. count view]
             * 1 - 2.sayı gizlenecek [2.sayı 2. count view]
             * 2 - sonuç gizlenecek [sonuç 4.count view]
             */

            int randomHide = new Random().nextInt(3);
            int hideType = (randomHide == 0 ? 0 : (randomHide == 1) ? 2 : 4);

            for (int question : arrayGameSymbol[rndSelectSymbol]) {

                for (int questionView = 0; questionView < 5; questionView++) {

                    int currentView = arrayQuestionOrder[question][questionView]; // Şuanki kontrol edilen view

                    /**
                     * Eğer bu view daha önce gizlendi ise aynı değerini korumaya devam etmeli aksi halde bug ortaya çıkıyor
                     * Örneğin: 2.sayı gizlenecek. 2.sayı -> 1.sorunun 3.view'ı, aynı view 11.sorunun 1.view'ı
                     * Kontrol esnasında 1.soruda 2.sayı [3.view] gizlenirken, 11.soruda view count eşleşmesi olmadığı için [hideType] visible olacak
                     * Bu durumda belirlenen oyun düzeni bozulmuş olacak bunu egale etmek için eğer o görünüm daha önce gizlendi ise aynı şekilde devam ediyor
                     */
                    if (Boolean.TRUE.equals(hashSymbolDesignViews.get(currentView + "_hide"))) hashSymbolDesignViews.put(currentView + "_hide", true);
                    else hashSymbolDesignViews.put(currentView+ "_hide", questionView == hideType);


                    int lastSymbolQuestion = arrayGameSymbol[rndSelectSymbol][arrayGameSymbol[rndSelectSymbol].length-1]; // Son soru
                    // Eğer son sorunun view'ı kontrol edildi ise düzen oluşturulmaya başlanıyor

                    if (question == lastSymbolQuestion && questionView == 4) liveCreateViews.postValue(true);

                }

            }
        };

        hand = new Handler(Looper.getMainLooper());


        hand.postDelayed(run, 0);

    }

}