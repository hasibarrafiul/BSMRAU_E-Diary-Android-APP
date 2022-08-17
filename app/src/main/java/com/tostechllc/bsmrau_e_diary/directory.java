package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);
        directoryListView = findViewById(R.id.directoryListView);

        arrayList = new ArrayList<>();

        faculty = findViewById(R.id.faculty);
        faculty.setOnClickListener(view -> facultylistShow());
        officials = findViewById(R.id.official);
        officials.setOnClickListener(view -> officialslistShow());

        fetchDirectory();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fetchDirectory();
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
        Intent intent = new Intent(this, faculty.class);
        startActivity(intent);
    }
    public void officialslistShow(){
        Intent intent = new Intent(this, officials.class);
        startActivity(intent);
    }
}