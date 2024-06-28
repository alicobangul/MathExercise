package com.matematik.antremani.model.view.game;

import android.view.View;
import java.io.Serializable;

public class GameClassicScreen implements Serializable {

    private String questionText = "";

    private String optionFirst = "";

    private Integer optionFirstVisibility = View.VISIBLE;
    private String optionSecond = "";
    private Integer optionSecondVisibility = View.VISIBLE;
    private String optionThird = "";
    private Integer optionThirdVisibility = View.VISIBLE;
    private String optionFourth = "";
    private Integer optionFourthVisibility = View.VISIBLE;

    public GameClassicScreen(String questionText, String optionFirst, String optionSecond, String optionThird, String optionFourth) {
        this.questionText = questionText;
        this.optionFirst = optionFirst;
        this.optionSecond = optionSecond;
        this.optionThird = optionThird;
        this.optionFourth = optionFourth;
    }

    public GameClassicScreen(){}

    public String getQuestionText() { return questionText; }

    public String getOptionFirst() { return optionFirst; }

    public Integer getOptionFirstVisibility() { return optionFirstVisibility; }

    public void setOptionFirstVisibility(Integer optionFirstVisibility) { this.optionFirstVisibility = optionFirstVisibility; }

    public String getOptionSecond() { return optionSecond; }

    public Integer getOptionSecondVisibility() { return optionSecondVisibility; }

    public void setOptionSecondVisibility(Integer optionSecondVisibility) { this.optionSecondVisibility = optionSecondVisibility; }

    public String getOptionThird() { return optionThird; }

    public Integer getOptionThirdVisibility() { return optionThirdVisibility; }

    public void setOptionThirdVisibility(Integer optionThirdVisibility) { this.optionThirdVisibility = optionThirdVisibility; }

    public String getOptionFourth() { return optionFourth; }

    public Integer getOptionFourthVisibility() { return optionFourthVisibility; }

    public void setOptionFourthVisibility(Integer optionFourthVisibility) { this.optionFourthVisibility = optionFourthVisibility; }

}
