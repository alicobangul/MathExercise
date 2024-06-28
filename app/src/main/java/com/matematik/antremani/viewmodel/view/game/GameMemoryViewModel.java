package com.matematik.antremani.viewmodel.view.game;

import android.app.Application;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.matematik.antremani.model.GameSettings;
import com.matematik.antremani.tool.game.CreateQuestion;

public class GameMemoryViewModel extends ViewModel {

    private final Application application;

    private final SavedStateHandle state;

    private final GameSettings gameSettings;

    private final CreateQuestion createQuestion;

    public GameMemoryViewModel(Application application, SavedStateHandle state, GameSettings gameSettings, CreateQuestion createQuestion) {
        this.application = application;
        this.state = state;
        this.gameSettings = gameSettings;
        this.createQuestion = createQuestion;
    }
}
