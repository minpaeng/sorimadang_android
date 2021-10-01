package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class InstrumentQuizIntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_quiz_intro);
        getSupportActionBar().hide();

    }

    public void instStage0Activity(View view) {
        Intent intent = new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class);
        intent.putExtra("stage",1);
        startActivity(intent);
    }
    public void instStage1Activity(View view) {
        Intent intent = new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class);
        intent.putExtra("stage",1);
        startActivity(intent);    }
    public void instStage2Activity(View view) {
        Intent intent = new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class);
        intent.putExtra("stage",2);
        startActivity(intent);    }
    public void instStage3Activity(View view) {
        Intent intent = new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class);
        intent.putExtra("stage",3);
        startActivity(intent);
    }

}