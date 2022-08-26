package com.tostechllc.bsmrau_e_diary;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class subCatagory extends AppCompatActivity {

    ArrayList<listedSubcatagory> arrayListSubCatagory;
    GridView SubcatagoryListView;
    customSubCatagoryAdapter customSubCatagoryAdapter;
    String catagoryPrev, titlePrev;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcatagory);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            catagoryPrev = extras.getString("catagory");
            titlePrev = extras.getString("title_subcategory");
        }

        arrayListSubCatagory = new ArrayList<>();

        SubcatagoryListView = findViewById(R.id.SubcatagoryListView);
        title = findViewById(R.id.tv_title_subcategory);
        title.setText(titlePrev);

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
                        String catagory = jo.getString("catagory");
                        String subcatagory = jo.getString("subcatagory");

                        listedSubcatagory listedSubcatagory = new listedSubcatagory(id, catagory, subcatagory);


                        //System.out.println("directory: "+postedDirectorysingle.id);

                        arrayListSubCatagory.add(listedSubcatagory);

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
        obj.execute("https://tostechllc.com/android/getSubCatagory.php?catagory="+catagoryPrev);
    }
    public void loadDatainList(){
        customSubCatagoryAdapter = new customSubCatagoryAdapter(this,arrayListSubCatagory);
        SubcatagoryListView.setAdapter(customSubCatagoryAdapter);
        customSubCatagoryAdapter.notifyDataSetChanged();
    }
}