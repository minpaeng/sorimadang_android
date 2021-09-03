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
            //2)번 코드 호출
//            oxapiString = new PostRestAPITask("http://sorimadang.shop/api/ox-game/wrong-questions/searchs").execute().get();
//            Log.v("성공 apiString", oxapiString);
//            JSONArray jarray=  new JSONArray(oxapiString);

            //1)번 코드 호출
//            JSONObject param = new JSONObject();
//            // POST 방식으로 호출.(GET, POST, PUT, DELETE 다 가능 합니다.)
//            HttpUtil.callApi(param, "POST");




            URL obj = null;
            String json = "";

            String ID_TOKEN = URLEncoder.encode(userIdToken,"UTF-8");

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idToken", ID_TOKEN);
            Log.v("API  성공","idtoken");

            StringBuilder postData = new StringBuilder();
            for(Map.Entry<String,Object> param : params.entrySet()) {
                if(postData.length() != 0) postData.append('&');
                postData.append(param.getKey());
                postData.append('=');
                postData.append(param.getValue());
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");
            Log.v("API  성공", String.valueOf(postDataBytes));

            obj = new URL("http://sorimadang.shop/api/ox-game/wrong-questions/searchs");

            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            con.setDoOutput(true);
            con.getOutputStream().write(postDataBytes);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "UTF-8"));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null){
                response.append(inputLine);
            }
            in.close();

            json = response.toString();
            Log.v("API resopnse 성공",json);


        } catch (Exception e) {
            e.printStackTrace();
            Log.v("실패 apiString", "실패");
        }



        //idtoken보내고 응답받아서 textview에 넘겨주기
        //wrongAnswer.setText(#응답#);
    }


    /*
    1) API POST 첫번째 코드
    - https://dion-ko.tistory.com/115 이 사이트의 코드 참조
    - 아래에 주석처리한 곳은 에러남! 에러 나지않게, ox오답노트코드에 맞춰서 수정 필요!
     */
    public static class HttpUtil {

        public static void callApi(JSONObject params, String type){

            HttpURLConnection conn = null;
            JSONObject responseJson = null;

            try {
                //URL 설정
                URL url = new URL("http://sorimadang.shop/api/ox-game/wrong-questions/searchs");

                conn = (HttpURLConnection) url.openConnection();

                // type의 경우 POST, GET, PUT, DELETE 가능
                conn.setRequestMethod(type);
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
                // JSON 형식의 데이터 셋팅
                JSONObject commands = new JSONObject();
                JSONArray jsonArray = new JSONArray();

//                params.addProperty("key", 1);
//                commands.add("userInfo", params);
//                // JSON 형식의 데이터 셋팅 끝
//
//                // 데이터를 STRING으로 변경
//                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                String jsonOutput = gson.toJson(commands);

                bw.write(commands.toString());
                bw.flush();
                bw.close();

                // 보내고 결과값 받기
                int responseCode = conn.getResponseCode();
                if (responseCode == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = "";
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    responseJson = new JSONObject(sb.toString());

                    // 응답 데이터
                    System.out.println("responseJson :: " + responseJson);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                System.out.println("not JSON Format response");
                e.printStackTrace();
            }
        }
    }


    /*
    2) API POST 두번째 코드
    - OXquizStageActivity.java의 api를 불러오는 코드와 같은 코드! GET -> POST 했다.
    - doInBackground 함수 POST에 맞게 수정 필요!!!!!
     */
    // Rest API calling task
    public static class PostRestAPITask extends AsyncTask<Integer, Void, String> {
        // Variable to store url
        protected String mURL;

        // Constructor
        public PostRestAPITask(String url) {
            mURL = url;
        }

        // Background work
        protected String doInBackground(Integer... params) {
            String result = null;

            try {
                // Open the connection
                URL url = new URL(mURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");

                conn.setDoInput(true); //input을 사용하도록 설정 (default : true)
                conn.setDoOutput(true); //output을 사용하도록 설정 (default : false)

                conn.setConnectTimeout(60); //타임아웃 시간 설정 (default : 무한대기)

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8")); //캐릭터셋 설정

                writer.write(
                        "idToken="+userIdToken
                ); //요청 파라미터를 입력
                writer.flush();
                writer.close();
                os.close();
                Log.v("성공 close: ","성공");

                //conn.connect();

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8")); //캐릭터셋 설정

                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = br.readLine()) != null) {
                    if(sb.length() > 0) {
                        sb.append("\n");
                    }
                    sb.append(line);
                }


//                InputStream is = conn.getInputStream();
//
//                // Get the stream
//                StringBuilder builder = new StringBuilder();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    builder.append(line);
//                }

                // Set the result
                result =sb.toString();//builder.toString();
                Log.v("성공 doin: ",result);
                Log.v("성공 doin2: ","끝");
            }
            catch (Exception e) {
                // Error calling the rest api
                Log.e("REST_API", "POST method failed: " + e.getMessage());
                e.printStackTrace();
            }
            return result; //null;
        }
    }

}