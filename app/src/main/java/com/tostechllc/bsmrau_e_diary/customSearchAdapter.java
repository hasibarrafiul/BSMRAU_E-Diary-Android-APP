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

public class customSearchAdapter extends BaseAdapter {
    Context context;
    ArrayList<listedSearch> arrayList;
    public customSearchAdapter(Context context, ArrayList<listedSearch> arrayList){
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

        View rowView = inflater.inflate(R.layout.searchcustomview, parent, false);

        TextView name = rowView.findViewById(R.id.tv_username);
        TextView work = rowView.findViewById(R.id.tv_work);
        TextView phone = rowView.findViewById(R.id.tv_phone);
        TextView email = rowView.findViewById(R.id.tv_email);
        TextView officePhone = rowView.findViewById(R.id.tv_officePhone);
        ImageView image = rowView.findViewById(R.id.faculty_image);

        listedSearch listedSearch = arrayList.get(position);

        name.setText(String.valueOf(listedSearch.getName()));
        work.setText(String.valueOf(listedSearch.getDesignation()));
        phone.setText(String.valueOf(listedSearch.getMobilenumber()));
        email.setText(String.valueOf(listedSearch.getEmail()));
        officePhone.setText(String.valueOf(listedSearch.getOfficenumber()));


        String imagePath = "https://tostechllc.com/bsmrau/facultyimage/"+listedSearch.getImage();

        Picasso.get().load(imagePath).resize(60,60).into(image);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel: 0" + listedSearch.getMobilenumber();
                intent.setData(Uri.parse(temp));

                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
