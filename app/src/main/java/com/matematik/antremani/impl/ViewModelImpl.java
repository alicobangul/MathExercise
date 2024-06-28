package com.matematik.antremani.impl;

import android.os.Bundle;

public interface ViewModelImpl {

    void checkArgsAndStart(Bundle argument);

    BonusImpl getBonusListener();

    void goToMainScreen();

    void restartProcess();

    void exitProcess();

    void dismissProcess();

}