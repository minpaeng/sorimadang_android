package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.ui.Instrument.InstrumentFragment;

public class TaInstStudyIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ta_inst_study_intro);
    }

    public void T_instBackActivity(View view) {
        startActivity(new Intent(TaInstStudyIntro.this, InstrumentFragment.class));
    }
}