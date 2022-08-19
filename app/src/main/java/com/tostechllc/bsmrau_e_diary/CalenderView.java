package com.tostechllc.bsmrau_e_diary;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class CalenderView extends AppCompatActivity {
    CalendarView calendar;
    int monthToFetch=1;
    ListView holidayListView;
    customHolidayAdapter customHolidayAdapter;
    ArrayList<holidayListView> arrayListHoliday;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);
        calendar = findViewById(R.id.calendar);
        arrayListHoliday = new ArrayList<>();
        holidayListView = findViewById(R.id.holidayListView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                            @Override
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {
                                emptyArraylist();
                                fetchHoliday();
                                monthToFetch = month+1;
                                //System.out.println(month+1);
                            }
                        });
    }

    public void fetchHoliday(){
        @SuppressLint("StaticFieldLeak")
        class dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data){
                try {

                    JSONObject  ja = new JSONObject(data);
                    JSONObject response = ja.getJSONObject("response");
                    String holidays = response.getString("holidays");

                    //System.out.println(holidays);

                    JSONArray jaa = new JSONArray(holidays);
                    JSONObject jo = null;

                    for(int i =0;i<jaa.length();i++){
                        jo=jaa.getJSONObject(i);
                        String holidayName = jo.getString("name");
                        String holidayDesc = jo.getString("description");
                        String holidayDate = jo.getString("date");
                        JSONObject date = new JSONObject(holidayDate);
                        String datetime = date.getString("datetime");
                        JSONObject getDay = new JSONObject(datetime);
                        String day = getDay.getString("day");
                        System.out.println(holidayName);
                        System.out.println(holidayDesc);
                        System.out.println(day);

                        holidayListView holidayListView = new holidayListView(holidayName, holidayDesc, day);
                        arrayListHoliday.add(holidayListView);
                        loadDatainList();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data = new StringBuffer();
                    String line;

                    while((line=br.readLine())!=null){
                        data.append(line+"\n");
                    }
                    br.close();
                    return data.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        dbManager obj =new dbManager();
        obj.execute("https://calendarific.com/api/v2/holidays?&api_key=3d83d3036ddc23ba1fe94d17e6a2ecc2036b79cd&country=BD&year=2022&month="+monthToFetch);
    }

    public void loadDatainList(){
        customHolidayAdapter = new customHolidayAdapter(this, arrayListHoliday);
        holidayListView.setAdapter(customHolidayAdapter);
        customHolidayAdapter.notifyDataSetChanged();
    }

    private void emptyArraylist() {
        arrayListHoliday = new ArrayList<>();
        customHolidayAdapter = new customHolidayAdapter(this,arrayListHoliday);
        holidayListView.setAdapter(customHolidayAdapter);
        customHolidayAdapter.notifyDataSetChanged();
    }
}

