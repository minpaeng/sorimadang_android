package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
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

    Button oBT,xBT,nextBT;
    static TextView OXstage,OXstep,OXtime,OXscore, OXquiz, OXpopScore;
    ImageView OXback;
    LottieAnimationView true_animation, false_animation;
    String userIdToken = null;
    int stage;
    int oxscore = 0;
    int num=10;
    private TimerTask mTimerTask;
    static int userquizNum;
    static int[] quizNum = new int[5];
    int stageNum;
    static String[] quiz = new String[5];
    static int[] answer = new int[5];
    private final Timer mTimer = new Timer();
    private final android.os.Handler handler = new android.os.Handler();
    boolean isTrueLottie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //뷰의 주소값을 가져오려면 항상 setContentView(R.layout.activity_main); 호출이 끝난 이 후 작업을 해야합니다.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_stage);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        stageNum = intent.getIntExtra("stageNumber",0);
        userquizNum = 0;
        Log.v("stageNum 확인", String.valueOf(stageNum));

        OXback = findViewById(R.id.imageView8);
        oBT = findViewById(R.id.oButton);
        xBT = findViewById(R.id.xButton);
        nextBT = findViewById(R.id.next_button);
        OXstage = findViewById(R.id.oxStage); //스테이지단계
        OXstep = findViewById(R.id.oxQuizStep); //퀴즈 단계
        OXtime = findViewById(R.id.oxTime); //제한시간
        OXscore = findViewById(R.id.oxScore); //점수
        OXquiz = findViewById(R.id.oxQuiz); //퀴즈내용
        OXpopScore = findViewById(R.id.oxPopScore); //맞추면 점수..?

        isTrueLottie = false;

        //idtoken가져오기(로그인 했을 경우)
        userIdToken = ( (UserIdApplication) getApplication() ).getId();
        Log.v("유저의 idtoken_stage",userIdToken);


        //1.stagenum 스테이지 표시해주기
        OXstage.setText("stage "+stageNum);
        OXscore.setText("score "+oxscore);

        //2.퀴즈 넘버링 + 3.퀴즈 내용 가져오기.
        try {

            Action action=Action.getInstance();
            String apiString= action.post(null,"http://sorimadang.shop/api/ox-game/questions");

            //String apiString = new RestAPITask("http://sorimadang.shop/api/ox-game/questions").execute().get();
            Log.v("f 성공 apiString", apiString);
            JSONArray jarray=  new JSONArray(apiString);

            int j=0;
            for(int i=0;i<jarray.length();i++){
                //Log.v("반복문 확인", String.valueOf(i));
                JSONObject jObject = jarray.getJSONObject(i);
                stage = jObject.getInt("stage_num");
                if(stage==stageNum){
                    quizNum[j] = jObject.getInt("quiz_num");
                    quiz[j] = jObject.getString("quiz");
                    answer[j] = jObject.getInt("answer");
                    Log.v("성공 apiString jjj",String.valueOf(j));
                    Log.v("성공 apiString/ stageNum:",String.valueOf(stageNum));
                    Log.v("성공 apiString/ stage 퀴즈:", quizNum[j] + " " + quiz[j] + answer[j]);
                    Log.v("성공 stage 퀴즈2:", String.valueOf(j));
                    j++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.v("실패 apiString", "실패");
        }
        /*

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

        OXstep.setText("Quiz "+(userquizNum+1));
        OXquiz.setText(quiz[userquizNum]);

        //로티 리스너
        /*true_animation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //true_animation.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        false_animation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                //false_animation.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

*/





        oBT.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimerTask.cancel();
                oBT.setEnabled(false);
                xBT.setEnabled(false);
                //o가 정답일 떄,
                if(answer[userquizNum]==1){
                true_animation = findViewById(R.id.lottie_true);
                true_animation.setAnimation("tickgreen.json");
                true_animation.setVisibility(View.VISIBLE);
                true_animation.playAnimation();
                true_animation.setRepeatCount(1);

                oxscore += 10;
                OXscore.setText("score "+oxscore);
                isTrueLottie = true;
                }

                //o가 오답일 때,
                else if(answer[userquizNum]==0) {

                    false_animation = findViewById(R.id.lottie_false);
                    false_animation.setAnimation("signforerrorflatstyle.json");
                    false_animation.setVisibility(View.VISIBLE);
                    false_animation.playAnimation();
                    //false_animation.setScrollBarFadeDuration(1000);
                    false_animation.setRepeatCount(1);

                    isTrueLottie = false;
                }
                //userquizNum++;
                nextBT.setVisibility(View.VISIBLE);


            }
        });

        xBT.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                mTimerTask.cancel();
                oBT.setEnabled(false);
                xBT.setEnabled(false);
                //x가 정답일 때,
                if(answer[userquizNum]==0){

                    true_animation = findViewById(R.id.lottie_true);
                    true_animation.setAnimation("tickgreen.json");
                    true_animation.setVisibility(View.VISIBLE);
                    true_animation.playAnimation();
                    true_animation.setRepeatCount(1);

                    oxscore += 10;
                    OXscore.setText("score "+oxscore);
                    isTrueLottie = true;
                }

                //X가 오답일 때,
                else if(answer[userquizNum]==1) {

                    false_animation = findViewById(R.id.lottie_false);
                    false_animation.setAnimation("signforerrorflatstyle.json");
                    false_animation.setVisibility(View.VISIBLE);
                    false_animation.playAnimation();
                    false_animation.setRepeatCount(1);

                    isTrueLottie = false;
                }
                nextBT.setVisibility(View.VISIBLE);

            }

        });

        nextBT.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(isTrueLottie == true){
                    true_animation.setVisibility(View.GONE);
                }
                else if(isTrueLottie == false){
                    false_animation.setVisibility(View.GONE);
                }
                else{

                }

                userquizNum++;

                if(userquizNum>4){
                    //startActivity(new Intent(OXquizStageActivity.this, OXquizResultActivity.class));
                    Intent resultIntent = new Intent(OXquizStageActivity.this, OXquizResultActivity.class);
                    resultIntent.putExtra("score",oxscore);
                    resultIntent.putExtra("stageNumber",stageNum);
                    startActivity(resultIntent);
                }
                else{
                    OXstep.setText("Quiz "+(userquizNum+1));
                    OXquiz.setText(quiz[userquizNum]);
                    mTimerTask = createTimerTask();
                    mTimer.schedule(mTimerTask, 0, 1000);
                }
                nextBT.setVisibility(View.INVISIBLE);
                oBT.setEnabled(true);
                xBT.setEnabled(true);
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
                //Log.v("update반복 count",String.valueOf(count));
            }
        };
        handler.post(runnable);
    }

        //rightOimg.setVisibility(View.VISIBLE); //화면에서 보이게 하는거->정답일때 보이게 할 수 있음




    void timeOver(){
        nextBT.setVisibility(View.VISIBLE);
        mTimerTask.cancel();
        oBT.setEnabled(false);
        xBT.setEnabled(false);
        false_animation = findViewById(R.id.lottie_false);
        false_animation.setAnimation("signforerrorflatstyle.json");
        false_animation.setVisibility(View.VISIBLE);
        false_animation.playAnimation();
        false_animation.setRepeatCount(1);
        isTrueLottie = false;
    }

    private TimerTask createTimerTask() {
        /*if(userquizNum>6){
            startActivity(new Intent(OXquizStageActivity.this, OXquizResultActivity.class));
        }*/

        /*OXstep.setText("Quiz "+(userquizNum+1));
        OXquiz.setText(quiz[userquizNum]);
        Log.v("퀴즈넘 0",String.valueOf(userquizNum));
        Log.v("타이머 0 퀴즈넘",String.valueOf(userquizNum));
        Log.v("타이머 0 퀴즈0",quiz[0]);
        Log.v("타이머 0 퀴즈[퀴즈넘]",quiz[userquizNum]);*/

        //userquizNum++;
        num = 10;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                update();
            }
        };

        return timerTask;
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
