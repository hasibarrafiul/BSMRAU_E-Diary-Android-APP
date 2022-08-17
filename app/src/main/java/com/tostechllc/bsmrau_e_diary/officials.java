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

public class officials extends AppCompatActivity {

    public static final String FETCH_OFFICIALS = "https://tostechllc.com/android/getOfficials.php";
    ArrayList<listedOfficials> arrayList;
    ListView officialsListView;
    com.tostechllc.bsmrau_e_diary.customOfficialsAdapter customOfficialsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_officials);

        officialsListView = findViewById(R.id.officialsListView);

        arrayList = new ArrayList<>();

        fetchFaculty();
        loadDatainList();
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
                        int officialid = jo.getInt("id");
                        String name = jo.getString("name");
                        String designation = jo.getString("designation");
                        String department = jo.getString("department");
                        int mobilenumber = jo.getInt("mobilenumber");
                        String email = jo.getString("email");
                        int officenumber = jo.getInt("officenumber");
                        String image = jo.getString("image");
                        int status = jo.getInt("status");

                        System.out.println(officialid+" "+name+" "+department);

                        listedOfficials listedOfficials = new listedOfficials(officialid,name,department);

                        arrayList.add(listedOfficials);

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
        obj.execute(FETCH_OFFICIALS);
    }
    public void loadDatainList(){
        customOfficialsAdapter = new customOfficialsAdapter(this,arrayList);
        officialsListView.setAdapter(customOfficialsAdapter);
        customOfficialsAdapter.notifyDataSetChanged();
    }
}