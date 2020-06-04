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

public class ClassesFragment extends Fragment {

    private RecyclerView classesList;
    private RecyclerView.Adapter classListAdapter;
    private RecyclerView.LayoutManager classListLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_classes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Classe> classes = Classe.dataSource(10);

        classesList = view.findViewById(R.id.classes_list_view);
        classesList.setHasFixedSize(true);
        classListAdapter = new ClassesListAdapter(classes);
        classListLayoutManager = new LinearLayoutManager(getContext());

        classesList.setLayoutManager(classListLayoutManager);
        classesList.setAdapter(classListAdapter);
    }
}
