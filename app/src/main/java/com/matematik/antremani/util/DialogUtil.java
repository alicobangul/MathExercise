package com.matematik.antremani.util;

import androidx.fragment.app.Fragment;

import com.matematik.antremani.view.dialog.ModeLevelDialog;
import com.matematik.antremani.view.dialog.NewGameDialog;

import javax.inject.Inject;

import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
public class DialogUtil {

    private final Fragment fragment;

    @Inject
    ModeLevelDialog modeLevelDialog;

    @Inject
    NewGameDialog newGameDialog;

    @Inject
    public DialogUtil(Fragment fragment) { this.fragment = fragment; }

    public void showModeLevelDialog() { modeLevelDialog.show(fragment.getChildFragmentManager(), null); }

    public void showNewGameDialog() { newGameDialog.show(fragment.getChildFragmentManager(), null); }

}