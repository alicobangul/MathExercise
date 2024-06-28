package com.matematik.antremani.util;

import android.app.Activity;
import android.content.IntentSender;
import com.google.android.gms.ads.MobileAds;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

public class AppUtil {

    private final Activity activity;

    public AppUtil(Activity activity) { this.activity = activity; }

    public void initAds() { MobileAds.initialize(activity); }

    public void checkInAppUpdate() {

        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(activity);

        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        appUpdateInfoTask.addOnSuccessListener(result -> {

            // Bir güncelleme var mı ?
            if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

                try { appUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, activity,60); }

                catch (IntentSender.SendIntentException e) { e.getStackTrace(); }

            }

        });

    }

}
