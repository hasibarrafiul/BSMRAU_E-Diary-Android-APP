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

public class customFacultyAdapter extends BaseAdapter {
    Context context;
    ArrayList<listedFaculty> arrayList;
    public customFacultyAdapter(Context context, ArrayList<listedFaculty> arrayList){
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

        View rowView = inflater.inflate(R.layout.facultycustomview, parent, false);

        TextView name = rowView.findViewById(R.id.tv_username);
        TextView work = rowView.findViewById(R.id.tv_work);
        TextView phone = rowView.findViewById(R.id.tv_phone);
        TextView email = rowView.findViewById(R.id.tv_email);
        TextView officePhone = rowView.findViewById(R.id.tv_officePhone);
        ImageView image = rowView.findViewById(R.id.faculty_image);

        listedFaculty listedFaculty = arrayList.get(position);

        name.setText(String.valueOf(listedFaculty.getName()));
        work.setText(String.valueOf(listedFaculty.getDesignation()));
        phone.setText(String.valueOf(listedFaculty.getMobilenumber()));
        email.setText(String.valueOf(listedFaculty.getEmail()));
        officePhone.setText(String.valueOf(listedFaculty.getOfficenumber()));

        String imagePath = "https://tostechllc.com/bsmrau/facultyimage/"+listedFaculty.getImage();

        Picasso.get().load(imagePath).resize(60,60).into(image);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel: 0" + listedFaculty.getMobilenumber();
                intent.setData(Uri.parse(temp));

                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
