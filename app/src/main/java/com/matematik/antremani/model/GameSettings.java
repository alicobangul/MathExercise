package com.matematik.antremani.model;

import androidx.annotation.NonNull;
import com.matematik.antremani.Constant;
import org.jetbrains.annotations.NotNull;
import java.io.Serializable;

public class GameSettings implements Serializable {

    @NotNull private String gameType = Constant.TYPE_LEVEL;
    @NotNull private String gameMode = Constant.MODE_CLASSIC;
    @NotNull private String timeMode = Constant.TIME_FIXED;
    @NotNull private Integer time = 5;
    @NotNull private Integer numberRange = 20;
    @NotNull private Boolean isAddition = true;
    @NotNull private Boolean isSubtraction = true;
    @NotNull private Boolean isMultiply = true;
    @NotNull private Boolean isDivision = true;

    @NotNull private Boolean isEmptyCreate = true;

    public GameSettings(
            @NonNull String gameType,
            @NonNull String gameMode,
            @NonNull String timeMode,
            @NonNull Integer time,
            @NonNull Integer numberRange,
            @NonNull Boolean isAddition,
            @NonNull Boolean isSubtraction,
            @NonNull Boolean isMultiply,
            @NonNull Boolean isDivision) {

        this.gameType = gameType;
        this.gameMode = gameMode;
        this.timeMode = timeMode;
        this.time = time;
        this.numberRange = numberRange + 1; // Random oluştururken gerekli
        this.isAddition = isAddition;
        this.isSubtraction = isSubtraction;
        this.isMultiply = isMultiply;
        this.isDivision = isDivision;

        this.isEmptyCreate = false;
    }

    public GameSettings() {}

    public boolean isTypeLevel() { return getGameType().matches(Constant.TYPE_LEVEL); }

    public boolean isTypeCustom() { return getGameType().matches(Constant.TYPE_CUSTOM); }

    @NotNull
    public String getGameType() { return gameType; }

    public void setGameType(@NotNull String gameType) { this.gameType = gameType; }

    @NotNull
    public String getGameMode() { return gameMode; }

    public void setGameMode(@NotNull String gameMode) { this.gameMode = gameMode; }

    @NotNull
    public String getTimeMode() { return timeMode; }

    public void setTimeMode(@NotNull String timeMode) { this.timeMode = timeMode; }

    @NotNull
    public Integer getTime() { return time; }

    public void setTime(@NotNull Integer time) { this.time = time; }


    @NotNull
    public Integer getNumberRange() { return numberRange; }

    public void setNumberRange(@NotNull Integer numberRange) { this.numberRange = numberRange + 1; }

    @NotNull
    public Boolean getAddition() { return isAddition; }

    public void setAddition(@NotNull Boolean isAddition) { this.isAddition = isAddition; }

    @NotNull
    public Boolean getSubtraction() { return isSubtraction; }

    public void setSubtraction(@NotNull Boolean isSubtraction) { this.isSubtraction = isSubtraction; }

    @NotNull
    public Boolean getMultiply() { return isMultiply; }

    public void setMultiply(@NotNull Boolean isMultiply) { this.isMultiply = isMultiply; }

    @NotNull
    public Boolean getDivision() { return isDivision; }

    public void setDivision(@NotNull Boolean isDivision) { this.isDivision = isDivision; }

    @NotNull
    public Boolean getIsEmptyCreate() { return isEmptyCreate; }

    public void setIsEmptyCreate(@NotNull Boolean isEmptyCreate) { this.isEmptyCreate = isEmptyCreate; }

}
