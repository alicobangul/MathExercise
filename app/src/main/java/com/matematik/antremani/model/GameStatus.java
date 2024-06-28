package com.matematik.antremani.model;

import java.io.Serializable;

public class GameStatus implements Serializable {

    private Boolean isGameActive = false;
    private Boolean isTimeEnd = false;
    private Boolean isWrongAnswerSelected = false;
    private Integer correctAnswerCount = 0;

    private Boolean isUseBonus = false;

    private Boolean isWrongAnswerDialogShow = false;

    public GameStatus() {}

    public void defaultGameStatus() {

        isGameActive = false;
        isTimeEnd = false;
        isWrongAnswerSelected = false;

        isWrongAnswerDialogShow = false;

    }

    public Boolean getIsGameActive() {
        return isGameActive;
    }

    public void setIsGameActive(Boolean isGameActive) {
        this.isGameActive = isGameActive;
    }

    public Boolean getIsTimeEnd() {
        return isTimeEnd;
    }

    public void setIsTimeEnd(Boolean isTimeEnd) {
        this.isTimeEnd = isTimeEnd;
    }

    public Boolean getIsWrongAnswerSelected() {
        return isWrongAnswerSelected;
    }

    public void setIsWrongAnswerSelected(Boolean isWrongAnswerSelected) { this.isWrongAnswerSelected = isWrongAnswerSelected; }

    public Integer getCorrectAnswerCount() {return correctAnswerCount;}

    public void updateCorrectAnswerCount() { correctAnswerCount++; }

    public Boolean getUseBonus() { return isUseBonus; }

    public void setUseBonus(Boolean useBonus) { isUseBonus = useBonus; }

    public Boolean getWrongAnswerDialogShow() {
        return isWrongAnswerDialogShow;
    }

    public void setWrongAnswerDialogShow(Boolean wrongAnswerDialogShow) { isWrongAnswerDialogShow = wrongAnswerDialogShow; }

}
