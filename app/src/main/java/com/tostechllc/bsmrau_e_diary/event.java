package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class event extends AppCompatActivity {

    public static final String FETCH_EVENTS = "https://tostechllc.com/android/getEvents.php";
    ArrayList<listedEvent> arrayListEvent;
    ListView eventlistView;
    customEventAdapter customEventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        arrayListEvent = new ArrayList<>();

        eventlistView = findViewById(R.id.eventListView);

        fetchEvent();

    }

    public void fetchEvent(){
        @SuppressLint("StaticFieldLeak")
        class dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data){
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;

                    for(int i =0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        int id = jo.getInt("id");
                        String headingimage = jo.getString("headingimage");
                        String heading = jo.getString("heading");
                        String content = jo.getString("content");
                        String created_on = jo.getString("created_on");
                        int status = jo.getInt("status");

                        System.out.println(id+" "+headingimage+" "+heading);

                        listedEvent listedEvent = new listedEvent(id,status,headingimage,heading,content,created_on);


                        //System.out.println("directory: "+postedDirectorysingle.id);

                        arrayListEvent.add(listedEvent);

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
        obj.execute(FETCH_EVENTS);
    }
    public void loadDatainList(){
        customEventAdapter = new customEventAdapter(this,arrayListEvent);
        eventlistView.setAdapter(customEventAdapter);
        customEventAdapter.notifyDataSetChanged();
    }
}