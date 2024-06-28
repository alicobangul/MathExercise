package com.matematik.antremani.model.view;

import androidx.annotation.NonNull;

import com.matematik.antremani.Constant;

import java.io.Serializable;

public class CustomScreen implements Serializable {

    @NonNull private String gameMode = Constant.MODE_CLASSIC;
    @NonNull private String timeMode = Constant.TIME_METER;
    @NonNull private Integer maxTime = 600;
    @NonNull private Integer time = 300;
    @NonNull private Boolean isTimeEnabled = true;
    @NonNull private String numberRange = "20";
    @NonNull private Boolean isAddition = true;
    @NonNull private Boolean isSubtraction = true;
    @NonNull private Boolean isMultiply = true;
    @NonNull private Boolean isDivision = true;

    public CustomScreen() {}

    @NonNull
    public String getGameMode() { return gameMode; }

    public void setGameMode(@NonNull String gameMode) {
        this.gameMode = gameMode;

        if (gameMode.matches(Constant.MODE_PASSWORD)) if (Integer.parseInt(numberRange) < 200) setNumberRange("200");

    }

    @NonNull
    public String getTimeMode() { return timeMode; }

    public void setTimeMode(@NonNull String timeMode) {

        this.timeMode = timeMode;

        this.isTimeEnabled = !timeMode.matches(Constant.TIME_NOTIME);

        switch (timeMode) {
            case Constant.TIME_METER ->  {
                maxTime = 600;
                time = 300;
            }
            case Constant.TIME_BONUS, Constant.TIME_FIXED ->  {
                maxTime = 180;
                time = 30;
            }
            case Constant.TIME_NOTIME ->  {
                maxTime = 0;
                time = 0;
            }
        }

    }

    @NonNull
    public Integer getMaxTime() { return maxTime; }

    public void setMaxTime(@NonNull Integer maxTime) { this.maxTime = maxTime; }

    @NonNull
    public Integer getTime() { return time; }

    public void setTime(@NonNull Integer time) { this.time = time; }

    @NonNull
    public Boolean getIsTimeEnabled() { return isTimeEnabled; }

    public void setIsTimeEnabled(@NonNull Boolean isTimeEnabled) { this.isTimeEnabled = isTimeEnabled; }

    @NonNull
    public String getNumberRange() { return numberRange; }

    public void setNumberRange(@NonNull String numberRange) { this.numberRange = numberRange; }

    public void defaultNumberRange() { this.numberRange = "20"; }

    public void defaultPasswordModeNumberRange() { this.numberRange = "200"; }

    @NonNull
    public Boolean getAddition() { return isAddition; }

    public void setAddition(@NonNull Boolean isAddition) { this.isAddition = isAddition; }

    @NonNull
    public Boolean getSubtraction() { return isSubtraction; }

    public void setSubtraction(@NonNull Boolean isSubtraction) { this.isSubtraction = isSubtraction; }

    @NonNull
    public Boolean getMultiply() { return isMultiply; }

    public void setMultiply(@NonNull Boolean isMultiply) { this.isMultiply = isMultiply; }

    @NonNull
    public Boolean getDivision() { return isDivision; }

    public void setDivision(@NonNull Boolean isDivision) { this.isDivision = isDivision; }


}
