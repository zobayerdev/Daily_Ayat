package com.trodev.dailyayah;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class WallpaperFragment extends Fragment {

    public WallpaperFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View  view =  inflater.inflate(R.layout.fragment_wallpaper, container, false);

       return view;
    }
}