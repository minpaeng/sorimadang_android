package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.ui.Instrument.InstrumentFragment;

public class InstStudyActivity extends AppCompatActivity {

    TextView ghtWhat, akgi;
    int whatAkgi; //1이면 관악기, 2이면 현악기, 3이면 타악기
    String akgiName; //악기이름

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst_study);

        ghtWhat = findViewById(R.id.instStudyTextView2); //악기종류
        akgi = findViewById(R.id.instStudyTextView1); //악기이름

        Intent intent = getIntent();
        whatAkgi = intent.getIntExtra("whatAkgi",0);
        akgiName = intent.getStringExtra("akgi");
        ghtWhat.setText(whatAkgi);
        akgi.setText(akgiName);
        
        //악기종류랑 악기이름 잘 넘겨주는지 확인
        //클래스에서 악기 찾아서 그림 표시, 소리표시
        
    }

    public void InstbackActivity(View view) {
        startActivity(new Intent(InstStudyActivity.this, InstrumentFragment.class));
    }
}