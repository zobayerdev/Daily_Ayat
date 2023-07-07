package com.trodev.dailyayah.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.trodev.dailyayah.R;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        // set title in activity
        getSupportActionBar().setTitle("Privacy Policy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}