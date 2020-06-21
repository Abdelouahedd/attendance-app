package com.example.attendanceapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;


public class ProfileFragment extends Fragment implements EditProfileDialog.EditProfileListner {

    Professor professor;

    private TextView usernameView;
    private TextView jobtitleview;

    private MaterialTextView firstNameView;
    private MaterialTextView lastNameView;
    private MaterialTextView universityView;
    private MaterialTextView emailView;
    private MaterialTextView passwordView;
    private MaterialTextView phoneView;

    private FloatingActionButton editButton;


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
        editButton = view.findViewById(R.id.edit_profile_info);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditDialog();
            }
        });

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

    private void openEditDialog() {
        EditProfileDialog editDialog = EditProfileDialog.newInstance(professor);
        editDialog.setTargetFragment(ProfileFragment.this, 1);
        editDialog.show(getActivity().getSupportFragmentManager(), "Edit " +
                "Dialog");
    }

    @Override
    public void applyChange() {
        ProfessorMainActivity activity = (ProfessorMainActivity) getActivity();
        activity.getProfDocumentRef().set(professor)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(getContext(), "Data Saved " +
                                "succesffully", Toast.LENGTH_LONG).show();;

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(getContext(), "Failed to save data",
                        Toast.LENGTH_LONG).show();
                Log.d("FIREBASE_DEBUG", e.toString());
            }
        });
        updateUI(professor);
    }
}
