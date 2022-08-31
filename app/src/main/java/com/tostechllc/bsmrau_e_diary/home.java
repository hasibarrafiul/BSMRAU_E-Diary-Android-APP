package com.tostechllc.bsmrau_e_diary;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class home extends AppCompatActivity  {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //navigationView.setNavigationItemSelectedListener(this);

/*        Menu = findViewById(R.id.menu);
        Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
*/


//        findViewById(R.id.menu).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawer.openDrawer(Gravity.RIGHT);
//            }
//        });
        Button directory = findViewById(R.id.btn_directory);
        directory.setOnClickListener(view -> fetchDirectory());

        Button calender = findViewById(R.id.btn_calender);
        calender.setOnClickListener(view -> fetchCalender());

        Button notice = findViewById(R.id.btn_notice);
        notice.setOnClickListener(view -> fetchNotice());

        Button event = findViewById(R.id.btn_event);
        event.setOnClickListener(view -> fetchEvent());

        Button news = findViewById(R.id.btn_news);
        news.setOnClickListener(view -> fetchNews());

        Button videos = findViewById(R.id.btn_video);
        videos.setOnClickListener(view -> fetchVideos());

    }

    //@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void fetchDirectory(){
        Intent intent = new Intent(this, Catagory.class);
        startActivity(intent);
    }
    public void fetchCalender(){
        Intent intent = new Intent(this, CalenderView.class);
        startActivity(intent);
    }

    private void fetchNotice() {
        Intent intent = new Intent(this, notices.class);
        startActivity(intent);
    }

    private void fetchEvent() {
        Intent intent = new Intent(this, event.class);
        startActivity(intent);
    }
    private void fetchNews() {
        Intent intent = new Intent(this, news.class);
        startActivity(intent);
    }
    private void fetchVideos() {
        Intent intent = new Intent(this, videos.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}