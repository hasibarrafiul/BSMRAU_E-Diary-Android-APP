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

public class customSubCatagoryAdapter extends BaseAdapter {

    Context context;
    ArrayList<listedSubcatagory> arrayList;
    public customSubCatagoryAdapter(Context context, ArrayList<listedSubcatagory> arrayList){
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

        View rowView = inflater.inflate(R.layout.subcatagorycustomview, parent, false);

        TextView catagory = rowView.findViewById(R.id.tv_heading);
        TextView subcatagory = rowView.findViewById(R.id.subcatagory);

        listedSubcatagory listedSubcatagory = arrayList.get(position);

//        id.setText(String.valueOf(listedEvent.getId()));
        catagory.setText(String.valueOf(listedSubcatagory.getCatagory()));
        subcatagory.setText(String.valueOf(listedSubcatagory.getSubcatagory()));



        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, directory.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("subcatagory", listedSubcatagory.getSubcatagory());
                context.startActivity(intent);
            }

        });

        return rowView;
    }
}
