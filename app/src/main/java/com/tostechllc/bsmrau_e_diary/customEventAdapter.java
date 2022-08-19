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

public class customEventAdapter extends BaseAdapter {

    Context context;
    ArrayList<listedEvent> arrayList;
    public customEventAdapter(Context context, ArrayList<listedEvent> arrayList){
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

        View rowView = inflater.inflate(R.layout.eventcustomview, parent, false);

        TextView heading = rowView.findViewById(R.id.tv_heading);
        TextView content = rowView.findViewById(R.id.tv_content);
        TextView id = rowView.findViewById(R.id.tv_id);
        TextView created_on = rowView.findViewById(R.id.tv_created_on);
        ImageView image = rowView.findViewById(R.id.heading_image);

        listedEvent listedEvent = arrayList.get(position);

        id.setText(String.valueOf(listedEvent.getId()));
        heading.setText(String.valueOf(listedEvent.getHeading()));
        content.setText(String.valueOf(listedEvent.getContent()));
        created_on.setText(String.valueOf(listedEvent.getCreated_on()));

        String imagePath = "https://tostechllc.com/bsmrau/eventimage/"+listedEvent.getHeadingimage();

        Picasso.get().load(imagePath).resize(60,60).into(image);

        return rowView;
    }
}
