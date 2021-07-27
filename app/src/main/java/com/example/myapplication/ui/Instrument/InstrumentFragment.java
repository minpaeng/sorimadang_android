package com.example.myapplication.ui.Instrument;

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

import com.example.myapplication.databinding.FragmentInstrumentBinding;

public class InstrumentFragment extends Fragment {

    private InstrumentViewModel instrumentViewModel;
    private FragmentInstrumentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        instrumentViewModel =
                new ViewModelProvider(this).get(InstrumentViewModel.class);

        binding = FragmentInstrumentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textInstrument;
        instrumentViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
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