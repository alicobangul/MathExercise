package com.matematik.antremani.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.matematik.antremani.R;
import com.matematik.antremani.model.view.CustomScreen;

public class BindingUtil {

    @BindingAdapter("android:adShow")
    public static void adShow(@NonNull AdView view, @NonNull Boolean isShow) {

        if(isShow) view.loadAd(new AdRequest.Builder().build());

    }

    @SuppressLint("SetTextI18n")
    @BindingAdapter("android:levelVisibility")
    public static void levelVisibility(@NonNull TextView view, @NonNull Boolean isShow) {

        String modeLevelText = "Level : " + view.getContext().getSharedPreferences(view.getContext().getPackageName(), Context.MODE_PRIVATE).getInt(view.getTag().toString(), 1);

        // Örnek : Level : 3
        if(isShow) view.setText(modeLevelText);

    }

    @BindingAdapter("android:checkedGameMode")
    public static void checkedGameMode(@NonNull RadioButton view, @NonNull String gameMode) {

        view.setChecked(view.getTag().toString().matches(gameMode));

    }

    @BindingAdapter("android:checkedTimeMode")
    public static void checkedTimeMode(@NonNull RadioButton view, @NonNull CustomScreen settings) {

        view.setChecked(view.getTag().toString().matches(settings.getTimeMode()));

    }

    @BindingAdapter("android:animatedGif")
    public static void animatedGif(@NonNull ImageView view, @NonNull String dialogTitle) {

        Glide
                .with(view.getContext())
                .load((dialogTitle.matches(view.getContext().getString(R.string.timeOver))) ? R.raw.hourglass : R.raw.wrong)
                .into(view);

    }

}
