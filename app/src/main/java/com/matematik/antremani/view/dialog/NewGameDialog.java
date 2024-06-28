package com.matematik.antremani.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import com.matematik.antremani.Constant;
import com.matematik.antremani.R;
import com.matematik.antremani.databinding.DialogModeSelectedBinding;
import com.matematik.antremani.model.GameSettings;
import com.matematik.antremani.repository.MathRepositoryImpl;
import com.matematik.antremani.util.GameUtil;
import com.matematik.antremani.util.Util;
import com.matematik.antremani.util.WorkUtil;
import java.util.Objects;
import javax.inject.Inject;

import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
public class NewGameDialog extends DialogFragment {

    private DialogModeSelectedBinding dialogBinding;

    private GameSettings gameSettings;

    @Inject
    MathRepositoryImpl mathRepository;

    @Inject
    GameUtil gameUtil;

    @Inject
    WorkUtil workUtil;

    @Inject
    Util util;

    @Inject
    public NewGameDialog() {}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Dialog dialog = new Dialog(requireContext());

        dialogBinding = DialogModeSelectedBinding.inflate(requireActivity().getLayoutInflater());

        dialog.setContentView(dialogBinding.getRoot());
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialogBinding.setDialog(this);

        dialogBinding.getRoot().setOnClickListener(v -> dialog.dismiss());

        dialogBinding.setSelectedMode(requireContext().getString(R.string.mode_classic)); // İlk açılışta ilk mod seçili olacak

        dialogBinding.btnOkey.setOnClickListener(v -> {

            dialog.dismiss();

            createGameModel();

            changeFragmentForGame();

        });

        return dialog;
    }

    public void radioButtonClick(@NonNull View view) { dialogBinding.setSelectedMode(view.getTag().toString()); }

    private void createGameModel() {

        gameSettings = new GameSettings(
                Constant.TYPE_LEVEL,
                Objects.requireNonNull(dialogBinding.getSelectedMode()),
                Constant.TIME_FIXED,
                gameUtil.getDefaultModeTime(dialogBinding.getSelectedMode()), // Seçilen oyun moduna göre fixed süre ayarlanıyor
                gameUtil.numberRangeEnd(Objects.requireNonNull(dialogBinding.getSelectedMode()), mathRepository.getModeLevel(dialogBinding.getSelectedMode())),
                true,
                true,
                true,
                true
        );

    }

    private void changeFragmentForGame() {

        Bundle arg = new Bundle();
        arg.putSerializable("gameSettings", gameSettings);

        // Oyun açılıyorken fragment backstack yapıyor olabilir, belki popupto eklenmiş action ile gitmeli ?
        workUtil.changeScreen(util.getModeFragmentId(dialogBinding.getSelectedMode()),false, arg);

    }

}