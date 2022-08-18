package com.tostechllc.bsmrau_e_diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class home extends AppCompatActivity  {

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

    }

    private void fetchNotice() {
        Intent intent = new Intent(this, notices.class);
        startActivity(intent);
    }

    //@Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void fetchDirectory(){
        Intent intent = new Intent(this, directory.class);
        startActivity(intent);
    }
    public void fetchCalender(){
        Intent intent = new Intent(this, CalenderView.class);
        startActivity(intent);
    }


}