package com.matematik.antremani.view.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentGamePuzzleBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GamePuzzleFragment extends Fragment {

    private FragmentGamePuzzleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGamePuzzleBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 10.level zorluğu ?

        Integer[][] arrayLines = {
                {0}, // Spam dizi
                {1,2,3,4,5,6,7,8}, // Soru-1 --> [1.Satır]
                {9,10,11,12,13,14,15,16}, // Soru-2 --> [2.Satır]
                {17,18,19,20,21,22,23,24}, // Soru-3 --> [3.Satır]
                {25,26,27,28,29,30,31,32}, // Soru-4 --> [4.Satır]
                {33,34,35,36,37,38,39,40}, // Soru-5 --> [5.Satır]
                {41,42,43,44,45,46,47,48}, // Soru-6 --> [6.Satır]
        };

        for (int i = 1; i < 49; i++) {

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.rowSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
            params.bottomMargin = (i < 41) ? 75 : 45; // Son satırdaki görünümler sadece 45dp yükseltiliyor
            params.rightMargin = 10;
            params.leftMargin = 10;


            EditText editInput = new EditText(requireActivity(), null, 0, R.style.gamePuzzleText);
            editInput.setLayoutParams(params);
            editInput.setVisibility(View.VISIBLE);
            editInput.setText("3");
            binding.gridGamePuzzle.addView(editInput);

        }


    }

}