package com.matematik.antremani.view.screen;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentMainBinding;
import com.matematik.antremani.util.DialogUtil;
import com.matematik.antremani.util.UserUtil;
import com.matematik.antremani.util.WorkUtil;

import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    @Inject
    DialogUtil dialogUtil;

    @Inject
    UserUtil userUtil;

    @Inject
    WorkUtil workUtil;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       viewListener();

    }

    private void viewListener() {

        binding.imgAllLevels.setOnClickListener(v -> dialogUtil.showModeLevelDialog());

        binding.btnLevelGameStart.setOnClickListener(v -> dialogUtil.showNewGameDialog());

        // Custom açılıyorken fragment backstack yapıyor olabilir, belki popupto eklenmiş action ile gitmeli ?

        binding.btnCustomGameSettings.setOnClickListener(v -> workUtil.changeScreen(R.id.customFragment, false,null));

        binding.btnShare.setOnClickListener(v -> userUtil.openShareMenu());

        binding.btnComment.setOnClickListener(v -> userUtil.inAppReview());

        binding.btnMoreApp.setOnClickListener(v -> userUtil.showVisitStoreQuestion(true));

    }

}