package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText idLoginInput, pwLoginInput;
    Button loginBT;
    String LOGINID,LOGINPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idLoginInput = findViewById(R.id.LoginIDeditText);
        pwLoginInput = findViewById(R.id.LoginPWeditText);
        loginBT = findViewById(R.id.Loginbutton);

        /*
        * 1. editText에 적힌 사용자 정보를 읽어옴.
        * */
        idLoginInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    LOGINID = idLoginInput.getText().toString();
                }
            }
        });

        pwLoginInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    LOGINPW = pwLoginInput.getText().toString();
                }
            }
        });


        /*
        * 2.버튼 누르면 서버에서 가져온 아이디,비번과 비교.
        * */

        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //서버에서 아이디,비번가져오기.
                //LOGINID,LOGINPW와 비교하기


                startActivity(new Intent(LoginActivity.this, IntroActivity.class));

            }
        });

        /*(선택)자동로그인
        *
        * */
    }
}