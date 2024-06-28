package com.matematik.antremani.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.matematik.antremani.R;

public class AdUtil {

    public AdUtil() {
    }

    public void reklamindir(InterstitialAdLoadCallback loadCallback) {

//        InterstitialAd.load(
//                context,
//                context.getString(R.string.ad_interstitial_test),
//                new AdRequest.Builder().build(),
//                loadCallback
//        );

    }

    public void indirmelistener(String getclassGetSimpleName) {

        InterstitialAdLoadCallback indirmecallback = new InterstitialAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                // Loading false
                Log.e("ERROR", "["+getclassGetSimpleName+"] -> Geçiş reklamı yüklenemedi");
            }

            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                super.onAdLoaded(interstitialAd);
                // interstitial ad hazır değişkene al
                // Loading false
                Log.i("INFO", "["+getclassGetSimpleName+"] -> Geçiş reklamı yüklendi");
            }
        };

    }

    public void gosterimlistener(String getclassGetSimpleName) {

        FullScreenContentCallback reklamlistener = new FullScreenContentCallback() {

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
                Log.i("INFO", "["+getclassGetSimpleName+"] sınıfından tetiklenen reklam kapatıldı [Dismissed]");
                // Reklamı null yap
                // Reklam görüntülenme tamamlandı sonraki işlemi tetikle
            }

            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                Log.i("ERROR", "["+getclassGetSimpleName+"] sınıfından tetiklenen reklam gösterilemedi [AdFailed]");
                Log.i("ERROR", adError.getMessage());
                // Reklamı null yap
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
                Log.i("INFO", "["+getclassGetSimpleName+"] görüntülenme AdMob sunucusuna gönderildi [Impression]");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent();
                Log.i("INFO", "["+getclassGetSimpleName+"] sınıfından tetiklenen reklam gösterildi [AdShowed]");
            }
        };

    }

}
