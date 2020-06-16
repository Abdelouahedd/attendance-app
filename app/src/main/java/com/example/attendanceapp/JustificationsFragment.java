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

import java.util.ArrayList;
import java.util.List;

public class JustificationsFragment extends Fragment {

    private RecyclerView justificationList;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter viewAdapater;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_justification, container,
                false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<Justification> justifications = Justification.dataSource(10);

        justificationList = view.findViewById(R.id.justification_listview);
        layoutManager = new LinearLayoutManager(getContext());
        viewAdapater = new JustificationListAdapter(justifications);

        justificationList.setLayoutManager(layoutManager);
        justificationList.setAdapter(viewAdapater);
    }
}
