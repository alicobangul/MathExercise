package com.matematik.antremani.view.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.matematik.antremani.databinding.DialogModeLevelBinding;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.scopes.FragmentScoped;
import dagger.hilt.android.scopes.ViewScoped;

@FragmentScoped
public class ModeLevelDialog extends DialogFragment {

    private Dialog dialog;

    @Inject
    public ModeLevelDialog() {}

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {

        super.show(manager, tag);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        dialog = new Dialog(requireContext());

        DialogModeLevelBinding dialogBinding = DialogModeLevelBinding.inflate(requireActivity().getLayoutInflater());

        dialog.setContentView(dialogBinding.getRoot());

        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        dialogBinding.getRoot().setOnClickListener(v -> dialog.dismiss());

        return dialog;
    }

}
