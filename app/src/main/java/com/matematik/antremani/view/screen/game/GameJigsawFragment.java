package com.matematik.antremani.view.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentGameJigsawBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameJigsawFragment extends Fragment {

    private FragmentGameJigsawBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameJigsawBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void startGame() {

        // Oyun başlıyor

    }

}