package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OXquizStageActivity extends AppCompatActivity {

    Button OXback,oBT,xBT;
    TextView OXstage,OXstep,OXtime,OXscore, OXquiz, OXpopScore;
    ImageView rightOimg,rightXimg,wrongOimg,wrongXimg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        OXback = findViewById(R.id.backBT);
        oBT = findViewById(R.id.oButton);
        xBT = findViewById(R.id.xButton);

        OXstage = findViewById(R.id.oxStage);
        OXstep = findViewById(R.id.oxQuizStep);
        OXtime = findViewById(R.id.oxTime);
        OXscore = findViewById(R.id.oxScore);
        OXquiz = findViewById(R.id.oxQuiz);
        OXpopScore = findViewById(R.id.oxPopScore);

        rightOimg = findViewById(R.id.rightOimg);
        rightXimg = findViewById(R.id.rigntXimg);
        wrongOimg = findViewById(R.id.wrongOimg);
        wrongXimg = findViewById(R.id.wrongXimg);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_stage);
    }

    //정답이랑 틀린거 o,x뜨는건 그냥 이미지뷰로 해서 뜨게 하는게 좋을 것 같음!!
}