package com.trodev.dailyayat.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.trodev.dailyayat.R;
import com.trodev.dailyayat.islamic_books.BooksAdapter;
import com.trodev.dailyayat.islamic_books.BooksModel;

import java.util.ArrayList;


public class BooksFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    DatabaseReference reference;
    ArrayList<BooksModel> model;
    BooksAdapter adapter;

    public BooksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);

        /*init views*/
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        loadBooks();

        return view;
    }

    private void loadBooks() {

        model = new ArrayList<>();

        /*adapter set on model*/
        adapter = new BooksAdapter(model, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        /*database child and get data from database*/
        reference = FirebaseDatabase.getInstance().getReference().child("islamic_books");


        /*data view on firebase database get data from on reference*/
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    progressBar.setVisibility(View.GONE);

                    BooksModel govtModel = dataSnapshot.getValue(BooksModel.class);
                    model.add(0, govtModel);
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        reference.keepSynced(true);

    }
}