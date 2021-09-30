package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class GwanInstStudyIntro extends AppCompatActivity {

    ImageButton imgbt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gwan_inst_study_intro);

        imgbt1 = findViewById(R.id.imageButton2);

    }
}