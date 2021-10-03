package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OXWrongQuizViewHolder extends RecyclerView.ViewHolder{
    TextView stageQuizNum;
    TextView quizText;

    OXWrongQuizViewHolder(View itemView)
    {
        super(itemView);

        stageQuizNum = itemView.findViewById(R.id.stageQuizNum);
        quizText = itemView.findViewById(R.id.quizText);
    }
}