package com.trodev.dailyayat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.trodev.dailyayat.R;

public class SplashScreen extends AppCompatActivity {

    LottieAnimationView animationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //  actionbar hide
        getSupportActionBar().hide();

        animationView = findViewById(R.id.animationView);

        animationView.animate().setDuration(4000);
        animationView.loop(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, MainActivity.class));
                finishAffinity();
            }
        },4000);

    }
}