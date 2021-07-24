package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class NameSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_select);

        getSupportActionBar().hide();

    }

    public void startMainActiviy(View view) {

        startActivity(new Intent(NameSelect.this, IntroLastActivity.class));

    }
}