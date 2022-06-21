package com.example.multiscreen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class TtsActivity extends AppCompatActivity {
    public static final Integer RecordAudioRequestCode = 1;

    TextToSpeech tts;
    EditText editText;
    Button btn_speak;
    ImageView img_mic;
    SpeechRecognizer speechRecognizer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);

        //implement text to speech

        btn_speak = findViewById(R.id.btn_speak);
        img_mic = findViewById(R.id.img_mic);
        editText = findViewById(R.id.edit_text);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            checkPermission();
        }

        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts = new TextToSpeech(TtsActivity.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = tts.setLanguage(new Locale("en", "US"));
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                Toast.makeText(TtsActivity.this, "This language is not supported", Toast.LENGTH_SHORT).show();
                            } else {
                                editText = findViewById(R.id.edit_text);
                                speak();
                            }
                        } else {
                            Toast.makeText(TtsActivity.this, "Text to speech failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




        //setup speech recognizer
        img_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
                try {
                    startActivityForResult(intent, RecordAudioRequestCode);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry! Speech recognition is not supported in this device.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RecordAudioRequestCode) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                editText.setText(
                        Objects.requireNonNull(result).get(0));
            }
        }
    }

    private void checkPermission() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(this,new String[] {
                    Manifest.permission.RECORD_AUDIO}, RecordAudioRequestCode);
            };
    }



    private void speak() {
        tts.speak(editText.getText().toString(),TextToSpeech.QUEUE_FLUSH, null);

    }

    @Override
    protected void onDestroy() {
        if(tts!=null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}