package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class OXwrongAnswerActivity extends AppCompatActivity {

    static String userIdToken=null;
    TextView wrongAnswer;
    String oxapiString=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxwrong_answer);

        //idtoken가져오기(로그인 했을 경우)
        userIdToken = ( (UserIdApplication) getApplication() ).getId();
        Log.v("유저의 idtoken_result",userIdToken);

        wrongAnswer=findViewById(R.id.textView);

        try {

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("실패 apiString", "실패");
        }



        //idtoken보내고 응답받아서 textview에 넘겨주기
        //wrongAnswer.setText(#응답#);
    }



}