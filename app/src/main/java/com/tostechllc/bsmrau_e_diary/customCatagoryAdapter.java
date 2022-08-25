package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class customCatagoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<listedCatagory> arrayList;
    public customCatagoryAdapter(Context context, ArrayList<listedCatagory> arrayList){
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

        View rowView = inflater.inflate(R.layout.catagorycustomview, parent, false);

        TextView heading = rowView.findViewById(R.id.tv_heading);
        ImageView image = rowView.findViewById(R.id.heading_image);

        listedCatagory listedCatagory = arrayList.get(position);

//        id.setText(String.valueOf(listedEvent.getId()));
        heading.setText(String.valueOf(listedCatagory.getCatagory()));
//        content.setText(String.valueOf(listedEvent.getContent()));
        //created_on.setText(String.valueOf(listedEvent.getCreated_on()));

        String imagePath = "https://tostechllc.com/bsmrau/catagoryimage/"+listedCatagory.getImage();

        Picasso.get().load(imagePath).resize(60,60).into(image);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, subCatagory.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("catagory", listedCatagory.getCatagory());
                context.startActivity(intent);
            }

        });

        return rowView;
    }
}
