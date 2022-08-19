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

public class news extends AppCompatActivity {

    ListView newsListView;
    public static final String FETCH_NEWS = "https://tostechllc.com/android/getNews.php";
    ArrayList<listedNews> arrayListNews;
    customNewsAdapter customNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsListView = findViewById(R.id.newsListView);

        arrayListNews = new ArrayList<>();

        fetchNews();

    }

    public void fetchNews(){
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
                        String newsimage = jo.getString("newsimage");
                        String newsheading = jo.getString("newsheading");
                        String newscontent = jo.getString("newscontent");
                        String created_on = jo.getString("created_on");
                        int status = jo.getInt("status");

                        System.out.println(id+" "+newsimage+" "+newsheading);

                        listedNews listedNews = new listedNews(id,status,newsimage,newsheading,newscontent,created_on);

                        //System.out.println("directory: "+postedDirectorysingle.id);

                        arrayListNews.add(listedNews);

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
        obj.execute(FETCH_NEWS);
    }
    public void loadDatainList(){
        customNewsAdapter = new customNewsAdapter(this,arrayListNews);
        newsListView.setAdapter(customNewsAdapter);
        customNewsAdapter.notifyDataSetChanged();
    }
}