package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class customNewsAdapter extends BaseAdapter {

    Context context;
    ArrayList<listedNews> arrayList;
    public customNewsAdapter(Context context, ArrayList<listedNews> arrayList){
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

        View rowView = inflater.inflate(R.layout.news_customview, parent, false);

        TextView id = rowView.findViewById(R.id.tv_id);
        TextView newsheading = rowView.findViewById(R.id.tv_newsheading);
        TextView newscontent = rowView.findViewById(R.id.tv_newscontent);
        TextView created_on = rowView.findViewById(R.id.tv_created_on);
        ImageView image = rowView.findViewById(R.id.news_image);

        listedNews listedNews = arrayList.get(position);

        id.setText(String.valueOf(listedNews.getId()));
        newsheading.setText(String.valueOf(listedNews.getNewsheading()));
        newscontent.setText(String.valueOf(listedNews.getNewscontent()));
        created_on.setText(String.valueOf(listedNews.getCreated_on()));

        String imagePath = "https://tostechllc.com/bsmrau/newsimage/"+listedNews.getNewsimage();

        Picasso.get().load(imagePath).resize(60,60).into(image);


        return rowView;
    }
}
