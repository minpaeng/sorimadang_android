package com.example.myapplication.ui.mypage;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentMypageBinding;

public class MypageFragment extends Fragment {

    View view;
    private FragmentMypageBinding binding;
    private MypageViewModel mypageViewModel;
    private ImageView halfpeng_img;

    public static MypageFragment newInstance() {
        return new MypageFragment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //View v = inflater.inflate(R.layout.fragment_mypage,container,false);

        halfpeng_img = (ImageView) getView().findViewById(R.id.peng_half_image);
        halfpeng_img.setBackground(new ShapeDrawable(new OvalShape()));
        halfpeng_img.setClipToOutline(true);
        mypageViewModel =
                new ViewModelProvider(this).get(MypageViewModel.class);

        binding = FragmentMypageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView view = binding.mypageTextview;
        mypageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                view.findViewById(R.id.mypage_textview);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


