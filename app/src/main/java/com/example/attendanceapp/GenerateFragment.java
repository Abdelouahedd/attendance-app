package com.example.attendanceapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.io.Serializable;
import java.util.List;

public class GenerateFragment extends Fragment {

    private List<Classroom> classrooms;
    private MaterialButton generateButton;
    private Spinner classroomsSpinner;
    private ArrayAdapter<Classroom> spinnerAdapter;
    private SeekBar durationSeekBar;
    private TextView durationTextview;
    private SwitchMaterial linkSwitch;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        classrooms = (List<Classroom>) getArguments().getSerializable(
                "classrooms");
        return inflater.inflate(R.layout.fragment_generate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classroomsSpinner = view.findViewById(R.id.classes_spinner);
        generateButton = view.findViewById(R.id.generate_button);
        durationSeekBar = view.findViewById(R.id.duration);
        durationTextview = view.findViewById(R.id.duration_textview);
        linkSwitch = view.findViewById(R.id.link_switch);

        spinnerAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, classrooms);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classroomsSpinner.setAdapter(spinnerAdapter);


        durationSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                durationTextview.setText(progress + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // do nothing
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int duration = durationSeekBar.getProgress();
                Boolean doGenerateLink = linkSwitch.isChecked();
                Classroom classroom = (Classroom) classroomsSpinner.getSelectedItem();

                generateQrCode(classroom, duration, doGenerateLink);
            }
        });
    }

    public static GenerateFragment newInstance(List<Classroom> classrooms) {
        GenerateFragment fragment = new GenerateFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("classrooms", (Serializable) classrooms);
        fragment.setArguments(bundle);

        return fragment;

    }

    private void generateQrCode(Classroom classroom, int duration,
                                Boolean geneateLink) {
        String msg = classroom +  " " + duration + " " + geneateLink;
        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
    }
}
