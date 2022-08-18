package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class directory extends AppCompatActivity {

    public static final String FETCH_DIRECTORY = "https://tostechllc.com/android/getDirectory.php";
    ArrayList<postedDirectory> arrayList;
    Button faculty, officials;
    ListView directoryListView;
    customDirectoryAdapter customDirectoryAdapter;

    public static final String FETCH_FACULTY = "https://tostechllc.com/android/getFaculty.php";
    ArrayList<listedFaculty> arrayListFaculty;
    ListView facultyListView;
    customFacultyAdapter customFacultyAdapter;

    public static final String FETCH_OFFICIALS = "https://tostechllc.com/android/getOfficials.php";
    ArrayList<listedOfficials> arrayListOfficials;
    ListView officialsListView;
    com.tostechllc.bsmrau_e_diary.customOfficialsAdapter customOfficialsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        //directoryListView = findViewById(R.id.directoryListView);

        arrayList = new ArrayList<>();
        arrayListFaculty = new ArrayList<>();
        arrayListOfficials = new ArrayList<>();

        faculty = findViewById(R.id.btn_faculty);
        faculty.setOnClickListener(view -> facultylistShow());

        officials = findViewById(R.id.btn_official);
        officials.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_10dp_dark_blue));
        officials.setOnClickListener(view -> officialslistShow());

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void fetchDirectory(){
        @SuppressLint("StaticFieldLeak")
        class dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data){
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;

                    for(int i =0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        int directoryid = jo.getInt("id");
                        String catagory = jo.getString("catagory");
                        String department = jo.getString("department");

                        System.out.println(directoryid+" "+catagory+" "+department);

                        postedDirectory postedDirectorysingle = new postedDirectory(directoryid,catagory,department);

                        //System.out.println("directory: "+postedDirectorysingle.id);

                        arrayList.add(postedDirectorysingle);

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
        obj.execute(FETCH_DIRECTORY);
    }
    public void loadDatainList(){
        customDirectoryAdapter = new customDirectoryAdapter(this,arrayList);
        directoryListView.setAdapter(customDirectoryAdapter);
        customDirectoryAdapter.notifyDataSetChanged();
    }
    public void facultylistShow(){
        facultyListView = findViewById(R.id.directoryListView);
        faculty.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_10dp_blue));
        officials.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_10dp_dark_blue));
        fetchFaculty();
        emptyFaculty();
        loadFacultyDatainList();
    }

    public void officialslistShow(){
        officialsListView = findViewById(R.id.directoryListView);
        officials.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_10dp_blue));
        faculty.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.rounded_10dp_dark_blue));
        fetchOfficials();
        emptyOfficials();
        loadOfficialsDatainList();
    }

    private void emptyFaculty() {
        arrayListFaculty = new ArrayList<>();
        customFacultyAdapter = new customFacultyAdapter(this,arrayListFaculty);
        facultyListView.setAdapter(customFacultyAdapter);
        customFacultyAdapter.notifyDataSetChanged();
    }

    private void emptyOfficials() {
        arrayListOfficials = new ArrayList<>();
        customOfficialsAdapter = new customOfficialsAdapter(this,arrayListOfficials);
        officialsListView.setAdapter(customOfficialsAdapter);
        customOfficialsAdapter.notifyDataSetChanged();
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

                        listedFaculty listedFaculty = new listedFaculty(facultyid,mobilenumber,officenumber,status,name,designation,department,email,image);

                        arrayListFaculty.add(listedFaculty);

                        loadFacultyDatainList();
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
    public void loadFacultyDatainList(){
        customFacultyAdapter = new customFacultyAdapter(this,arrayListFaculty);
        facultyListView.setAdapter(customFacultyAdapter);
        customFacultyAdapter.notifyDataSetChanged();
    }
    public void fetchOfficials(){
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

                        listedOfficials listedOfficials = new listedOfficials(officialid,mobilenumber,officenumber,status,name,designation,department,email,image);

                        arrayListOfficials.add(listedOfficials);

                        loadOfficialsDatainList();
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
    public void loadOfficialsDatainList(){
        customOfficialsAdapter = new customOfficialsAdapter(this,arrayListOfficials);
        officialsListView.setAdapter(customOfficialsAdapter);
        customOfficialsAdapter.notifyDataSetChanged();
    }
}