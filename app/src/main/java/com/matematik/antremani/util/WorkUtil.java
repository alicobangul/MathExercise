package com.matematik.antremani.util;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.matematik.antremani.R;

import java.util.Objects;

import javax.inject.Inject;

public class WorkUtil {

    private final Fragment fragment;

    private NavController navController;

    private String errorOutput;

    @Inject
    public WorkUtil(@NonNull Fragment fragment) { this.fragment = fragment; }

    public void changeScreen(Integer screenOrAction, @NonNull Boolean isForError, @Nullable Bundle argument) {

        navController = Navigation.findNavController(fragment.requireView());

        String currentFragment = "Aktif Fragment : " + getFragmentName(Objects.requireNonNull(navController.getCurrentDestination()).getId());

        String targetFragment = "Hedef Fragment : " + getFragmentName(screenOrAction);

        errorOutput = currentFragment + " | " + targetFragment;

        try { navController.navigate(screenOrAction, argument); }

        catch (IllegalStateException | IllegalArgumentException exception) {

            if (!isForError) openMainScreenForError();

            else userInfoForError();

        }
    }

    public void goToMainScreen() { changeScreen(R.id.mainFragment, false, null); }

    @NonNull
    private String getFragmentName(Integer id) {

        try { return Objects.requireNonNull(Objects.requireNonNull(navController.getGraph().findNode(id)).getLabel()).toString(); }

        catch (NullPointerException ignored) { return "BİLİNMİYOR"; }

    }

    private void openMainScreenForError() {

        Log.e("ERROR", "[WorkUtil-changeScreen] -> Fragment açılamadı | " + errorOutput);

        Log.e("ERROR", "[WorkUtil-changeScreen] -> MainFragmenta yönlendiriliyor");

        changeScreen(R.id.mainFragment, true, null);

    }

    private void userInfoForError() {

        Log.e("ERROR", "[WorkUtil-changeScreen] -> Fragment açılamadı | MainFragment yönlendirildi | MainFragmentda açılamadı");

        Log.e("ERROR", "[WorkUtil-changeScreen] -> Kullanıcıya uygulamayı tekrar başlatması için snackbar gösteriliyor");

        Snackbar.make(fragment.requireView(), fragment.getString(R.string.technicalError), Snackbar.LENGTH_LONG).show();

    }

}
