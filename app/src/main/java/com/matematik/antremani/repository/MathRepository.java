package com.matematik.antremani.repository;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class MathRepository implements MathRepositoryImpl {

    private final SharedPreferences sharedPreferences;

    @Inject
    public MathRepository(SharedPreferences sharedPreferences) { this.sharedPreferences = sharedPreferences; }

    @Override
    public void upgradeLevel(String mode) { sharedPreferences.edit().putInt(mode, getModeLevelInt(mode) + 1).apply(); }

    @Override
    public String getModeLevel(String mode) { return String.valueOf(getModeLevelInt(mode)); }

    @Override
    public Integer getModeLevelInt(String mode) { return sharedPreferences.getInt(mode, 1); }

}
