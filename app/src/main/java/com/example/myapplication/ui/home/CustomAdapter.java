package com.example.myapplication.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;




public class CustomAdapter extends ArrayAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private List list;

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class ViewHolder {
        public TextView tv_name;
        public TextView tv_exp;
        public ImageView iv_image;
    }

    public CustomAdapter(Context context, ArrayList list){
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            convertView = layoutInflater.inflate(R.layout.listview_custom, parent, false);
        }


        viewHolder = new ViewHolder();
        viewHolder.tv_name = (TextView) convertView.findViewById(R.id.textView_name);
        viewHolder.tv_exp = (TextView) convertView.findViewById(R.id.textView_exp);
        viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.imageView_game);

        final ListviewComponent listviewComponent = (ListviewComponent) list.get(position);
        viewHolder.tv_name.setText(listviewComponent.getGameName());
        viewHolder.tv_exp.setText(listviewComponent.getGameExp());
        viewHolder.iv_image.setImageResource(listviewComponent.getGameImage());

        if(viewHolder.tv_name.toString().equals("너 이름이 뭐니?") ){
            convertView.setBackgroundColor(listviewComponent.setBackgound());
        }
        else if(viewHolder.tv_name.toString().equals("국악기 OX 퀴") ){
            convertView.setBackgroundColor(listviewComponent.setBackgound());
        }
        else if(viewHolder.tv_name.toString().equals("민요 맞히기") ){
            convertView.setBackgroundColor(listviewComponent.setBackgound());
        }
        else {
            convertView.setBackgroundColor(listviewComponent.setBackgound());
        }


        return convertView;
    }
}