package com.example.attendanceapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

import java.io.Serializable;

public class ProfileFragment extends Fragment {

    Professor professor;
    private TextView usernameView;
    private TextView jobtitleview;
    private MaterialTextView firstNameView;
    private MaterialTextView lastNameView;
    private MaterialTextView universityView;
    private MaterialTextView emailView;
    private MaterialTextView passwordView;
    private MaterialTextView phoneView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        professor = (Professor) getArguments().getSerializable("professor");
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        usernameView = view.findViewById(R.id.username);
        jobtitleview = view.findViewById(R.id.job_title);
        firstNameView = view.findViewById(R.id.first_name);
        lastNameView = view.findViewById(R.id.last_name);
        universityView = view.findViewById(R.id.university);
        emailView = view.findViewById(R.id.email);
        passwordView = view.findViewById(R.id.password);
        phoneView  = view.findViewById(R.id.phone);

        updateUI(this.professor);
    }

    public static ProfileFragment newInstance(Professor professor) {

        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("professor", professor);
        fragment.setArguments(bundle);

        return fragment;
    }

    private void updateUI(Professor professor) {

        usernameView.setText(professor.getUsername());
        jobtitleview.setText(professor.getJobTitle());
        firstNameView.setText(professor.getFirstName());
        lastNameView.setText(professor.getLastName());
        universityView.setText(professor.getUniversity());
        emailView.setText(professor.getEmail());
        passwordView.setText(professor.getPassword());
        phoneView.setText(professor.getPhone());

    }
}
