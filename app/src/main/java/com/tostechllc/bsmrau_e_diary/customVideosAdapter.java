package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class customVideosAdapter extends BaseAdapter {

    Context context;
    ArrayList<listedVideos> arrayList;
    public customVideosAdapter(Context context, ArrayList<listedVideos> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.videos_customview, parent, false);

        TextView videoid = rowView.findViewById(R.id.tv_videoid);
        TextView videolink = rowView.findViewById(R.id.tv_videolink);
        TextView date = rowView.findViewById(R.id.tv_date);


        listedVideos listedVideos = arrayList.get(position);


        videoid.setText(String.valueOf(listedVideos.getId()));
        videolink.setText(String.valueOf(listedVideos.getVideolink()));
        date.setText(String.valueOf(listedVideos.getDate()));

        return rowView;
    }
}
