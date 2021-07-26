package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnFocusChangeListener;

public class SignUpActivity extends AppCompatActivity {

    EditText idInput, pwInput, pwCheckInput;
    TextView idNotice,pwNotice,pwCheckNotice;
    Button signUpBT;
    String USERID, USERPW, USERPWCHECK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        idInput =findViewById(R.id.SignUpIDeditText);
        pwInput=findViewById(R.id.SignUpPWeditText);
        pwCheckInput=findViewById(R.id.PWCheckeditText);

        idNotice=findViewById(R.id.SignUpIDNotice);
        pwNotice=findViewById(R.id.SignUpPWNotice);
        pwCheckNotice=findViewById(R.id.PWCheckNotice);

        /*
         *1. editText 글자수 최소,최대 제한
         *아이디, 비밀번호 editText의 경우,
         *영문과 숫자를 합쳐서 6자 이상, 15자 이하가 되도록 한다.
         *
         * ???영어나 숫자가 있는지 확인해야하는지 체크??
         *
         *activity_sign_up.xml에서 maxlength를 15자로 함.
         *
         * 비밀번호 확인 editText의 경우,
         * 비밀번호 editText와 일치하는지 확인해준다.
         *
         */

        idInput.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) { //포커스가 editText에서 다른 곳으로 바뀔때
                    if(idInput.length() < 6 || idInput.length() > 15) { //아이디가 6자 미만일때나 15자 초과일때 (오류!)
                        idNotice.setTextColor(Color.parseColor("#FF0000"));
                        idNotice.setText("아이디 형식이 올바르지 않습니다.");
                    }
                    else if(idInput.length() >= 6 && idInput.length() <= 15){ //아이디 형식이 올바를 때
                        idNotice.setTextColor(Color.parseColor("#0038FF"));
                        idNotice.setText("올바른 아이디 형식입니다.");
                        USERID = idInput.getText().toString();
                    }
                }
            }
        });

        pwInput.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){ //포커스가 editText에서 다른 곳으로 바뀔 때
                    if(pwInput.length() < 6 || pwInput.length() > 15) { //비밀번호가 6자 미만일때나 15자 초과일때 (오류!)
                        pwNotice.setTextColor(Color.parseColor("#FF0000"));
                        pwNotice.setText("비밀번호 형식이 올바르지 않습니다.");
                    }
                    else if(pwInput.length() >= 6 && pwInput.length() <= 15){ //비밀번호 형식이 올바를 때
                        pwNotice.setTextColor(Color.parseColor("#0038FF"));
                        pwNotice.setText("올바른 비밀번호 형식입니다.");
                        USERPW = pwInput.getText().toString();
                    }
                }
            }
        });

        pwCheckInput.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                USERPWCHECK = pwCheckInput.getText().toString();
                if(!hasFocus){ //포커스가 editText에서 다른 곳으로 바뀔 때
                    if(USERPW.equals(USERPWCHECK)) { //비밀번호 불일치
                        pwCheckNotice.setTextColor(Color.parseColor("#FF0000"));
                        pwCheckNotice.setText("비밀번호가 틀립니다.");
                    }
                    else { //비밀번호 일치
                        pwCheckNotice.setTextColor(Color.parseColor("#0038FF"));
                        pwCheckNotice.setText("비밀번호가 일치합니다.");
                    }
                }
            }
        });

        /*
         * 2. 버튼 누르면 edittext의 값 가져옴.=>USERXX로 저장한 값.
         *  사용자 아이디, 패스워드를 서버와 연결해서 보내주기
         * */

        signUpBT = findViewById(R.id.SignUpbutton);
        signUpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //USERID,USERPW를 서버에 보내주기.
            }
        });


    }



}