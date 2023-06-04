package com.trodev.dailyayah;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AyatFragment extends Fragment {

    private RecyclerView ayatRv;
    private List<UploadAllAyats> list1;
    private AyatAdapter adapter;
    private DatabaseReference reference, dbRef;


    public AyatFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ayat, container, false);

        ayatRv = view.findViewById(R.id.ayatRv);

        reference = FirebaseDatabase.getInstance().getReference().child("Daily Ayahs");

        AyatDataAll();

        return  view;
    }

    private void AyatDataAll() {

        dbRef = reference.child("Daily Ayat");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list1 = new ArrayList<>();
                if(!dataSnapshot.exists())
                {
                    // progressDialog.show();
                    ayatRv.setVisibility(View.VISIBLE); // change
                }
                else
                {
                    // progressDialog.hide();
                    ayatRv.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        UploadAllAyats data = snapshot.getValue(UploadAllAyats.class);
                        list1.add(0,data);
                    }
                    ayatRv.setHasFixedSize(true);
                    ayatRv.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new AyatAdapter(list1,getContext(),"Daily Ayat");
                    ayatRv.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // progressDialog.hide();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}