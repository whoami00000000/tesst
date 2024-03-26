package com.example.oechappfinal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ((HomeActivity) requireActivity()).showToolbar();
        ((HomeActivity) requireActivity()).getSupportActionBar().setTitle("Profile");
        ((HomeActivity) requireActivity()).getSupportActionBar();

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}