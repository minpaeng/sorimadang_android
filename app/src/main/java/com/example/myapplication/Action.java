package com.example.myapplication;

import android.util.Log;

import org.json.JSONObject;

import java.net.*;
import java.io.*;

public class Action {

    private static final String TAG = "restApi";

    //이 곳에 요청을 할 HOST URL을 적어주면 됩니다.
    String HOST_URL; //="http://sorimadang.shop/api/user";

    //싱글톤을 위한 코드
    private Action(){}
    public static Action getInstance(){
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder{
        private static final Action INSTANCE=new Action();
    }

    public String get(String jsonMessage,String apiurl) {
        try {
            HOST_URL = apiurl;

            //get 요청할 url
            URL url = new URL(HOST_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("GET");

            //URLConnection에 대한 doOutput 필드값을 지정된 값으로 설정합니다.
            //URL 연결은 입출력에 사용될 수 있어요.
            //URL 연결을 출력용으로 사용하려는 경우 DoOutput 플래그를 true로 설정하고,
            //그렇지 않은 경우는 false로 설정하세요. 기본값은 false입니다.
            //con.setDoOutput(false);

            StringBuilder sb = new StringBuilder();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();

                String response = sb.toString();
                return response;
            } else {
                Log.d(TAG, "StringBuilder:get "+con.getResponseMessage());
                //System.out.println(con.getResponseMessage());
            }

        } catch (Exception e) {
            Log.d(TAG, "Action GET : Err "+e.toString());
            //System.err.println(e.toString());
        }
        return null;
    }

    public String post(String jsonMessage,String apiurl){
        try {
            HOST_URL = apiurl;

            //post 요청할 url을 적어주시면 됩니다. 형태를 위해 저는 그냥 아무거나 적은 겁니다.
            URL url = new URL(HOST_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
            con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
            con.setRequestMethod("POST"); //어떤 요청으로 보낼 것인지?

            //json으로 message를 전달하고자 할 때
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Content-type", "application/json");

            con.setDoInput(true);
            con.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(jsonMessage); //json 형식의 message 전달
            wr.flush();
            Log.d(TAG,jsonMessage);

            StringBuilder sb = new StringBuilder();
            Log.v("server: cgrc  ",Integer.toString(con.getResponseCode()));
            Log.v("server: ok ", String.valueOf(HttpURLConnection.HTTP_OK));
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(), "utf-8"));
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                    Log.d(TAG, "StringBuilder:post 4 "+line);
                }
                br.close();

                String response = sb.toString();
                return response;
            } else {
                Log.d(TAG, "StringBuilder:post 3 "+con.getResponseMessage());
                //System.out.println(con.getResponseMessage());
            }
        } catch (Exception e){
            Log.d(TAG, "Action POST : Err "+e.toString());
            //System.err.println(e.toString());
        }
        return null;
    }
}
