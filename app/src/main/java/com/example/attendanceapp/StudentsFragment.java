package com.example.attendanceapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsFragment extends Fragment {

    private RecyclerView studentsList;
    private RecyclerView.Adapter viewAdapter;
    private RecyclerView.LayoutManager layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_students, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Student> data = Student.datasource(10);

        studentsList = view.findViewById(R.id.students_list_view);
        viewAdapter = new StudentListAdapter(data);
        layout = new LinearLayoutManager(getContext());

        studentsList.setLayoutManager(layout);
        studentsList.setAdapter(viewAdapter);
    }
}
