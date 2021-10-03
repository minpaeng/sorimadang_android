package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OXquizIntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_intro);
        getSupportActionBar().hide();

        new Thread(){
            @Override
            public void run() {
                Action action=Action.getInstance();
                String apiString= action.get(null,"http://sorimadang.shop/api/ox-game/questions");

                oxQuizApi.getQuiz(apiString);

                if(apiString != null){
                    Log.v("성공 퀴즈 apiString", apiString);//.toString());
                }
                else
                    Log.v("성공 퀴즈 apiString", "null");
            }
        }.start();


    }


    public void startStage1Activity(View view) {
        Intent intent1 = new Intent(OXquizIntroActivity.this, OXquizStageActivity.class);
        intent1.putExtra("stageNumber",1);
        startActivity(intent1);
    }
    public void startStage2Activity(View view) {
        Intent intent2 = new Intent(OXquizIntroActivity.this, OXquizStageActivity.class);
        intent2.putExtra("stageNumber",2);
        startActivity(intent2);
    }
    public void startStage3Activity(View view) {
        Intent intent3 = new Intent(OXquizIntroActivity.this, OXquizStageActivity.class);
        intent3.putExtra("stageNumber",3);
        startActivity(intent3);
    }
    public void oxintrobackActivity(View view){
        startActivity(new Intent(OXquizIntroActivity.this, MainActivity.class));
    }

    public static class oxQuizApi{
        //ox퀴즈 내용 서버에서 가져온 후 활용하는 클래스
        public static int[][] oxQuizNum = new int[3][5];//퀴즈 넘버
        public static String [][] oxQuiz = new String[3][5]; //퀴즈 내용
        public static int [][] oxQuizAnswer = new int[3][5]; //퀴즈 정답
        static int stage;

        //1.퀴즈 내용 저장하는 함수
        public static void getQuiz(String api){

            Log.v("getQuiz",api);

            try {
                JSONArray jarray = new JSONArray(api);

                int j=0;
                for (int i = 0; i < jarray.length(); i++) { //15번

                    Log.v("반복문 확인", String.valueOf(i));
                    JSONObject jObject = jarray.getJSONObject(i);

                    stage = jObject.getInt("stage_num");
                    Log.v("stage 확인", String.valueOf(stage));//1~3

                    if(stage==1){
                        oxQuizNum[0][i] = jObject.getInt("quiz_num");
                        oxQuiz[0][i] = jObject.getString("quiz");
                        oxQuizAnswer[0][i] = jObject.getInt("answer");
                        Log.v("성공 gq/ stageNum:",String.valueOf(stage));
                        Log.v("성공 gq/ stage 퀴즈:", oxQuizNum[0][i] + " " + oxQuiz[0][i] + oxQuizAnswer[0][i]);
                        if(i==4){
                            j+=5;
                        }
                    }else if(stage==2){
                        oxQuizNum[1][i-j] = jObject.getInt("quiz_num");
                        oxQuiz[1][i-j] = jObject.getString("quiz");
                        oxQuizAnswer[1][i-j] = jObject.getInt("answer");
                        Log.v("성공 gq/ stageNum:",String.valueOf(stage));
                        Log.v("성공 gq/ stage 퀴즈:", oxQuizNum[1][i-j] + " " + oxQuiz[1][i-j] + oxQuizAnswer[1][i-j]);
                        if(i==9){
                            j+=5;
                        }
                    }else{
                        oxQuizNum[2][i-j] = jObject.getInt("quiz_num");
                        oxQuiz[2][i-j] = jObject.getString("quiz");
                        oxQuizAnswer[2][i-j] = jObject.getInt("answer");
                        Log.v("성공 gq/ stageNum:",String.valueOf(stage));
                        Log.v("성공 gq/ stage 퀴즈:", oxQuizNum[2][i-j] + " " + oxQuiz[2][i-j] + oxQuizAnswer[2][i-j]);
                        if(j==14){
                            j=0;
                        }
                    }
                }

            }catch(Exception e){
                //에러
                e.printStackTrace();
                Log.v("실패 gq", "실패");
            }

        }
        //2.퀴즈 내용 출력하는 함수

    }
}