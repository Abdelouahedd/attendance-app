package com.example.attendanceapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

public class AddClassroomDialog extends DialogFragment {

    private TextInputEditText label;
    private TextInputEditText subject;
    private TextInputEditText sessions;
    private Classroom classroom;
    private SaveClassroomInterface listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        classroom = (Classroom) getArguments().getSerializable("classroom");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_classroom, null);

        builder.setView(view).setTitle("Add New Classroom")
            .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // save new classroom

                    String classroomLabel = label.getText().toString();
                    String classroomSubject = subject.getText().toString();
                    String numberOfSession = sessions.getText().toString();

                    if ( validateInput(classroomLabel, classroomSubject,
                            numberOfSession) ){

                        classroom.setLabel(classroomLabel);
                        classroom.setSubject(classroomSubject);
                        classroom.setNumberSession(Integer.valueOf(numberOfSession));

                        listener.saveClassroom();

                    } else {

                        Toast.makeText(getContext(), "Invalid Input",
                                Toast.LENGTH_LONG).show();
                    }
                }
            });

        label = view.findViewById(R.id.classroom_label);
        subject = view.findViewById(R.id.classroom_subject);
        sessions = view.findViewById(R.id.classroom_sessions);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (SaveClassroomInterface) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must " +
                    "implments SaveClassroomInterface");
        }
    }

    public static AddClassroomDialog newInstance(Classroom classroom) {
        AddClassroomDialog dialog = new AddClassroomDialog();
        Bundle bundle = new Bundle();

        bundle.putSerializable("classroom", classroom);
        dialog.setArguments(bundle);

        return dialog;
    }
    private Boolean validateInput(String label, String subject,
                                 String sessions) {

        if ( label.length() == 0 || subject.length() == 0 )
            return false;

        try {
            Integer numberOfSessions = Integer.valueOf(sessions);
            if ( numberOfSessions <= 0 )
                return false;
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public interface SaveClassroomInterface {
        public void saveClassroom();
    }
}
