package com.matematik.antremani.view.screen.game;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.matematik.antremani.R;
import com.matematik.antremani.databinding.FragmentGameDuelBinding;
import com.matematik.antremani.util.Util;

import java.util.Locale;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class GameDuelFragment extends Fragment {

    private FragmentGameDuelBinding binding;
    private String speechText;
    private String questionUiText;

    @Inject
    public Util util;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGameDuelBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextToSpeech textToSpeech;
        textToSpeech = new TextToSpeech(requireActivity().getApplicationContext(), status -> {}, "com.google.android.tts");

        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String utteranceId) {
                // Konuşma başladığında
            }

            @Override
            public void onDone(String utteranceId) {
                // Konuşma bitince
                // Butonlarada textler yerleştirilecek
                binding.txtListenerQuestion.setText(questionUiText);
            }

            @Override
            @SuppressWarnings("deprecation")
            public void onError(String utteranceId) {
                // Konuşma gerçekleştirilemedi
            }

        });


        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            textToSpeech.setLanguage(Locale.forLanguageTag(util.getSystemLanguage()));
            textToSpeech.setSpeechRate(0.8f);
            textToSpeech.setPitch(0.9f);
            createSpeechText("operator", 5, 5, null, 15);
            textToSpeech.speak(speechText, TextToSpeech.QUEUE_ADD, null, speechText);

        },1000);

        /**
         * Tek yapman gereken createspeechtext'e gerekli bilgileri vermek
         * Speech ondestroyview onstop stop ve shutdown yapmayı unutma
         * Bonus olarak daha yavaş konuşma olabilir
         * Aynı zamanda includegametool'da bonus butonların isimlerini değiştir (belki idleride değiştirilmeli)
         */

    }

    private void createSpeechText(String question, @Nullable Integer firstNumber, @Nullable Integer secondNumber, @Nullable String operator, @Nullable Integer result) {

        // Operatör sorulan soru değilse metnini bul

        String unknown = " x ";
        String operatorText = "";

        if (operator != null) {
            switch (operator) {
                case "+" -> operatorText = getString(R.string.plus);
                case "-" -> operatorText = getString(R.string.minus);
                case "x" -> operatorText = getString(R.string.times);
                case "/" -> operatorText = getString(R.string.dividedby);
            }
        }

        // Konuşma metni hazırlanıyor
        switch (question) {
            case "result" -> {
                speechText = firstNumber + operatorText + secondNumber;
                questionUiText = getString(R.string.questionResult);
            }
            case "operator" -> {
                speechText = firstNumber + getString(R.string.and) + secondNumber + getString(R.string.equals) + result;
                questionUiText = getString(R.string.questionOperator);
            }
            case "firstNumber" -> {
                speechText = unknown + operatorText + secondNumber + getString(R.string.equals) + result;
                questionUiText = getString(R.string.questionNumber);
            }
            case "secondNumber" -> {
                speechText = firstNumber + operatorText + unknown + getString(R.string.equals) + result;
                questionUiText = getString(R.string.questionNumber);
            }
        }


    }

}