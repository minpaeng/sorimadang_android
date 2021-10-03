package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OXWrongQuizAdapter extends RecyclerView.Adapter<OXWrongQuizViewHolder> {
    private ArrayList<OXWrongQuiz> oxWrongQuizs = null;
    private String userIdToken;

    OXWrongQuizAdapter(ArrayList<OXWrongQuiz> oxWrongQuizs, String userIdToken) {
        this.oxWrongQuizs = oxWrongQuizs;
        this.userIdToken = userIdToken;
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
    public void onBindViewHolder(OXWrongQuizViewHolder viewHolder, @SuppressLint("RecyclerView") int position)
    {
        String stageQuizNum = oxWrongQuizs.get(position).getStageNum() + "-" + oxWrongQuizs.get(position).getQuizNum();

        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인딩
        viewHolder.stageQuizNum.setText(stageQuizNum);
        viewHolder.quizText.setText(oxWrongQuizs.get(position).getQuiz());
        //정답에 따라 백그라운드컬러 변경
        if (oxWrongQuizs.get(position).getAnswer() == 1) {
            viewHolder.quizText.setBackgroundResource(R.drawable.rectangle_border_bold_green);
        }
        else {
            viewHolder.quizText.setBackgroundResource(R.drawable.rectangle_border_bold_red);
        }
        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCard(position);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        //Adapter가 관리하는 전체 데이터 개수 반환
        int size = oxWrongQuizs.size();
        return oxWrongQuizs.size();
    }

    private void deleteCard(int position) {
        //카드뷰 문항정보
        int stageNum = oxWrongQuizs.get(position).getStageNum();
        int quizNum = oxWrongQuizs.get(position).getQuizNum();

        //카드뷰 삭제
        oxWrongQuizs.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, oxWrongQuizs.size());

        //서버 오답 삭제
        new Thread() {
            @Override
            public void run() {
                deleteNote(stageNum, quizNum);
            }
        }.start();

    }

    private void deleteNote(int stageNum, int quizNum) {
        try {
            Action action=Action.getInstance();
            JSONObject reqtoServer=new JSONObject();
            reqtoServer.put("idToken",userIdToken);
            reqtoServer.put("stage_num", stageNum);
            reqtoServer.put("quiz_num", quizNum);
            String res= action.post(reqtoServer.toString(),"http://sorimadang.shop/api/ox-game/wrong-questions/remove");
            Log.v("result", res);
            if(res != null){
                Log.v("delete 성공", res);//.toString());
            }
            else Log.v("delete null", "null");

        } catch (JSONException e){
            //에러
            e.printStackTrace();
            Log.v("오답 apiString", "실패");
        }
    }
}
