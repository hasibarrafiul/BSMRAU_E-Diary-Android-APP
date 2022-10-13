package com.tostechllc.bsmrau_e_diary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class CalenderView extends AppCompatActivity {
    CalendarView calendar;
    int monthToFetch=1, yearToFetch = 2022;
    ListView holidayListView;
    customHolidayAdapter customHolidayAdapter;
    ArrayList<holidayListView> arrayListHoliday;
    ImageButton back, home;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_view);
        calendar = findViewById(R.id.calendar);
        long selectedDate = calendar.getDate();
        milliSecToDate(selectedDate);
        System.out.println("Data selected: "+selectedDate);
        arrayListHoliday = new ArrayList<>();
        holidayListView = findViewById(R.id.holidayListView);
        emptyArraylist();
        if(checkNetworkConnection()){
            fetchHoliday();
        }
        else{
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
        home = findViewById(R.id.btn_home);

        home.setOnClickListener(view -> {
            Intent intent = new Intent(this, home.class).setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);;
            startActivity(intent);
            finish();
        });
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                            @Override
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {
                                monthToFetch = month+1;
                                yearToFetch = year;
                                emptyArraylist();
                                fetchHoliday();
                            }
                        });
        back = findViewById(R.id.btn_back);
        back.setOnClickListener(view -> onBackPressed());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void milliSecToDate (long milliSec){
        @SuppressLint("SimpleDateFormat") DateFormat obj = new SimpleDateFormat("dd MM yyyy HH:mm:ss:SSS Z");
        Date res = new Date(milliSec);
        String[] date = obj.format(res).split(" ");
        monthToFetch = Integer.parseInt(date[1]);
        yearToFetch = Integer.parseInt(date[2]);

    }

    public void fetchHoliday(){
        @SuppressLint("StaticFieldLeak")
        class dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data){
                try {

                    JSONObject ja = new JSONObject(data);
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
                        String month = getDay.getString("month");

//                        System.out.println(holidayName);
//                        System.out.println(holidayDesc);
//                        System.out.println(day);
                        switch(month){
                            case "1":
                                month = "JAN";
                                break;
                            case "2":
                                month = "FEB";
                                break;
                            case "3":
                                month = "MAR";
                                break;
                            case "4":
                                month = "APR";
                                break;
                            case "5":
                                month = "MAY";
                                break;
                            case "6":
                                month = "JUN";
                                break;
                            case "7":
                                month = "JUL";
                                break;
                            case "8":
                                month = "AUG";
                                break;
                            case "9":
                                month = "SEP";
                                break;
                            case "10":
                                month = "OCT";
                                break;
                            case "11":
                                month = "NOV";
                                break;
                            case "12":
                                month = "DEC";
                                break;
                        }


                        holidayListView holidayListView = new holidayListView(holidayName, holidayDesc, month+" "+day);
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
        obj.execute("https://calendarific.com/api/v2/holidays?&api_key=3d83d3036ddc23ba1fe94d17e6a2ecc2036b79cd&country=BD&year="+yearToFetch+"&month="+monthToFetch);
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
    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}

