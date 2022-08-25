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

public class Catagory extends AppCompatActivity {

    public static final String FETCH_EVENTS = "https://tostechllc.com/android/getCatagory.php";
    ArrayList<listedCatagory> arrayListCatagory;
    ListView catagoryListView;
    customCatagoryAdapter customCatagoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagory);

        arrayListCatagory = new ArrayList<>();

        catagoryListView = findViewById(R.id.catagoryListView);

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
                        String image = jo.getString("image");
                        String catagory = jo.getString("catagory");

                        listedCatagory listedCatagory = new listedCatagory(id, catagory,image);


                        //System.out.println("directory: "+postedDirectorysingle.id);

                        arrayListCatagory.add(listedCatagory);

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
        customCatagoryAdapter = new customCatagoryAdapter(this,arrayListCatagory);
        catagoryListView.setAdapter(customCatagoryAdapter);
        customCatagoryAdapter.notifyDataSetChanged();
    }
}