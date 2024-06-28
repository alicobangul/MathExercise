package com.matematik.antremani.util;

import androidx.annotation.NonNull;
import com.matematik.antremani.Constant;
import com.matematik.antremani.R;
import org.jetbrains.annotations.Contract;
import java.util.Locale;
import javax.inject.Inject;

public class Util {

    @Inject
    public Util() {}

    @NonNull
    public String getSystemLanguage() { return (Locale.getDefault().getLanguage().matches("tr") ? "tr" : "en"); }

    @Contract(pure = true)
    public Integer getModeFragmentId(@NonNull String mode) {

        return switch (mode) {

            case Constant.MODE_CLASSIC -> R.id.gameClassicFragment;

            case Constant.MODE_PUZZLE -> R.id.gamePuzzleFragment;

            case Constant.MODE_PASSWORD -> R.id.gamePasswordFragment;

            case Constant.MODE_LOGIC -> R.id.gameLogicFragment;

            case Constant.MODE_SYMBOL -> R.id.gameSymbolFragment;

            case Constant.MODE_MEMORY -> R.id.gameMemoryFragment;

            case Constant.MODE_DUEL -> R.id.gameDuelFragment;

            case Constant.MODE_JIGSAW -> R.id.gameJigsawFragment;

            default -> R.id.gameTicTacToeFragment;

        };

    }

}
