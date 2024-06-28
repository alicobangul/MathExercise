package com.matematik.antremani.view.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.matematik.antremani.databinding.FragmentGameMemoryBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameMemoryFragment extends Fragment {

    private FragmentGameMemoryBinding binding;

    Integer showCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameMemoryBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showCount = 0;
        String[] arrayString = {"3","5","+","2","5","=","?"};

        Handler handler = new Handler(Looper.getMainLooper());
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(showCount < arrayString.length) {
                    binding.txtVelocityQuestion.setText(arrayString[showCount]);
                    showCount++;
                    handler.postDelayed(this, 750);
                }
            }
        };

        binding.txtVelocityQuestion.setText("");
        handler.postDelayed(runnable,750);


        /**
         * Levele göre soru oluşturduktan sonra tek yapman gereken yukarıdaki gibi karakterlere bölüp dizi yaratmak sonrada süreyle onları göstermek
         *
         * 10.levelde süre 500ms olabilir hızlıca gösterim için
         *
         * Bonus olarakta yeni soru yerine daha yavaş gösterim, daha kolay soru olabilir
         */


    }

}