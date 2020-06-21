package com.example.attendanceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClassesListAdapter extends RecyclerView.Adapter {

    // view holder for classes
    public static class ClassListViewHolder extends RecyclerView.ViewHolder {
        private TextView mClasseName;
        private TextView mSubjectName;
        private TextView mStudentsNumber;
        private TextView mclassCreationDate;

        public ClassListViewHolder(View itemView) {
            super(itemView);
            mClasseName = itemView.findViewById(R.id.class_name);
            mSubjectName = itemView.findViewById(R.id.subject_name);
            mStudentsNumber = itemView.findViewById(R.id.students_number);
            mclassCreationDate =
                    itemView.findViewById(R.id.class_creation_date);
        }
    }

    private List<Classroom> aClasses;
    public ClassesListAdapter(List<Classroom> aClasses) {
        this.aClasses = aClasses;
    }
    @NonNull
    @Override
    public ClassListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.classes_item,
                parent,
                false
        );
        ClassListViewHolder classViewHolder = new ClassListViewHolder(v);
        return classViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Classroom currentClassroom = aClasses.get(position);
        ClassListViewHolder classHolder = (ClassListViewHolder) holder;

        classHolder.mClasseName.setText(currentClassroom.getLabel());
        classHolder.mSubjectName.setText(currentClassroom.getSubject());
        classHolder.mStudentsNumber.setText(currentClassroom.getNumberStudents().toString());
        classHolder.mclassCreationDate.setText(currentClassroom.getCreatedAt().toString());
    }


    @Override
    public int getItemCount() {
        return aClasses.size();
    }
}
