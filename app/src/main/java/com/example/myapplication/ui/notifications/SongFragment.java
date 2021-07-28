package com.example.myapplication.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSongBinding;
import com.example.myapplication.ui.mypage.MypageViewModel;

public class SongFragment extends Fragment {

    private SongViewModel songViewModel;
    private FragmentSongBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        songViewModel =
                new ViewModelProvider(this).get(SongViewModel.class);

        binding = FragmentSongBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSong;
        songViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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