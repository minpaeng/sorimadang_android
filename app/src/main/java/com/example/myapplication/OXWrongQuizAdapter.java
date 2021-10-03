package com.example.myapplication;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OXWrongQuizAdapter extends RecyclerView.Adapter<OXWrongQuizViewHolder> {
    private ArrayList<OXWrongQuiz> oxWrongQuizs = null;

    OXWrongQuizAdapter(ArrayList<OXWrongQuiz> oxWrongQuizs) {
        this.oxWrongQuizs = oxWrongQuizs;
    }

    @Override
    public OXWrongQuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.card_oxquiz, parent, false);
        OXWrongQuizViewHolder viewHolder = new OXWrongQuizViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OXWrongQuizViewHolder viewHolder, int position)
    {
        String stageQuizNum = oxWrongQuizs.get(position).getStageNum() + "-" + oxWrongQuizs.get(position).getQuizNum();

        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인딩
        viewHolder.stageQuizNum.setText(stageQuizNum);
        viewHolder.quizText.setText(oxWrongQuizs.get(position).getQuiz());
        /*//정답에 따라 백그라운드컬러 변경
        if (oxWrongQuizs.get(position).getAnswer() == 1) {
            viewHolder.quizText.setBackground(Drawable.createFromPath("../res/drawable/rectangle_border_bold_green"));
        }
        else {
            viewHolder.quizText.setBackground(Drawable.createFromPath("../res/drawable/rectangle_border_bold_red"));
        }*/
    }

    @Override
    public int getItemCount()
    {
        //Adapter가 관리하는 전체 데이터 개수 반환
        int size = oxWrongQuizs.size();
        return oxWrongQuizs.size();
    }
}
