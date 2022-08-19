package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class noticeDetails extends AppCompatActivity {

    String noticeHeading, details, image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noticeHeading = extras.getString("noticeheading");
            details = extras.getString("noticedetails");
            image = extras.getString("noticeimage");
            System.out.println(noticeHeading);
            System.out.println(details);
            System.out.println(image);
        }
    }
}