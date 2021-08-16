package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        getSupportActionBar().hide();
        Handler hand2 = new Handler();

        hand2.postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                startActivity(new Intent(IntroActivity.this,
                        InstrumentQuizStageActivity.class));

                finish();

            }
        }, 2000);
    }
}