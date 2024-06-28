package com.matematik.antremani.view.screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.matematik.antremani.databinding.FragmentCustomBinding;
import com.matematik.antremani.impl.Game;
import com.matematik.antremani.viewmodel.view.CustomViewModel;
import com.matematik.antremani.viewmodel.SharedViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CustomFragment extends Fragment implements Game.Shared {

    private FragmentCustomBinding binding;

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCustomBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        CustomViewModel customViewModel = new ViewModelProvider(this).get(CustomViewModel.class);

        customViewModel.setImplShared(this);

        binding.setViewModel(customViewModel);

        binding.setLifecycleOwner(this);

        if (sharedViewModel.getCustomScreenValue() != null) customViewModel.restoreSettingsQuestion(binding.getRoot());

        customViewModel.saveSettingsQuestion.observe(getViewLifecycleOwner(), isShowQuestion -> {

        });

    }

    @Override
    public SharedViewModel getSharedViewModel() { return sharedViewModel; }

}