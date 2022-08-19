package com.tostechllc.bsmrau_e_diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class event_details extends AppCompatActivity {

    String eventheading_image,event_heading,event_content,event_time;
    TextView eventHeading, eventContent, eventTime;
    ImageView eventHeadingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            event_heading = extras.getString("eventheading");
            event_content = extras.getString("eventcontent");
            eventheading_image = extras.getString("eventimage");
            event_time = extras.getString("eventtime");
            System.out.println(event_heading);
            System.out.println(event_content);
            System.out.println(eventheading_image);
            System.out.println(event_time);
        }

        eventHeadingImage = findViewById(R.id.eventImage);
        eventHeading = findViewById(R.id.eventHeading);
        eventContent = findViewById(R.id.eventContent);
        eventTime = findViewById(R.id.eventTime);
        eventHeading.setText(event_heading);
        eventContent.setText(event_content);
        eventTime.setText(event_time);

        String imagePath = "https://tostechllc.com/bsmrau/eventimage/"+eventheading_image;

        Picasso.get().load(imagePath).resize(600,600).into(eventHeadingImage);

    }
}