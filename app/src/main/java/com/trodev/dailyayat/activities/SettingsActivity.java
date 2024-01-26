package com.trodev.dailyayat.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import com.trodev.dailyayat.R;

public class SettingsActivity extends AppCompatActivity {

    SeekBar seekBarTextSize;
    SharedPreferences sharedPreferences;
    TextView textView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // action bar title
        getSupportActionBar().setTitle("অ্যাপ সেটিং");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        seekBarTextSize = findViewById(R.id.seekBarTextSize);
        textView4 = findViewById(R.id.textView4);
        sharedPreferences = getSharedPreferences("AppSettingsPrefs", 0);

        int textSize = sharedPreferences.getInt("TextSize", 90);
        seekBarTextSize.setProgress(textSize);

        seekBarTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("TextSize", progress);
                editor.apply();
                textView4.setTextSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}