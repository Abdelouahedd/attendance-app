package com.example.attendanceapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentsFragment extends Fragment {

    private List<Student> students;
    private RecyclerView studentsList;
    private RecyclerView.Adapter viewAdapter;
    private RecyclerView.LayoutManager layout;

    public static StudentsFragment newInstance(List<Student> students) {
        StudentsFragment fragment = new StudentsFragment();
        Bundle bunlde = new Bundle();

        bunlde.putSerializable("students", (Serializable) students);
        fragment.setArguments(bunlde);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        students = (List<Student>) getArguments().getSerializable("students");
        return inflater.inflate(R.layout.fragment_students, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        studentsList = view.findViewById(R.id.students_list_view);
        viewAdapter = new StudentListAdapter(students);
        layout = new LinearLayoutManager(getContext());

        studentsList.setLayoutManager(layout);
        studentsList.setAdapter(viewAdapter);

        viewAdapter.notifyDataSetChanged();
    }
}
