package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OXquizIntroActivity extends AppCompatActivity {

    Button oxStage1, oxStage2, oxStage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        oxStage1 = findViewById(R.id.stage1BT);
        oxStage2 = findViewById(R.id.stage2BT);
        oxStage3 = findViewById(R.id.stage3BT);
        View.OnClickListener oxClick;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_intro);

        oxClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.stage1BT:
                        startActivity(new Intent(OXquizIntroActivity.this, OXquizStageActivity.class));
                        break;
                    case R.id.stage2BT:
                        startActivity(new Intent(OXquizIntroActivity.this, OXquizStageActivity.class));
                        break;
                    case R.id.stage3BT:
                        startActivity(new Intent(OXquizIntroActivity.this, OXquizStageActivity.class));
                        break;
                }
            }
        };

        oxStage1.setOnClickListener(oxClick);
        oxStage2.setOnClickListener(oxClick);
        oxStage3.setOnClickListener(oxClick);

    }


}