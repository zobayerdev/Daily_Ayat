package com.trodev.dailyayat.islamic_books;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_view);

        /*action bar title*/
        getSupportActionBar().setTitle("পিডিএফ দেখুন");

        /*init views*/
        pdfView = findViewById(R.id.pdfView);

        /*pdf url*/
        pdfUrl = getIntent().getStringExtra("pdfUrl");


        /*progress dialog show and init*/
        progressDialog = new ProgressDialog(BooksViewActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog = ProgressDialog
                .show(this,
                        "পিডিএফ লোড হচ্ছে",
                        "কিছুক্ষণ অপেক্ষা করুন");
        progressDialog.show();

        /*pdf view loading*/
        new PdfDownload().execute(pdfUrl);

    }

    private class PdfDownload extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {

            InputStream inputStream = null;

            try {
                progressDialog.show();
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                if (urlConnection.getResponseCode() == 200) {
                    progressDialog.show();
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            progressDialog.hide();
            pdfView.fromStream(inputStream)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    .load();
        }
    }
}