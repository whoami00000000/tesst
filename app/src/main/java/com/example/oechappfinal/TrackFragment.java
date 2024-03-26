package com.example.oechappfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TrackFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((HomeActivity) requireActivity()).hideToolbar();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_track, container, false);
    }
}