package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OXquizStageActivity extends AppCompatActivity {

    Button oBT,xBT;
    TextView OXstage,OXstep,OXtime,OXscore, OXquiz, OXpopScore;
    ImageView rightOimg,rightXimg,wrongOimg,wrongXimg, OXback;
    LottieAnimationView true_animation, false_animation;
    String apiString = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        OXback = findViewById(R.id.imageView8);
        oBT = findViewById(R.id.oButton);
        xBT = findViewById(R.id.xButton);

        OXstage = findViewById(R.id.oxStage);
        OXstep = findViewById(R.id.oxQuizStep);
        OXtime = findViewById(R.id.oxTime);
        OXscore = findViewById(R.id.oxScore);
        OXquiz = findViewById(R.id.oxQuiz);
        OXpopScore = findViewById(R.id.oxPopScore);

        rightOimg = findViewById(R.id.rightOimg);
        rightXimg = findViewById(R.id.rigntXimg);
        wrongOimg = findViewById(R.id.wrongOimg);
        wrongXimg = findViewById(R.id.wrongXimg);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxquiz_stage);

        //rightOimg.setVisibility(View.VISIBLE); //화면에서 보이게 하는거->정답일때 보이게 할 수 있음

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

        some_method_in_ui_thread();
        }

    // Calling the rest api in the UI thread
    protected void some_method_in_ui_thread() {

        new RestAPITask("http://sorimadang.shop/api/ox-game/questions").execute();
    }

    // Rest API calling task
    public static class RestAPITask extends AsyncTask<Integer, Void, Void> {
        // Variable to store url
        protected String mURL;

        // Constructor
        public RestAPITask(String url) {
            mURL = url;
        }

        // Background work
        protected Void doInBackground(Integer... params) {
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
                Log.v("성공: ",result);
            }
            catch (Exception e) {
                // Error calling the rest api
                Log.e("REST_API", "GET method failed: " + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }
}
