package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class customOfficialsAdapter extends BaseAdapter {
    Context context;
    ArrayList<listedOfficials> arrayList;
    public customOfficialsAdapter(Context context, ArrayList<listedOfficials> arrayList){
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

        View rowView = inflater.inflate(R.layout.officialscustomview, parent, false);

        TextView name = rowView.findViewById(R.id.tv_username_officials);
        TextView work = rowView.findViewById(R.id.tv_work_officials);
        TextView phone = rowView.findViewById(R.id.tv_phone_officials);
        TextView email = rowView.findViewById(R.id.tv_email_officials);
        TextView officePhone = rowView.findViewById(R.id.tv_officePhone_officials);
        ImageView image = rowView.findViewById(R.id.officials_image);
        Button call = rowView.findViewById(R.id.btn_call_officials);
        Button sendEmail = rowView.findViewById(R.id.btn_email_officials);

        listedOfficials listedOfficials = arrayList.get(position);


        name.setText(String.valueOf(listedOfficials.getName()));
        work.setText(String.valueOf(listedOfficials.getDesignation()));
        phone.setText(String.valueOf(listedOfficials.getMobilenumber()));
        email.setText(String.valueOf(listedOfficials.getEmail()));
        officePhone.setText(String.valueOf(listedOfficials.getOfficenumber()));


        String imagePath = "https://tostechllc.com/bsmrau/facultyimage/"+listedOfficials.getImage();

        Picasso.get().load(imagePath).resize(100,100).into(image);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                String temp = "tel: 0" + listedOfficials.getMobilenumber();
                intent.setData(Uri.parse(temp));

                try{
                    context.startActivity(intent);
                }
                catch(Exception e){
                    Toast.makeText(context, "No Phone App", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                String temp = "mailto:" + listedOfficials.getEmail();
                intent.setData(Uri.parse(temp));

                try{
                    context.startActivity(intent);
                }
                catch(Exception e){
                    Toast.makeText(context, "No Email App", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rowView;
    }
}
