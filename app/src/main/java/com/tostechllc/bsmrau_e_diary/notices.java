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

public class notices extends AppCompatActivity {

    public static final String FETCH_NOTICES = "https://tostechllc.com/android/getNotices.php";
    ArrayList<listedNotice> arrayListNotice;
    ListView noticeListView;
    customNoticeAdapter customNoticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices);

        noticeListView = findViewById(R.id.notice_listView);

        arrayListNotice = new ArrayList<>();

        fetchNotice();

    }

    protected void onRestart() {

        super.onRestart();
    }

    public void fetchNotice(){
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
                        String noticeimage = jo.getString("noticeimage");
                        String noticeheading = jo.getString("noticeheading");
                        String details = jo.getString("details");
                        String created_on = jo.getString("created_on");
                        int status = jo.getInt("status");

                        System.out.println(id+" "+noticeimage+" "+noticeheading);

                        listedNotice listedNotice = new listedNotice(noticeimage,noticeheading,details,created_on,id,status);

                        //System.out.println("directory: "+postedDirectorysingle.id);

                        arrayListNotice.add(listedNotice);

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
        obj.execute(FETCH_NOTICES);
    }
    public void loadDatainList(){
        customNoticeAdapter = new customNoticeAdapter(this,arrayListNotice);
        noticeListView.setAdapter(customNoticeAdapter);
        customNoticeAdapter.notifyDataSetChanged();
    }
}