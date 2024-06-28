package com.matematik.antremani.repository;

public interface MathRepositoryImpl {

    void upgradeLevel(String mode);

    String getModeLevel(String mode);

    Integer getModeLevelInt(String mode);

}
