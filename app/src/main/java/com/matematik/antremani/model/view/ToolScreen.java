package com.matematik.antremani.model.view;

import android.view.View;

import java.io.Serializable;

public class ToolScreen implements Serializable {

    private Integer time;
    private Integer currentTime;
    private String levelOrCorrectText = "0";
    private Integer timeTextVisibility = View.VISIBLE;
    private Integer timeProgressVisibility = View.VISIBLE;
    private Integer bonusEasierVisibility = View.VISIBLE;
    private Integer bonusClueVisibility = View.VISIBLE;

    public ToolScreen(Integer gameTime) {
        this.time = gameTime;
        this.currentTime = gameTime;
    }

    private Integer bonusAddTimeVisibility = View.VISIBLE;

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Integer currentTime) {
        this.currentTime = currentTime;
    }


    public String getLevelOrCorrectText() {
        return levelOrCorrectText;
    }

    public void setLevelOrCorrectText(String levelOrCorrectText) {this.levelOrCorrectText = levelOrCorrectText;}


    public Integer getTimeTextVisibility() {
        return timeTextVisibility;
    }

    public void setTimeTextVisibility(Integer timeTextVisibility) {
        this.timeTextVisibility = timeTextVisibility;
    }

    public Integer getTimeProgressVisibility() {
        return timeProgressVisibility;
    }

    public void setTimeProgressVisibility(Integer timeProgressVisibility) {
        this.timeProgressVisibility = timeProgressVisibility;
    }

    public Integer getBonusEasierVisibility() {
        return bonusEasierVisibility;
    }

    public void setBonusEasierVisibility(Integer bonusEasierVisibility) {
        this.bonusEasierVisibility = bonusEasierVisibility;
    }

    public Integer getBonusClueVisibility() {
        return bonusClueVisibility;
    }

    public void setBonusClueVisibility(Integer bonusClueVisibility) {
        this.bonusClueVisibility = bonusClueVisibility;
    }

    public Integer getBonusAddTimeVisibility() {
        return bonusAddTimeVisibility;
    }

    public void setBonusAddTimeVisibility(Integer bonusAddTimeVisibility) {
        this.bonusAddTimeVisibility = bonusAddTimeVisibility;
    }

    public Integer getBonusNoTimeVisibility() {
        return bonusNoTimeVisibility;
    }

    public void setBonusNoTimeVisibility(Integer bonusNoTimeVisibility) {
        this.bonusNoTimeVisibility = bonusNoTimeVisibility;
    }

    private Integer bonusNoTimeVisibility = View.VISIBLE;

}
