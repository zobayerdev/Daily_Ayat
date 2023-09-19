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

import com.squareup.picasso.Picasso;
import com.trodev.dailyayat.R;

public class FullDataActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView copyIv, shareIv;
    private TextView ayahTv, authorTv, headTv, dateTv;
    String name, author, head, date, image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_data);

        // action bar title
        getSupportActionBar().setTitle("সম্পূর্ণ দেখুন");
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        /*find view all id*/
        imageView = findViewById(R.id.imageIV);
        ayahTv = findViewById(R.id.ayahTv);
        authorTv = findViewById(R.id.authorTv);
        headTv = findViewById(R.id.headTv);
        dateTv = findViewById(R.id.dateTv);
        copyIv = findViewById(R.id.copyIv);
        shareIv = findViewById(R.id.shareIv);

        /*get data in another activity*/
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        author = intent.getStringExtra("author");
        head = intent.getStringExtra("headline");
        date = intent.getStringExtra("date");
        image = intent.getStringExtra("image");

        /*set data in this id*/
        ayahTv.setText("বর্ণনাঃ " + name);
        authorTv.setText("উৎসঃ " + author);
        headTv.setText("আয়াতঃ " + head);
        dateTv.setText("প্রকাশিত তারিখঃ " + date);


        /*image view set on images*/
        Picasso.get()
                .load(image)
                .placeholder(R.drawable.apps_icon)
                .fit()
                .centerCrop()
                .into(imageView);


        copyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", headTv.getText().toString().trim() + "\n" + ayahTv.getText().toString().trim() + "\n"
                        + authorTv.getText().toString().trim() + "\n\n" + "Daily Ayah App টি এখনি ডাউনলোড করুন গুগল প্লে- স্টোর থেকে");
                clipboard.setPrimaryClip(clip);
                Toast.makeText(FullDataActivity.this, "Copy successful", Toast.LENGTH_SHORT).show();
            }
        });


        shareIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, headTv.getText().toString().trim() + "\n" + ayahTv.getText().toString().trim() + "\n" + authorTv.getText().toString().trim() + "\n\n" + "Daily Ayah App টি এখনি ডাউনলোড করুন গুগল প্লে- স্টোর থেকে");
                startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });

    }

}