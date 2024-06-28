package com.matematik.antremani.util;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import java.io.Serializable;
import com.matematik.antremani.Constant;
import com.matematik.antremani.repository.MathRepositoryImpl;
import com.matematik.antremani.view.dialog.GameDialog;

import java.util.Random;

import javax.inject.Inject;

public class GameUtil implements Serializable {

    @Inject
    public GameUtil() {}

    @NonNull
    public Integer getDefaultModeTime(@NonNull String mode) {

        // Level türündeki oyun modlarının fixed süreleri

        return switch (mode) {
            case Constant.MODE_PUZZLE, Constant.MODE_PASSWORD -> 180;
            case Constant.MODE_LOGIC -> 15;
            case Constant.MODE_SYMBOL -> 30;
            default -> 5;
        };

    }


    @NonNull
    public Integer numberRangeStart(Integer rangeEnd, @NonNull String operator) {

        /**
         * Eğer toplama ise: aralık başlangıcı (rangeEnd/3)
         * (örneğin 23-70 aralık: 55+70,60+68,51+70,71+70, 44+58)
         * (örneğin 30-90 aralık: 65+78, 77+80, 88+90, 61+88)
         *
         * Eğer çıkarma ise: aralık başlangıcı (rangeEnd/3)
         * (örneğin 20-60 aralık: 58-21, 47-25, 35-50, 52-44)
         *
         * Eğer çarpma ise: aralık başlangıcı 1
         *
         * Eğer bölme ise: aralık başlangıcı 1 ancak sayı ayarlamasında 2.sayı 1.sayının maksimum 4/1'i kadar.
         * Aksi durumda bölme işlemi basitleşiyor. (Örneğin 240/70 = 3)
         */

        return switch (operator) {
            case Constant.OPERATOR_ADDITION, Constant.OPERATOR_SUBTRACTION -> rangeEnd / 3;
            default -> 1;
        };

    }

    @NonNull
    public Integer numberRangeEnd(@NonNull String mode, String level) {

        if (mode.matches(Constant.MODE_PASSWORD)) return (Integer.parseInt(level) <= 10) ? 200 : (Integer.parseInt(level) * 50);

        else return (Integer.parseInt(level) <= 10) ? 20 : 20 + (Integer.parseInt(level) / 2);

    }

    @NonNull
    public Integer learnDifficultyLevel(String level) {

        return (Integer.parseInt(level) % 10 > 0) ? Integer.parseInt(level) + (10 - Integer.parseInt(level) % 10) : Integer.parseInt(level);

    }

    @NonNull
    public Integer randomNumber(@NonNull Random random, Integer min, Integer max) {

        return random.nextInt((max - min) + 1) + min;

    }

    public void showDialog(
            FragmentManager fragmentManager,
            Boolean isWrongAnswerDialog,
            Boolean isTypeLevel,
            String gameMode,
            Integer correctAnswerCount,
            Runnable restartProcess,
            Runnable dismissProcess,
            Runnable exitProcess
    ) {
        GameDialog userDialog = new GameDialog(
                isWrongAnswerDialog,
                isTypeLevel,
                gameMode,
                correctAnswerCount,
                restartProcess,
                exitProcess,
                dismissProcess
        );

        userDialog.show(fragmentManager, "GameUtil - showDialog");

    }

}
