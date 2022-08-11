package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class directory extends AppCompatActivity {

    public static final String FETCH_DIRECTORY = "https://tostechllc.com/android/getDirectory.php";
    TextView dirid, dircatagory, dirdepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        dirid = findViewById(R.id.dirid);
        dircatagory = findViewById(R.id.catagory);
        dirdepartment = findViewById(R.id.department);

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
                    JSONObject jo;

                    for(int i =0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        int directoryid = jo.getInt("id");
                        String catagory = jo.getString("catagory");
                        String department = jo.getString("department");

                        System.out.println(directoryid+" "+catagory+" "+department);
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
}