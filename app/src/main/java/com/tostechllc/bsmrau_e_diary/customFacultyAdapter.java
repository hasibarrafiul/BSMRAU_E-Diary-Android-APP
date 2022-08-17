package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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

        TextView facid = rowView.findViewById(R.id.facid);
        TextView facname = rowView.findViewById(R.id.facname);
        TextView facdepartment = rowView.findViewById(R.id.facdepartment);

        listedFaculty listedFaculty = arrayList.get(position);

        System.out.println("Custom Adapter"+listedFaculty.getId());

        facid.setText(String.valueOf(listedFaculty.getId()));
        facname.setText(listedFaculty.getName());
        facdepartment.setText(listedFaculty.getDepartment());

        return rowView;
    }
}
