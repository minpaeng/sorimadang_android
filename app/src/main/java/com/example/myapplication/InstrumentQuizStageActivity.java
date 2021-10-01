package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class InstrumentQuizStageActivity extends AppCompatActivity {

    final int PERMISSION = 1;
    SpeechRecognizer mRecognizer;
    Intent intent;
    String tag = "LOG IS";
    TextView what_name;
    Button speech_button;
    TextView stt_view;
    ImageView inst_image;
    int inst_id;
    String inst_name = null;
    String img_url = null;
    String sound_url = null;
    TextView blank;
    Bitmap bmp = null;
    int[] instID_arr = new int[35];
    String[] instName_arr = new String[35];
    String[] instIMG_arr = new String[35];
    String[] instSound_arr = new String[35];
    Drawable drawable;
    int curValue;
    boolean isCorrect;
    boolean isNext;
    int prob_num = 1;
    int stage;
    TextView level_text;
    Random random = new Random();
    Button pass_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrument_quiz_stage);
        getSupportActionBar().hide();
        if ( Build.VERSION.SDK_INT >= 23 ){
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET, Manifest.permission.RECORD_AUDIO},PERMISSION);
        }
        what_name = findViewById(R.id.what_name_text);
        speech_button = findViewById(R.id.speech_button);
        stt_view = findViewById(R.id.stt_view);
        inst_image = findViewById(R.id.inst_img);
        blank = findViewById(R.id.blank_gray);
        level_text = findViewById(R.id.textView13);
        pass_bt = findViewById(R.id.pass_button);

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko-KR");

        Intent intent1 = getIntent();
        stage = intent1.getIntExtra("stage",0);

        if(stage == 1) level_text.setText("쉬움");
        if(stage == 2) level_text.setText("보통");
        if(stage == 3) level_text.setText("어려움");

        inst_image.setVisibility(View.INVISIBLE);
        curValue = random.nextInt(30);

        try {
            Log.v(tag, "start");
            new Task().execute().get();
        } catch (InterruptedException e) {
            Log.v(tag, "error1");
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.v(tag, "error2");
            e.printStackTrace();
        }
        final Handler handler = new Handler(){
            public void handleMessage(Message msg){
                // 원래 하려던 동작 (UI변경 작업 등)
                inst_image.setVisibility(View.VISIBLE);
                inst_image.setImageDrawable(drawable);
            }
        };
        try {

            new Thread(){
                @Override
                public void run() {
                    try {
                        Action action=Action.getInstance();
                        String apiString= action.get(null,"http://sorimadang.shop/api/gugakgis");

                        Log.v(tag, apiString);
                        JSONArray jarray=  new JSONArray(apiString);

                        for (int i = 0; i < jarray.length(); i++) {
                            JSONObject jObject = jarray.getJSONObject(i);

                            inst_id = jObject.getInt("id");
                            inst_name = jObject.optString("akgiName");
                            img_url = jObject.optString("imageUrl");
                            sound_url = jObject.optString("soundUrl");

                            instID_arr[i] = inst_id;
                            instName_arr[i] = inst_name;
                            instIMG_arr[i] = img_url;
                            instSound_arr[i] = sound_url;
                        }

                    } catch (JSONException e){
                        //에러
                        e.printStackTrace();
                        Log.v(tag, "실패");
                    }
                    while(true) {
                        //Log.v(tag, "result = " + instName_arr[curValue]);
                        img_url = instIMG_arr[curValue]; //url of the image
                        InputStream is = null;
                        try {
                            is = (InputStream) new URL(img_url).getContent();
                            //Log.d(tag, img_url);
                            //Log.v(tag, "inst url is " + "success");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        drawable = Drawable.createFromStream(is, "srcName");
                        Message message = handler.obtainMessage();
                        handler.sendMessage(message);
                    }
                }
            }.start();

        } catch (Exception e) {
            e.printStackTrace();
            Log.v(tag, "실패2");
        }



    }

    public void PassButton(View view){
        prob_num++;
        if(stage == 1 && prob_num == 4){
            startActivity(new Intent(InstrumentQuizStageActivity.this, InstrumentQuizResultActivity.class));
        }
        else if(stage == 2 && prob_num == 5){
            startActivity(new Intent(InstrumentQuizStageActivity.this, InstrumentQuizResultActivity.class));
        }
        else if(stage == 3 && prob_num == 6){
            startActivity(new Intent(InstrumentQuizStageActivity.this, InstrumentQuizResultActivity.class));
        }
        else{
            curValue = random.nextInt(30);
            stt_view.setText("  ");
            speech_button.setText("정답말하기");
            isNext = false;
        }
    }

    public void STTButton(View view) {
        if(isNext == true){
            prob_num++;
            speech_button.setBackgroundColor(Color.parseColor("#FFCB69"));
            if(stage == 1 && prob_num == 4){
                startActivity(new Intent(InstrumentQuizStageActivity.this, InstrumentQuizResultActivity.class));
            }
            else if(stage == 2 && prob_num == 5){
                startActivity(new Intent(InstrumentQuizStageActivity.this, InstrumentQuizResultActivity.class));
            }
            else if(stage == 3 && prob_num == 6){
                startActivity(new Intent(InstrumentQuizStageActivity.this, InstrumentQuizResultActivity.class));
            }
            else{
                curValue = random.nextInt(30);
                stt_view.setText("  ");
                speech_button.setText("정답말하기");
                isNext = false;
            }

        }
        else{
            mRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
            mRecognizer.setRecognitionListener(listener);
            mRecognizer.startListening(intent);
        }


    }

    private RecognitionListener listener = new RecognitionListener() {

        @Override
        public void onReadyForSpeech(Bundle params) {
            Toast.makeText(getApplicationContext(),"음성인식을 시작합니다.",Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {
           what_name.setVisibility(View.INVISIBLE);
        }

        @Override
         public void onError(int error) {
             Toast.makeText(getApplicationContext(), "버튼을 눌러 다시 말해주세요. " ,Toast.LENGTH_SHORT).show();

         }

         @Override
         public void onResults(Bundle results) {
             ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
             for(int i = 0; i < matches.size() ; i++){
                 stt_view.setText(matches.get(i));
                 Log.d(tag,matches.get(i)+" and "+instName_arr[curValue]);
                 if(matches.get(i).equals(instName_arr[curValue])) {
                     isCorrect =true;
                     speech_button.setText("다음문제");
                     speech_button.setBackgroundColor(Color.parseColor("#7ADB6A"));
                     //true lottie
                     //다음문제 버튼 생성
                     isNext = true;

                 }
                 else {
                     isCorrect = false;
                     //false lottie;
                     speech_button.setText("다시하기");
                 }
             }
         }


         @Override
         public void onPartialResults(Bundle partialResults) {

         }

         @Override
         public void onEvent(int eventType, Bundle params) {

         }
       };
}
/*
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
        }
        catch (Exception e) {
            // Error calling the rest api
            Log.e("REST_API", "GET method failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}*/


class Task extends AsyncTask<String, Void, String> {

    String clientKey = "#########################";;
    private String str, receiveMsg;
    private final String ID = "########";

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL("http://sorimadang.shop/api/gugakgis");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            //conn.setRequestProperty("x-waple-authorization", clientKey);

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);

                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return receiveMsg;
    }

}




