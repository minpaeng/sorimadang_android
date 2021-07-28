package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OXquizStageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_stage);
    }

    //정답이랑 틀린거 o,x뜨는건 그냥 이미지뷰로 해서 뜨게 하는게 좋을 것 같음!!
}