package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OXquizResultActivity extends AppCompatActivity {

    int total_score,correct_number,wrong_number, stagenumber;
    TextView totScore;
    TextView correctNum;
    TextView wrongNum;

    Button next_level_BT;
    Button wrong_answer_BT;

    TextView OXresultstage;
    ImageView OXresultback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_result);
        getSupportActionBar().hide();

        Intent getIntent = getIntent();
        total_score = getIntent.getIntExtra("score",0);
        stagenumber = getIntent.getIntExtra("stageNumber",0);

        OXresultstage = findViewById(R.id.oxStage);
        OXresultstage.setText("stage "+stagenumber);

        totScore = findViewById(R.id.tot_score);
        correctNum = findViewById(R.id.correct_num);
        wrongNum = findViewById(R.id.wrong_num);

        wrong_answer_BT = findViewById(R.id.button3);

        next_level_BT = findViewById(R.id.nextLevel_button);
        totScore.setText(String.valueOf(total_score));

        if(total_score != 0){
            correct_number = total_score/10;
            wrong_number = 5 - correct_number;
        }
        else {
            correct_number = 0;
            wrong_number = 5;
        }
        correctNum.setText(String.valueOf(correct_number));
        wrongNum.setText(String.valueOf(wrong_number));

        OXresultback = findViewById(R.id.imageView6);
        OXresultback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OXquizResultActivity.this, OXquizIntroActivity.class));
            }
        });


        if(stagenumber == 1 || stagenumber == 2){
            stagenumber +=1;
        }
        else{
            next_level_BT.setText("처음단계\n다시하기");
            stagenumber = 1;
        }

        next_level_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent stageIntent = new Intent(OXquizResultActivity.this, OXquizStageActivity.class);
                stageIntent.putExtra("stageNumber",stagenumber);
                startActivity(stageIntent);

            }
        });

        wrong_answer_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //오답노트 화면
                Intent stageIntent = new Intent(OXquizResultActivity.this, OXwrongAnswerActivity.class);
                startActivity(stageIntent);
           }
        });


    }


}