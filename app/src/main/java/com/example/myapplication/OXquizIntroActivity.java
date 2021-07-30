package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OXquizIntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_intro);


    }


    public void startStage1Activity(View view) {
        startActivity(new Intent(OXquizIntroActivity.this, OXquizStageActivity.class));
    }
    public void startStage2Activity(View view) {
        startActivity(new Intent(OXquizIntroActivity.this, OXquizStageActivity.class));
    }
    public void startStage3Activity(View view) {
        startActivity(new Intent(OXquizIntroActivity.this, OXquizStageActivity.class));
    }
}