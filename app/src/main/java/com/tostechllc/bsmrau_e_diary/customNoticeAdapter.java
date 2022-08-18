package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class customNoticeAdapter extends BaseAdapter {
    Context context;
    ArrayList<listedNotice> arrayList;
    public customNoticeAdapter(Context context, ArrayList<listedNotice> arrayList){
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

        View rowView = inflater.inflate(R.layout.noticecustomview, parent, false);



        TextView noticeid = rowView.findViewById(R.id.noticeid);
        ImageView noticeimage = rowView.findViewById(R.id.noticeimage);
        TextView noticeheading = rowView.findViewById(R.id.noticeheading);

        listedNotice listedNotice = arrayList.get(position);

        System.out.println("Notice Custom Adapter "+listedNotice.getId());

        noticeid.setText(String.valueOf(listedNotice.getId()));
        noticeheading.setText(listedNotice.getHeading());
        String imagepath = "https://tostechllc.com/bsmrau/noticeimage/"+listedNotice.getImage();

        Picasso.get().load(imagepath).resize(100,100).into(noticeimage);

        return rowView;
    }
}
