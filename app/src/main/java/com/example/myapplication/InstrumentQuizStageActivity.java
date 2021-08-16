package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class InstrumentQuizStageActivity extends AppCompatActivity {

    final int PERMISSION = 1;
    SpeechRecognizer mRecognizer;
    Intent intent;

    TextView what_name;
    Button speech_button;
    TextView stt_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_quiz_stage);
        getSupportActionBar().hide();
        if ( Build.VERSION.SDK_INT >= 23 ){
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO},PERMISSION);
        }
        what_name = findViewById(R.id.what_name_text);
        speech_button = findViewById(R.id.speech_button);
        stt_view = findViewById(R.id.stt_view);

        
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");





    }

    public void STTButton(View view) {
            mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mRecognizer.setRecognitionListener(listener);
            mRecognizer.startListening(intent);

        }

    private RecognitionListener listener = new RecognitionListener() {

        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(),"음성인식을 시작합니다.",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {
           what_name.setVisibility(View.INVISIBLE);
        }

        @Override
         public void onError(int error) {
             Toast.makeText(getApplicationContext(), "에러가 발생하였습니다. " ,Toast.LENGTH_SHORT).show();

         }

         @Override
         public void onResults(Bundle results) {
             ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
             for(int i = 0; i < matches.size() ; i++){
                 stt_view.setText(matches.get(i));
             }
         }


         @Override
         public void onPartialResults(Bundle partialResults) {

         }

         @Override
         public void onEvent(int eventType, Bundle params) {

         }
       };
}









