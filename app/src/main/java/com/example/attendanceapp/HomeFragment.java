package com.example.attendanceapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;

public class HomeFragment extends Fragment {

    private TextView dateTextView;
    private TextView fullnameTextview;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dateTextView  = view.findViewById(R.id.hm_date);
        dateTextView.setText(getFormattedDate());

        fullnameTextview = view.findViewById(R.id.hm_user_fullname);
        Bundle bundle = this.getArguments();
        if ( bundle != null ) {

            String fullname = bundle.getString("fullname");
            fullnameTextview.setText(fullname);

        }
    }

    public static HomeFragment newInstance(String fullname) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();

        bundle.putString("fullname", fullname);
        fragment.setArguments(bundle);

        return fragment;
    }
    private String getFormattedDate() {

        String day = LocalDate.now().getDayOfWeek().name();
        String month = LocalDate.now().getMonth().name();
        String dayNumber = String.valueOf(LocalDate.now().getDayOfMonth());
        String year = String.valueOf(LocalDate.now().getYear());

        return String.format("%s %s %s, %s",
                day, month, dayNumber, year);
    }
}
