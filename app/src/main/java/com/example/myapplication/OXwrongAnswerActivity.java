package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OXwrongAnswerActivity extends AppCompatActivity {
    private ArrayList<OXWrongQuiz> oxWrongQuizs;
    static String userIdToken = null;
    private JSONArray jArray = null;
    private Thread getWrongQuizs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("오답노트");
        setContentView(R.layout.activity_oxwrong_answer);

        //idtoken가져오기(로그인 했을 경우)
        userIdToken = ( (UserIdApplication) getApplication() ).getId();
        Log.v("유저의 idtoken_result",userIdToken);


        //idtoken 가져오기 실패 시(로그인 없이 서비스 이용 시도, 일정 시간 경과로 인한 idToken 만료) 로그인하라는 경고 띄우기 구현 필요


        //오답노트 가져오기
        getWrongQuizs = new Thread(){
            @Override
            public void run() {
                jArray = getNotes();
            }
        };
        getWrongQuizs.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        //getWrongQuizs 스레드 종료까지 대기
        try {
            getWrongQuizs.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //오답노트 화면 그리기
        try {
            initializeData(jArray);
            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(manager); // LayoutManager 등록
            recyclerView.setAdapter(new OXWrongQuizAdapter(oxWrongQuizs));  // Adapter 등록
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //오답노트 데이터 준비(JSON 파싱)
    public void initializeData(JSONArray jArray) throws JSONException {
        oxWrongQuizs = new ArrayList<>();

        int len = jArray.length();
        for(int i=0;i< len;i++){
            JSONObject jObject = jArray.getJSONObject(i).getJSONObject("gameOXQuiz");
            int stageNum = jObject.getInt("stage_num");
            int quizNum = jObject.getInt("quiz_num");
            String quiz = jObject.getString("quiz");
            int answer = jObject.getInt("answer");
            oxWrongQuizs.add(new OXWrongQuiz(stageNum, quizNum, quiz, answer));
        }
    }

    private JSONArray getNotes() {
        try {
            Action action=Action.getInstance();
            JSONObject reqtoServer=new JSONObject();
            reqtoServer.put("idToken",userIdToken);
            String res= action.post(reqtoServer.toString(),"http://sorimadang.shop/api/ox-game/wrong-questions/search");
            Log.v("result", res);
            if(res != null){
                Log.v("o 오답 apiString", res);//.toString());
            }
            else Log.v("o 오답 apiString", "null");

            // 가장 큰 JSONObject 가져오기
            JSONObject jObject = new JSONObject(res);
            // 배열 가져오기
            jArray = jObject.getJSONArray("wrongQuizs");

            return jArray;

        } catch (JSONException e){
            //에러
            e.printStackTrace();
            Log.v("오답 apiString", "실패");
            return null;
        }
    }
}