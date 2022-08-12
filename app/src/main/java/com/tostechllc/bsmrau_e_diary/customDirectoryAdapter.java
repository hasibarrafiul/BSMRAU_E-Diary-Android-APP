package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class customDirectoryAdapter extends BaseAdapter {
    Context context;
    ArrayList<postedDirectory> arrayList;
    int id,personid, flag = 1, fromWorker;
    String category;
    public customDirectoryAdapter(Context context, ArrayList<postedDirectory> arrayList){
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

        View rowView = inflater.inflate(R.layout.directorycustomview, parent, false);

        TextView dirid = rowView.findViewById(R.id.dirid);
        TextView catagory = rowView.findViewById(R.id.catagory);
        TextView department = rowView.findViewById(R.id.department);

        System.out.println(dirid);

        postedDirectory postedDirectory = arrayList.get(position);

        dirid.setText(postedDirectory.getId());
        catagory.setText(postedDirectory.getCatagory());
        department.setText(postedDirectory.getDepartment());

        return rowView;
    }
}
