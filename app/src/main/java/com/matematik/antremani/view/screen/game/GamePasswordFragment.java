package com.matematik.antremani.view.screen.game;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentGamePasswordBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GamePasswordFragment extends Fragment {

    private FragmentGamePasswordBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGamePasswordBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * x rakam doğru ama yeri yanlış
         * x rakam doğru ve yeri doğru
         * x rakam doğru ama yerleri yanlış
         * tüm rakamlar yanlış
         * sayı x sayısına (3,4,5,6,8,9,11,12) tam bölünüyor
         * sayı asal bir sayı
         */

        createInput();

    }

    private void createInput() {

        // Save state durumları tutuldu mu ?

        for (int i = 0; i < 5; i++) {

            EditText input = new EditText(requireActivity(), null, 0, R.style.gamePasswordText);
            input.setText("9");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            params.setMargins(10,35,10,35);
            input.setPadding(15,15,15,15);
            input.setLayoutParams(params);
            binding.linearPasswordText.addView(input);

        }

        for (int i = 0; i < 5; i++) {

            TextView input = new TextView(requireActivity(), null, 0, R.style.gamePasswordInfo);
            input.setText("289 - Bir rakam doğru ama yeri yanlış");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);
            params.setMargins(7,15,7,15);
            input.setPadding(5,0,5,0);
            input.setLayoutParams(params);
            binding.linearPassword.addView(input);

        }

    }

}