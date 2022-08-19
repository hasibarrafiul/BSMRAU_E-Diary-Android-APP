package com.tostechllc.bsmrau_e_diary;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class customHolidayAdapter extends BaseAdapter {

    Context context;
    ArrayList<holidayListView> arrayListHoliday;
    public customHolidayAdapter(Context context, ArrayList<holidayListView> arrayListHoliday){
        this.context = context;
        this.arrayListHoliday = arrayListHoliday;
    }

    @Override
    public int getCount() {
        return this.arrayListHoliday.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListHoliday.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.holiday_customview, parent, false);

        holidayListView holidayListView = arrayListHoliday.get(position);

        TextView title = rowView.findViewById(R.id.holidayTitle);
        TextView desc = rowView.findViewById(R.id.holidaydesc);
        TextView day = rowView.findViewById(R.id.holidayDay);

        title.setText(holidayListView.getName());
        desc.setText(holidayListView.getDesc());
        day.setText(holidayListView.getDay());

        return rowView;
    }
}
