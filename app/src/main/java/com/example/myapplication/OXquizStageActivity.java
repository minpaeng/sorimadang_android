package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OXquizStageActivity extends AppCompatActivity {

    Button oBT,xBT;
    static TextView OXstage,OXstep,OXtime,OXscore, OXquiz, OXpopScore;
    ImageView rightOimg,rightXimg,wrongOimg,wrongXimg, OXback;
    LottieAnimationView true_animation, false_animation;
    String apiString = null;
    int stage;
    int counting = 10;
    static int count=1;
    int num=10;
    private Timer timer;
    private TimerTask mTimerTask;
    static int[] quizNum = new int[5];
    static int stageNum;
    static String[] quiz = new String[5];
    static int[] answer = new int[5];
    private final Timer mTimer = new Timer();
    private final android.os.Handler handler = new android.os.Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //뷰의 주소값을 가져오려면 항상 setContentView(R.layout.activity_main); 호출이 끝난 이 후 작업을 해야합니다.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_stage);

        Intent intent = getIntent();
        stageNum = intent.getIntExtra("stageNumber",0);
        Log.v("stageNum 확인", String.valueOf(stageNum));

        OXback = findViewById(R.id.imageView8);
        oBT = findViewById(R.id.oButton);
        xBT = findViewById(R.id.xButton);

        OXstage = findViewById(R.id.oxStage); //스테이지단계
        OXstep = findViewById(R.id.oxQuizStep); //퀴즈 단계
        OXtime = findViewById(R.id.oxTime); //제한시간
        OXscore = findViewById(R.id.oxScore); //점수
        OXquiz = findViewById(R.id.oxQuiz); //퀴즈내용
        OXpopScore = findViewById(R.id.oxPopScore); //맞추면 점수..?

        rightOimg = findViewById(R.id.rightOimg);
        rightXimg = findViewById(R.id.rigntXimg);
        wrongOimg = findViewById(R.id.wrongOimg);
        wrongXimg = findViewById(R.id.wrongXimg);

        //1.stagenum 스테이지 표시해주기
        OXstage.setText("Stage "+stageNum);

        //2.퀴즈 넘버링 + 3.퀴즈 내용 가져오기.
        try {
            String apiString = new RestAPITask("http://sorimadang.shop/api/ox-game/questions").execute().get();
            Log.v("성공 apiString", apiString);
            JSONArray jarray=  new JSONArray(apiString);

            for(int i=0;i<jarray.length();i++){
                int j=0;
                //Log.v("반복문 확인", String.valueOf(i));
                JSONObject jObject = jarray.getJSONObject(i);
                stage = jObject.getInt("stageNum");
                //Log.v("반복문 stage 확인", String.valueOf(stage));
                if(stage==stageNum){
                    quizNum[j] = jObject.getInt("quizNum");
                    quiz[j] = jObject.getString("quiz");
                    answer[j] = jObject.getInt("answer");
                    Log.v("성공 apiString/ stageNum:",String.valueOf(stageNum));
                    Log.v("성공 apiString/ stage 퀴즈:",quizNum[j]+" "+quiz[j]+answer[j]);
                    j++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.v("실패 apiString", apiString);
        }

//        for(int i=0;i<5;i++){
//            OXstep.setText("Quiz "+quizNum[i]);
//            OXquiz.setText(quiz[i]);
//            task.run();
//
//
//        }

//        Timer timer=new Timer();
//        TimerTask task=new TimerTask(){
//            @Override
//            public void run() {
//                //TODO Auto-generated method stub
//
//                if(count <= 10){ //count값이 10보다 작거나 같을때까지 수행
//                    Log.v("run","[카운트다운 : "+count+"]");
//                    update();
//                    count++; //실행횟수 증가
//                }
//                else{
//                    timer.cancel(); //타이머 종료
//                    Log.v("run","[카운트다운 : 종료]");
//                }
//            }
//        };
//        timer.schedule(task, 0, 1000); //실행 Task, 0초뒤 실행, 10초마다 반복

        /*CountDownTimer CDT = new CountDownTimer(3000000, 1000){

            @Override
            public void onTick(long millisUntilFinished) { // 총 시간과 주기
                if(num <= 0){
                    OXtime.setText('x');
                }
                else if (num>10){
                    num--;
                    OXtime.setText(String.valueOf(num));
                }
                else {
                    num--;
                    OXtime.setText(String.valueOf(num));
                }


            }

            @Override
            public void onFinish() {

            }
        };
*/
        mTimerTask = createTimerTask();
        mTimer.schedule(mTimerTask,0, 1000);

        oBT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimerTask.cancel();
                true_animation = findViewById(R.id.lottie_true);
                true_animation.setAnimation("tickgreen.json");
                true_animation.playAnimation();
                true_animation.setRepeatCount(3);

            }
        });

        xBT.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                mTimerTask = createTimerTask();
                mTimer.schedule(mTimerTask, 0, 1000);
            }

        });
    }

    private void update(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                OXtime.setText(String.valueOf(num));//OXtime.setText(String.valueOf(num));
                if (num<0){
                    num = 0;
                    OXtime.setText(String.valueOf(num));
                    timeOver();
                }
                num--;
                Log.v("update반복 count",String.valueOf(count));
            }
        };
        handler.post(runnable);
    }






        //4.버튼누르면 정답 판단해서 로티띄우기
        /*
        true_animation = findViewById(R.id.lottie_true);
        true_animation.setAnimation("tickgreen.json");
        true_animation.playAnimation();
        true_animation.setRepeatCount(3);


        false_animation = findViewById(R.id.lottie_false);
        false_animation.setAnimation("signforerrorflatstyle.json");
        false_animation.playAnimation();
        false_animation.setRepeatCount(3);
        */

        //5.10초 타이머
        //6.결과..

        //rightOimg.setVisibility(View.VISIBLE); //화면에서 보이게 하는거->정답일때 보이게 할 수 있음




    void timeOver(){
        mTimerTask.cancel();
        false_animation = findViewById(R.id.lottie_false);
        false_animation.setAnimation("signforerrorflatstyle.json");
        false_animation.playAnimation();
        false_animation.setRepeatCount(3);
    }

    private TimerTask createTimerTask() {
        num = 10;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };
        return timerTask;
    }

    // Calling the rest api in the UI thread
    protected void some_method_in_ui_thread() {

        new RestAPITask("http://sorimadang.shop/api/ox-game/questions").execute();
    }



    // Rest API calling task
    public static class RestAPITask extends AsyncTask<Integer, Void, String> {
        // Variable to store url
        protected String mURL;

        // Constructor
        public RestAPITask(String url) {
            mURL = url;
        }

        // Background work
        protected String doInBackground(Integer... params) {
            String result = null;

            try {
                // Open the connection
                URL url = new URL(mURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStream is = conn.getInputStream();

                // Get the stream
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                // Set the result
                result = builder.toString();
                Log.v("성공doin: ",result);
            }
            catch (Exception e) {
                // Error calling the rest api
                Log.e("REST_API", "GET method failed: " + e.getMessage());
                e.printStackTrace();
            }
            return result; //null;
        }
    }

    public void oxstagebackActivity(View view) {
        startActivity(new Intent(OXquizStageActivity.this, OXquizIntroActivity.class));
    }


}
