package com.matematik.antremani.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.matematik.antremani.R;

import javax.inject.Inject;

import dagger.hilt.android.scopes.FragmentScoped;

@FragmentScoped
public class UserUtil {

    private final Activity activity;

    @Inject
    public UserUtil(Activity activity) { this.activity = activity; }

    public void openShareMenu() {

        try {

            String message = activity.getString(R.string.app_name) +
                    activity.getString(R.string.invitation_message) +
                    activity.getString(R.string.playstore_app_link);

            activity.startActivity(
                    Intent.createChooser(
                            new Intent()
                                    .setAction(Intent.ACTION_SEND)
                                    .putExtra(Intent.EXTRA_TEXT, message)
                                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    .setType("text/plain"), null)
            );

        }

        catch (ActivityNotFoundException e) { Log.e("ERROR", "[UserUtil-openShareMenu] -> Paylaşım menüsü açılamadı"); }

    }

    public void inAppReview() {

        /**
         * Test için ReviewManagerFactory.create(activity) yerine -> new FakeReviewManager(activity) kullan
         * Eğer task sonuçları onSuccess dönüyorsa başarılıdır
         */

        ReviewManager reviewManager = ReviewManagerFactory.create(activity);

        reviewManager
                .requestReviewFlow()
                .addOnCompleteListener(task -> {

                    if(task.isSuccessful()){

                        reviewManager
                                .launchReviewFlow(activity, task.getResult())
                                .addOnCompleteListener(commentTask -> {

                                    if (commentTask.isSuccessful()) Toast.makeText(activity, activity.getString(R.string.thanksForComment),Toast.LENGTH_SHORT).show();

                                    else showVisitStoreQuestion(false);

                                });
                    }

                    else showVisitStoreQuestion(false);

                });

    }

    public void showVisitStoreQuestion(Boolean isForMoreApp) {

        String message = activity.getString(isForMoreApp ? R.string.visitStoreInformation : R.string.commentError);

        Snackbar
                .make(activity.findViewById(R.id.fragmentContainer), message, Snackbar.LENGTH_LONG)
                .setAction(activity.getString(R.string.okey), v -> openPlayStore(isForMoreApp))
                .setTextColor(Color.GRAY)
                .setActionTextColor(Color.WHITE)
                .show();

    }

    private void openPlayStore(Boolean isForMoreApp) {

        /**
         * Eğer isForMoreApp true ise geliştirici sayfası açılıyor (diğer uygulamaları görmek için)
         * False ise uygulama sayfası açılıyor
         */

        Uri link = Uri.parse(activity.getString(isForMoreApp ? R.string.playstore_developer_link : R.string.playstore_app_link));

        try {
            activity.startActivity(
                    new Intent(Intent.ACTION_VIEW, link)
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        catch (ActivityNotFoundException e) { Log.e("ERROR", "[UserUtil-openPlayStore] -> Play store açılamadı"); }

    }

}
