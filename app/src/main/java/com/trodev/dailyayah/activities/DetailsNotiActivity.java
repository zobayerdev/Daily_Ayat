package com.trodev.dailyayah.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.trodev.dailyayah.R;

public class DetailsNotiActivity extends AppCompatActivity {

    private TextView ayahTv, headTv, dateTv;

    String headline, body, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_noti);


        // action bar title
        getSupportActionBar().setTitle("বিস্তারিত দেখুন");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*find view all id*/
        ayahTv = findViewById(R.id.ayahTv);
        headTv = findViewById(R.id.headTv);
        dateTv = findViewById(R.id.dateTv);


        /*get data in another activity*/
        Intent intent = getIntent();
        body = intent.getStringExtra("body");
        headline = intent.getStringExtra("title");
        date = intent.getStringExtra("date");

        /*set data in this id*/
        ayahTv.setText("বর্ণনাঃ " + body);
        headTv.setText("হেডলাইনঃ " + headline);
        dateTv.setText("তারিখঃ " + date);

    }
}