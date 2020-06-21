package com.example.attendanceapp;

import android.os.Bundle;
import android.os.strictmode.CleartextNetworkViolation;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ClassesFragment extends Fragment implements AddClassroomDialog.SaveClassroomInterface {

    private RecyclerView classesList;
    private RecyclerView.Adapter classListAdapter;
    private RecyclerView.LayoutManager classListLayoutManager;
    private FloatingActionButton addClassroomButton;
    private List<Classroom> classrooms;
    private Classroom newClassroom;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        classrooms = (List<Classroom>) getArguments().getSerializable("classrooms");
        return inflater.inflate(R.layout.fragment_classes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        classesList = view.findViewById(R.id.classes_list_view);
        classesList.setHasFixedSize(true);
        classListAdapter = new ClassesListAdapter(classrooms);
        classListLayoutManager = new LinearLayoutManager(getContext());

        classesList.setLayoutManager(classListLayoutManager);
        classesList.setAdapter(classListAdapter);

        addClassroomButton = view.findViewById(R.id.add_classroom);
        addClassroomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newClassroom = new Classroom();
                AddClassroomDialog dialog =
                        AddClassroomDialog.newInstance(newClassroom);
                dialog.setTargetFragment(ClassesFragment.this, 2);
                dialog.show(getActivity().getSupportFragmentManager(), "Add " +
                        "Classroom");
            }
        });
    }

    public static ClassesFragment newInstance(List<Classroom> classrooms) {
        ClassesFragment fragment = new ClassesFragment();
        Bundle bundle = new Bundle();

        bundle.putSerializable("classrooms", (Serializable) classrooms);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void saveClassroom() {

        String uuid =
                ((ProfessorMainActivity) getActivity()).getProfessorUUID();
        FirebaseFirestore db = ((ProfessorMainActivity)getActivity()).getDb();

        newClassroom.setCreatedAt(new Date());
        newClassroom.setInvitationCode(generateInvitationCode());
        newClassroom.setNumberStudents(0);
        newClassroom.setProfessorUUID(uuid);

        db.collection("classrooms")
                .document()
                .set(newClassroom)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Classroom Added",
                                Toast.LENGTH_LONG).show();
                        classrooms.add(newClassroom);
                        classListAdapter.notifyItemInserted(classrooms.size() - 1);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), ("Failed to save " +
                                "classroom"), Toast.LENGTH_LONG).show();
                        Log.d("FIREBASE_DEBUG", "onFailure: " + e.getMessage());
                    }
                });
    }

    public String generateInvitationCode() {
        StringBuilder invitationCode = new StringBuilder(20);
        int minAscii = 97;
        int maxAscii = 122;
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            invitationCode.append((char) (rand.nextInt((maxAscii - minAscii) + 1) + minAscii));
            invitationCode.append(rand.nextInt(10));
        }
        return invitationCode.toString();
    }
}
