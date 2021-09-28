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
        startActivity(new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class));
    }
    public void instStage1Activity(View view) {
        startActivity(new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class));
    }
    public void instStage2Activity(View view) {
        startActivity(new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class));
    }
    public void instStage3Activity(View view) {
        startActivity(new Intent(InstrumentQuizIntroActivity.this, InstrumentQuizStageActivity.class));
    }

}