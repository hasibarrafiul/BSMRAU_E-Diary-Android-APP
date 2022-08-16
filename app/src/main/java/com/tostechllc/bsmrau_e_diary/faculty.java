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

public class faculty extends AppCompatActivity {

    public static final String FETCH_FACULTY = "https://tostechllc.com/android/getFaculty.php";
    ArrayList<listedFaculty> arrayList;
    ListView facultyListView;
    customFacultyAdapter customFacultyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        facultyListView = findViewById(R.id.facultyListView);

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
                        int facultyid = jo.getInt("id");
                        String name = jo.getString("name");
                        String designation = jo.getString("designation");
                        String department = jo.getString("department");
                        int mobilenumber = jo.getInt("mobilenumber");
                        String email = jo.getString("email");
                        int officenumber = jo.getInt("officenumber");
                        String image = jo.getString("image");
                        int status = jo.getInt("status");

                        System.out.println(facultyid+" "+name+" "+department);

                        listedFaculty listedFaculty = new listedFaculty(facultyid,name,department);

                        arrayList.add(listedFaculty);

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
        obj.execute(FETCH_FACULTY);
    }
    public void loadDatainList(){
        customFacultyAdapter = new customFacultyAdapter(this,arrayList);
        facultyListView.setAdapter(customFacultyAdapter);
        customFacultyAdapter.notifyDataSetChanged();
    }
}