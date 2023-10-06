package com.trodev.dailyayat.islamic_books;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.trodev.dailyayat.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class BooksViewActivity extends AppCompatActivity {

    private WebView webView;
    String pdfUrl;
    ProgressDialog progressDialog;
    PDFView pdfView;
    LottieAnimationView animationView;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_view);

        reference = FirebaseDatabase.getInstance().getReference();

        /*action bar title*/
        getSupportActionBar().setTitle("পিডিএফ দেখুন");

        /*init views*/
        pdfView = findViewById(R.id.pdfView);

        /*pdf url*/
        pdfUrl = getIntent().getStringExtra("pdfUrl");


        animationView = findViewById(R.id.animationView);
        animationView.loop(true);

        /*pdf view loading*/
        new PdfDownload().execute(pdfUrl);

        reference.keepSynced(true);

    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {

            setProgressBarVisibility(View.INVISIBLE);
            InputStream inputStream = null;

            try {
                // progressDialog.show();
                setProgressBarVisibility(View.VISIBLE);
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200) {
                    setProgressBarVisibility(View.VISIBLE);
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;

        }

        @Override
        protected void onPostExecute(InputStream inputStream) {

            pdfView.fromStream(inputStream)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(true)
                    .fitEachPage(true) // fit each page to the view, else smaller pages are scaled relative to largest page.
                    .pageSnap(true)
                    .load();

            setProgressBarVisibility(View.INVISIBLE);
        }



    }

    private void setProgressBarVisibility(int visibility) {
        // If a user returns back, a NPE may occur if WebView is still loading a page and then tries to hide a ProgressBar.
        if (animationView != null) {
            animationView.setVisibility(visibility);
        }
    }

}