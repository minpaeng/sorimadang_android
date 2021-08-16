package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ui.home.HomeFragment;

public class OXquizIntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_intro);


    }


    public void startStage1Activity(View view) {
        Intent intent1 = new Intent(OXquizIntroActivity.this, OXquizStageActivity.class);
        intent1.putExtra("stageNumber",1);
        startActivity(intent1);
    }
    public void startStage2Activity(View view) {
        Intent intent2 = new Intent(OXquizIntroActivity.this, OXquizStageActivity.class);
        intent2.putExtra("stageNumber",2);
        startActivity(intent2);
    }
    public void startStage3Activity(View view) {
        Intent intent3 = new Intent(OXquizIntroActivity.this, OXquizStageActivity.class);
        intent3.putExtra("stageNumber",3);
        startActivity(intent3);
    }
    public void oxintrobackActivity(View view){
        startActivity(new Intent(OXquizIntroActivity.this, HomeFragment.class));
    }
}