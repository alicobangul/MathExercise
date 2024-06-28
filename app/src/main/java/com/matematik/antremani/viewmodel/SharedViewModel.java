package com.matematik.antremani.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import com.matematik.antremani.R;
import com.matematik.antremani.tool.SingleLiveEvent;
import com.matematik.antremani.model.view.CustomScreen;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SharedViewModel extends ViewModel {

    private final SavedStateHandle state;

    @Inject
    public SharedViewModel(SavedStateHandle savedStateHandle) { this.state = savedStateHandle; }

    public MutableLiveData<CustomScreen> getCustomScreen() { return state.getLiveData("_customScreen", null); }
    public @Nullable CustomScreen getCustomScreenValue() { return getCustomScreen().getValue(); }
    public void setCustomScreen(@Nullable CustomScreen customScreen) { getCustomScreen().setValue(customScreen); }

    // Oyun aktif mi
    public Boolean getGameActive() { return (state.get("_gameActive") != null) ? state.get("_gameActive") : false; }
    public void setGameActive (Boolean value) { state.set("_gameActive", value); }

    public void fragmentChangeListener(@NonNull NavController controller) {

        controller.addOnDestinationChangedListener((navController, fragment, bundle) -> {

            // MainFragment veya CustomFragment açılırsa aktif oyun yok
            setGameActive(fragment.getId() != R.id.mainFragment && fragment.getId() != R.id.customFragment);

        });

    }

}
