package com.matematik.antremani.view.screen.game;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentGameClassicBinding;
import com.matematik.antremani.databinding.FragmentGameTictactoeBinding;

public class GameTicTacToeFragment extends Fragment {

    private FragmentGameTictactoeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGameTictactoeBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}