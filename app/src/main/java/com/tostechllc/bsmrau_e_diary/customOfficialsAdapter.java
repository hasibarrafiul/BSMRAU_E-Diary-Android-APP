package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

        TextView offid = rowView.findViewById(R.id.offid);
        TextView offname = rowView.findViewById(R.id.offname);
        TextView offdepartment = rowView.findViewById(R.id.offdepartment);

        listedOfficials listedOfficials = arrayList.get(position);

        System.out.println("Custom Adapter"+listedOfficials.getId());

        offid.setText(String.valueOf(listedOfficials.getId()));
        offname.setText(listedOfficials.getName());
        offdepartment.setText(listedOfficials.getDepartment());

        return rowView;
    }
}
