package com.tostechllc.bsmrau_e_diary;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class search extends AppCompatActivity {

    ArrayList<listedSearch> arrayListSearch;
    ListView searchListview;
    customSearchAdapter customSearchAdapter;
    EditText searchED;
    String searchValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        arrayListSearch = new ArrayList<>();
        searchListview = findViewById(R.id.search_listview);
        searchED = findViewById(R.id.seachEditText);

        searchED.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){
                System.out.println(searchED.getText().toString().trim());
                searchValue = searchED.getText().toString().trim();
                searchListShow();
            }
        });


        //fetchSearch();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    public void searchListShow(){
        searchListview = findViewById(R.id.search_listview);
        fetchSearch();
        emptySearch();
        loadSearch();
    }

    private void emptySearch() {
        arrayListSearch = new ArrayList<>();
        customSearchAdapter = new customSearchAdapter(this,arrayListSearch);
        searchListview.setAdapter(customSearchAdapter);
        customSearchAdapter.notifyDataSetChanged();
    }


    public void fetchSearch(){
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



                        listedSearch listedSearch = new listedSearch(facultyid,mobilenumber,officenumber,status,name,designation,department,email,image);

                        arrayListSearch.add(listedSearch);

                        loadSearch();
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
        obj.execute("https://tostechllc.com/android/search.php?search="+searchValue);
    }
    public void loadSearch(){
        customSearchAdapter = new customSearchAdapter(this,arrayListSearch);
        searchListview.setAdapter(customSearchAdapter);
        customSearchAdapter.notifyDataSetChanged();
    }

}