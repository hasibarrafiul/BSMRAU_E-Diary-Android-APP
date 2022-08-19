package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class news_details extends AppCompatActivity {

    String newsHeading, newsContent, image, created_on;
    TextView newsheading, newscontent, newstime;
    ImageView newsImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            newsHeading = extras.getString("newsheading");
            newsContent = extras.getString("newscontent");
            image = extras.getString("newsimage");
            created_on = extras.getString("newstime");
            System.out.println(newsHeading);
            System.out.println(newsContent);
            System.out.println(image);
            System.out.println(created_on);
        }

        newsheading = findViewById(R.id.newsHeading);
        newscontent = findViewById(R.id.newsContent);
        newstime = findViewById(R.id.newsTime);
        newsImage = findViewById(R.id.newsImage);

        newsheading.setText(newsHeading);
        newscontent.setText(newsContent);
        newstime.setText(created_on);

        String imagePath = "https://tostechllc.com/bsmrau/newsimage/"+image;

        Picasso.get().load(imagePath).resize(600,600).into(newsImage);
    }
}