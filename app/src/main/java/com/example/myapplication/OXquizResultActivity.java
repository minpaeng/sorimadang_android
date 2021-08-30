package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OXquizResultActivity extends AppCompatActivity {

    int total_score,correct_number,wrong_number, stagenumber;
    TextView totScore;
    TextView correctNum;
    TextView wrongNum;

    TextView OXresultstage;
    ImageView OXresultback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_result);
        getSupportActionBar().hide();

        Intent getIntent = getIntent();
        total_score = getIntent.getIntExtra("score",0);
        stagenumber = getIntent.getIntExtra("stagenumber",0);

        OXresultstage = findViewById(R.id.oxStage);
        OXresultstage.setText("stage "+stagenumber);

        totScore = findViewById(R.id.tot_score);
        correctNum = findViewById(R.id.correct_num);
        wrongNum = findViewById(R.id.wrong_num);

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

    }


}