package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class noticeDetails extends AppCompatActivity {

    String noticeHeading, details, image;
    TextView title, detailss;
    ImageView noticeImage;
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

        title = findViewById(R.id.noticeTitle);
        detailss = findViewById(R.id.noticeDetails);
        noticeImage = findViewById(R.id.noticeImage);

        title.setText(noticeHeading);
        detailss.setText(details);

        String imagePath = "https://tostechllc.com/bsmrau/noticeimage/"+image;

        Picasso.get().load(imagePath).resize(600,600).into(noticeImage);

    }
}