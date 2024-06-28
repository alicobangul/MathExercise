package com.matematik.antremani.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.matematik.antremani.R;
import com.matematik.antremani.databinding.DialogGameInfoBinding;
import com.matematik.antremani.repository.MathRepository;
import com.matematik.antremani.repository.MathRepositoryImpl;
import com.matematik.antremani.util.Util;

import javax.inject.Inject;

public class GameDialog extends DialogFragment {

    private Dialog dialog;

    private final Boolean isWrongAnswerDialog;
    private final Boolean isTypeLevel;

    private final String gameMode;

    private final Integer correctAnswerCount;

    private final Runnable restartProcess;
    private final Runnable exitProcess;
    private final Runnable dismissProcess;

    @Inject
    Util util;

    @Inject
    MathRepositoryImpl mathRepository;

    public GameDialog(
            Boolean isWrongAnswerDialog,
            Boolean isTypeLevel,
            String gameMode,
            Integer correctAnswerCount,
            Runnable restartProcess,
            Runnable exitProcess,
            Runnable dismissProcess
    ) {
        this.isWrongAnswerDialog = isWrongAnswerDialog;
        this.isTypeLevel = isTypeLevel;
        this.gameMode = gameMode;
        this.correctAnswerCount = correctAnswerCount;
        this.restartProcess = restartProcess;
        this.exitProcess = exitProcess;
        this.dismissProcess = dismissProcess;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        String levelText = "LEVEL " + mathRepository.getModeLevel(gameMode); // Örnek : LEVEL 28
        String correctAnswerText = correctAnswerCount + " " + requireContext().getString(R.string.correctText); // Örnek : 3 DOĞRU CEVAP

        dialog = new Dialog(requireContext());

        DialogGameInfoBinding dialogBinding = DialogGameInfoBinding.inflate(requireActivity().getLayoutInflater());

        dialogBinding.setDialogTitle(requireContext().getString((isWrongAnswerDialog) ? R.string.wrongAnswer : R.string.timeOver));

        dialogBinding.setDialogText((isTypeLevel) ? levelText : correctAnswerText);

        dialogBinding.btnDialogPremium.setOnClickListener(view -> dialog.dismiss());

        dialogBinding.btnDialogRestart.setOnClickListener(view -> {
            restartProcess.run();
            dialog.dismiss();
        });

        dialogBinding.btnDialogExit.setOnClickListener(view -> {
            exitProcess.run();
            dialog.dismiss();
        });

        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        return dialog;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {

        dismissProcess.run();

        super.onDismiss(dialog);
    }
}
