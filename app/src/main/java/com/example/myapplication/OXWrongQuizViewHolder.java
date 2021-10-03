package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class OXWrongQuizViewHolder extends RecyclerView.ViewHolder{
    TextView stageQuizNum;
    TextView quizText;
    Button deleteButton;

    OXWrongQuizViewHolder(View itemView)
    {
        super(itemView);

        stageQuizNum = itemView.findViewById(R.id.stageQuizNum);
        quizText = itemView.findViewById(R.id.quizText);
        deleteButton = itemView.findViewById(R.id.deleteButton);
    }
}