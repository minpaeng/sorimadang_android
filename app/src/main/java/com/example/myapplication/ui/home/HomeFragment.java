package com.example.myapplication.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.core.widget.ListViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    ArrayList<ListviewComponent> ListviewComponents;
    ListView customListView;
    private static CustomAdapter customAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        ListviewComponents = new ArrayList<>();
        ListviewComponents.add(new ListviewComponent("너 이름이 뭐니?", "국악기의 이름을 맞혀주세요",R.drawable.janggu , Color.parseColor("#DADADA")));
        ListviewComponents.add(new ListviewComponent("국악기 OX 퀴즈", "a", R.drawable.oxquiz,Color.parseColor("#F8F8F8")));
        ListviewComponents.add(new ListviewComponent("민요 맞히기", "a", R.drawable.musicnotes,Color.parseColor("#DADADA")));
        ListviewComponents.add(new ListviewComponent("하늘에서 음표가\n떨어진다면", "a", R.drawable.musicnote2,Color.parseColor("#F8F8F8")));

        customListView = (ListView) root.findViewById(R.id.main_listview);
        customAdapter = new CustomAdapter(getContext(),ListviewComponents);
        customListView.setAdapter(customAdapter);
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //각 아이템을 분간 할 수 있는 position과 뷰
                String selectedItem = (String) view.findViewById(R.id.textView_name).getTag().toString();
            }
        });
        /*final TextView textView = binding.allGame;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}