package com.example.myapplication.ui.Instrument;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.GwanInstStudyIntro;
import com.example.myapplication.InstrumentQuizIntroActivity;
import com.example.myapplication.OXquizIntroActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.home.CustomAdapter;
import com.example.myapplication.ui.home.ListviewComponent;
import com.example.myapplication.ui.mypage.InstAdapter;
import com.example.myapplication.ui.mypage.ListviewComponent2;

import java.util.ArrayList;

public class InstrumentFragment extends Fragment {

    ArrayList<ListviewComponent2> ListviewComponents;
    ListView customListView2;
    private static InstAdapter instAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_instrument,container,false);

        ListviewComponents = new ArrayList<>();
        ListviewComponents.add(new ListviewComponent2("관악기", "0/17(수정필요)",R.drawable.janggu , Color.parseColor("#DADADA")));
        ListviewComponents.add(new ListviewComponent2("현악기", "0/17(수정필요)", R.drawable.oxquiz,Color.parseColor("#F8F8F8")));
        ListviewComponents.add(new ListviewComponent2("타악기", "0/17(수정필요)", R.drawable.musicnotes,Color.parseColor("#DADADA")));

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
                        Toast.makeText(getActivity(),"toast 1",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"toast 2",Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"toast 3",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        return root;
    }

}