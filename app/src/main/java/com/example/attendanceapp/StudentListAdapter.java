package com.example.attendanceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentListAdapter extends RecyclerView.Adapter {

    private List<Student> students;

    public StudentListAdapter(List<Student> students) {
        this.students = students;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.students_item,
                parent,
                false
        );
        StudentViewHolder viewHolder = new StudentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        StudentViewHolder viewHolder = (StudentViewHolder) holder;
        Student student = students.get(position);
        Attendance attendance = student.getAttendance();

        viewHolder.studentName.setText(student.getFullname());
        viewHolder.studentGender.setText(student.getGender());
        viewHolder.studentPoints.setText(attendance.getPoints().toString());
        viewHolder.studentJoined.setText(attendance.getJoinedAt().toString());
        viewHolder.studentPresence.setText(attendance.getPresence().toString());
        viewHolder.studentAbsence.setText(attendance.getAbsence().toString());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }
    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        private TextView studentName;
        private TextView studentGender;
        private TextView studentPoints;
        private TextView studentJoined;
        private TextView studentPresence;
        private TextView studentAbsence;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName = itemView.findViewById(R.id.student_name);
            studentGender = itemView.findViewById(R.id.student_gender);
            studentPoints = itemView.findViewById(R.id.student_points);
            studentJoined = itemView.findViewById(R.id.student_joined);
            studentPresence = itemView.findViewById(R.id.student_present);
            studentAbsence = itemView.findViewById(R.id.student_absent);
        }
    }
}
