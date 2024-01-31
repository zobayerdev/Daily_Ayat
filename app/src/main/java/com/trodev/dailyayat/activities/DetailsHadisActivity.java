package com.trodev.dailyayat.activities;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.trodev.dailyayat.R;

public class DetailsHadisActivity extends AppCompatActivity {

    private ImageView copyIv, shareIv;
    private TextView ayahTv, authorTv, headTv, dateTv;

    String name, author, head, date;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hadis_ayat);

        // action bar title
        getSupportActionBar().setTitle("সম্পূর্ণ দেখুন");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        /*find view all id*/
        ayahTv = findViewById(R.id.ayahTv);
        authorTv = findViewById(R.id.authorTv);
        headTv = findViewById(R.id.headTv);
        dateTv = findViewById(R.id.dateTv);
        copyIv = findViewById(R.id.copyIv);
        shareIv = findViewById(R.id.shareIv);

        /*get data in another activity*/
        Intent intent = getIntent();
        name = intent.getStringExtra("ayat");
        author = intent.getStringExtra("author");
        head = intent.getStringExtra("headline");
        date = intent.getStringExtra("date");

        /*set data in this id*/
        headTv.setText("হাদিসঃ- " + head);
        ayahTv.setText("হাদিস বর্ণনাঃ- " + name);
        authorTv.setText("উৎসঃ- " + author);
        dateTv.setText("প্রকাশিত তারিখঃ- " + date);

        copyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", headTv.getText().toString().trim() + "\n" + ayahTv.getText().toString().trim() + "\n" + authorTv.getText().toString().trim() + "\n\n" + "Daily Ayah App টি এখনি ডাউনলোড করুন গুগল প্লে- স্টোর থেকে");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(DetailsHadisActivity.this, "কপি সম্পন্ন হয়েছে", Toast.LENGTH_SHORT).show();
            }
        });


        shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, headTv.getText().toString().trim() + "\n" + ayahTv.getText().toString().trim() + "\n" + authorTv.getText().toString().trim() + "\n\n" + "Daily Ayat App টি এখনি ডাউনলোড করুন গুগল প্লে- স্টোর থেকে");
                startActivity(Intent.createChooser(sharingIntent, "শেয়ার করুন"));

            }
        });

    }

}