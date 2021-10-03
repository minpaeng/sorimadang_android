package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxwrong_answer);

        //idtoken가져오기(로그인 했을 경우)
        userIdToken = ( (UserIdApplication) getApplication() ).getId();
        Log.v("유저의 idtoken_result",userIdToken);

/*
        //idtoken 가져오기 실패 시 로그인하라는 경고 띄우기
        try {

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("실패 apiString", "실패");
        }
*/
        //앱 시작하자마자 http통신: 오답노트 가져오기
        new Thread(){
            @Override
            public void run() {
                try {
                    Action action=Action.getInstance();
                    JSONObject reqtoServer=new JSONObject();
                    reqtoServer.put("idToken",userIdToken);
                    String res= action.post(reqtoServer.toString(),"http://sorimadang.shop/api/ox-game/wrong-questions/search");
                    Log.v("result", res);

                    // 가장 큰 JSONObject를 가져옵니다.
                    JSONObject jObject = new JSONObject(res);
                    // 배열을 가져옵니다.
                    jArray = jObject.getJSONArray("wrongQuizs");

                    Log.v("오답 읽기 성공", jArray.getJSONObject(0).getJSONObject("gameOXQuiz").getString("quiz"));
                    Log.v("오답 읽기 성공", jArray.getJSONObject(1).getJSONObject("gameOXQuiz").getString("quiz"));
                    Log.v("오답 읽기 성공", jArray.getJSONObject(2).getJSONObject("gameOXQuiz").getString("quiz"));
                    Log.v("array 길이", Integer.toString(jArray.length()));

                    if(res != null){
                        Log.v("o 오답 apiString", res);//.toString());
                    }
                    else Log.v("o 오답 apiString", "null");

                } catch (JSONException e){
                    //에러
                    e.printStackTrace();
                    Log.v("오답 apiString", "실패");
                }
            }
        }.start();
        int a = 1;
        int b = 2;
    }

    @Override
    public void onResume() {
        super.onResume();
        //http통신 완료 후 json 파싱 작업 실행
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int c = 3;
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

    public void initializeData(JSONArray jArray) throws JSONException {
        oxWrongQuizs = new ArrayList<>();

        int len = jArray.length();
        for(int i=0;i< len;i++){
            //Log.v("반복문 확인", String.valueOf(i));
            JSONObject jObject = jArray.getJSONObject(i).getJSONObject("gameOXQuiz");
            int stageNum = jObject.getInt("stage_num");
            Log.v("성공 stageNum", Integer.toString(stageNum));
            int quizNum = jObject.getInt("quiz_num");
            Log.v("성공 quiz_num", Integer.toString(quizNum));
            String quiz = jObject.getString("quiz");
            Log.v("성공 quiz", quiz);
            int answer = jObject.getInt("answer");
            Log.v("성공 answer", Integer.toString(answer));
            oxWrongQuizs.add(new OXWrongQuiz(stageNum, quizNum, quiz, answer));
            System.out.println(oxWrongQuizs);
        }
    }

}