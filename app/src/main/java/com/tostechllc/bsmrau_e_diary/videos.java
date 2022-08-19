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

public class videos extends AppCompatActivity {

    ListView videoListView;
    public static final String FETCH_VIDEOS = "https://tostechllc.com/android/getVideos.php";
    ArrayList<listedVideos> arrayListVideos;
    customVideosAdapter customVideosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);
        videoListView = findViewById(R.id.videoListView);

        arrayListVideos = new ArrayList<>();

        fetchFaculty();
    }
    public void fetchFaculty(){
        @SuppressLint("StaticFieldLeak")
        class dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data){
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;

                    for(int i =0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        int videoid = jo.getInt("id");
                        String videolink = jo.getString("videolink");
                        String date = jo.getString("date");
                        String videoname = jo.getString("videoname");


                        listedVideos listedVideos = new listedVideos(videoid,videolink,date,videoname);

                        arrayListVideos.add(listedVideos);

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
        obj.execute(FETCH_VIDEOS);
    }
    public void loadDatainList(){
        customVideosAdapter = new customVideosAdapter(this,arrayListVideos);
        videoListView.setAdapter(customVideosAdapter);
        customVideosAdapter.notifyDataSetChanged();
    }
}