package com.example.myapplication.ui.Instrument;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Action;
import com.example.myapplication.GwanInstStudyIntro;
import com.example.myapplication.HyunInstStudyIntro;
import com.example.myapplication.InstrumentQuizIntroActivity;
import com.example.myapplication.OXquizIntroActivity;
import com.example.myapplication.R;
import com.example.myapplication.TaInstStudyIntro;
import com.example.myapplication.ui.home.CustomAdapter;
import com.example.myapplication.ui.home.ListviewComponent;
import com.example.myapplication.ui.mypage.InstAdapter;
import com.example.myapplication.ui.mypage.ListviewComponent2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class InstrumentFragment extends Fragment {

    ArrayList<ListviewComponent2> ListviewComponents;
    ListView customListView2;
    private static InstAdapter instAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_instrument,container,false);

        new Thread(){
            @Override
            public void run() {
                Action action = Action.getInstance();
                String apiString = action.get(null,"http://sorimadang.shop/api/gugakgis"); // 관악기
                gugakgiApi.getGugakgi(apiString);


                if(apiString != null){
                    Log.v("성공 악기 apiString1", apiString);
                }
                else {
                    Log.v("성공 악기 apiString1", "null");
                }
            }
        }.start();

        ListviewComponents = new ArrayList<>();
        ListviewComponents.add(new ListviewComponent2("관악기", "14",R.drawable.janggu , Color.parseColor("#DADADA")));
        ListviewComponents.add(new ListviewComponent2("현악기", "8", R.drawable.oxquiz,Color.parseColor("#F8F8F8")));
        ListviewComponents.add(new ListviewComponent2("타악기", "8", R.drawable.musicnotes,Color.parseColor("#DADADA")));

        customListView2 = (ListView) root.findViewById(R.id.main_listview);
        instAdapter = new InstAdapter(getContext(),ListviewComponents);

        customListView2.setAdapter(instAdapter);
        customListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //각 아이템을 분간 할 수 있는 position과 뷰
                switch (position) {
                    case 0:
                        Intent intent0 = new Intent(getActivity(), GwanInstStudyIntro.class);
                        startActivity(intent0);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getActivity(), HyunInstStudyIntro.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getActivity(), TaInstStudyIntro.class);
                        startActivity(intent2);
                        break;
                }
            }
        });

        return root;
    }

    public static class gugakgiApi{
        //관악기
        public static String akgiNameG[] = new String[14];
        public static String imgUrlG[] = new String[14];
        public static String soundUrlG[] = new String[14];
        //현악기
        public static String akgiNameH[] = new String[8];
        public static String imgUrlH[] = new String[8];
        public static String soundUrlH[] = new String[8];
        //타악기
        public static String akgiNameT[] = new String[8];
        public static String imgUrlT[] = new String[8];
        public static String soundUrlT[] = new String[8];

        public static void getGugakgi(String api){
            //Log.v("getGugakgi",api);
            try {
                JSONArray jarray = new JSONArray(api);

                int j=0;
                for (int i = 0; i < jarray.length(); i++) { //15번
                    Log.v("반복문 확인", String.valueOf(i));
                    JSONObject jObject = jarray.getJSONObject(i);

                    if(i<=13){
                        akgiNameG[i] = jObject.getString("akgiName");
                        imgUrlG[i] = jObject.getString("imageUrl");
                        soundUrlG[i] = jObject.getString("soundUrl");

                        Log.v("성공 G/ stage 퀴즈:", akgiNameG[i] + " " + imgUrlG[i] + soundUrlG[i]);

                        if(i==13){
                            j+=14;
                        }
                    }else if(i>=14 && i<=21){
                        akgiNameT[i-j] = jObject.getString("akgiName");
                        imgUrlT[i-j] = jObject.getString("imageUrl");
                        soundUrlT[i-j] = jObject.getString("soundUrl");

                        Log.v("성공 T/:", akgiNameT[i-j] + " " + imgUrlT[i-j] + soundUrlT[i-j]);

                        if(i==21){
                            j+=8;
                        }
                    }else{
                        akgiNameH[i-j] = jObject.getString("akgiName");
                        imgUrlH[i-j] = jObject.getString("imageUrl");
                        soundUrlH[i-j] = jObject.getString("soundUrl");

                        Log.v("성공 H/:", akgiNameH[i-j] + " " + imgUrlH[i-j] + soundUrlH[i-j]);
                    }


                }
            }catch(Exception e){
                //에러
                e.printStackTrace();
                Log.v("실패 국악기", "실패");
            }
        }

    }

}