package com.matematik.antremani.view.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentGameLogicBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameLogicFragment extends Fragment {

    private FragmentGameLogicBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameLogicBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**
         * Logaritmik olarak kaç tane soru eklemen gerekiyorsa o kadar textview ekle her şey ayarlı
         * 10 .level zorluğu ?
         */

        for (int i = 1; i < 3; i++) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.weight = 1;

            TextView editInput = new TextView(requireActivity(), null, 0, R.style.gameLogicQuestionText);
            editInput.setLayoutParams(params);
            editInput.setVisibility(View.VISIBLE);
            editInput.setText("A + B = 30");
            binding.linearLogicQuestion.addView(editInput);

        }
    }

}