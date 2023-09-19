package com.trodev.dailyayat.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.dailyayat.models.NotificationData;
import com.trodev.dailyayat.R;
import com.trodev.dailyayat.adapters.NotificationAdapter;

import java.util.ArrayList;
import java.util.List;

public class FullNotificationActivity extends AppCompatActivity {

    private RecyclerView notiRv;
    private List<NotificationData> list1;
    private NotificationAdapter adapter;
    private DatabaseReference reference, dbRef;
    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_notification);

        // action bar title
        getSupportActionBar().setTitle("নোটিফিকেশান");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        notiRv = findViewById(R.id.notiRv);

        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);

        reference = FirebaseDatabase.getInstance().getReference().child("Notifications");

        AyatDataAll();
    }


    private void AyatDataAll() {

        dbRef = reference.child("Notifications");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if (!dataSnapshot.exists()) {
                    notiRv.setVisibility(View.VISIBLE); // change
                } else {

                    notiRv.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        NotificationData data = snapshot.getValue(NotificationData.class);
                        list1.add(0, data);
                    }
                    notiRv.setHasFixedSize(true);
                    notiRv.setLayoutManager(new LinearLayoutManager(FullNotificationActivity.this));
                    adapter = new NotificationAdapter(list1, FullNotificationActivity.this, "Notifications");
                    notiRv.setAdapter(adapter);
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(FullNotificationActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}