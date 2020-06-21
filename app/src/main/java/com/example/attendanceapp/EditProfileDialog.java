package com.example.attendanceapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;

public class EditProfileDialog extends DialogFragment {

    private Professor professor;
    private EditProfileListner listner;

    private TextInputEditText editUsername;
    private TextInputEditText editJobtitle;
    private TextInputEditText editUniversity;
    private TextInputEditText editFirstname;
    private TextInputEditText editLastname;
    private TextInputEditText editPhone;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        professor = (Professor) getArguments().getSerializable("professor");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_profile, null);

        builder.setView(view)
                .setTitle("Edit Information")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateProfessorObject();
                        listner.applyChange();
                    }
                });

        editUsername = view.findViewById(R.id.edit_username);
        editFirstname = view.findViewById(R.id.edit_firstname);
        editLastname = view.findViewById(R.id.edit_lastname);
        editUniversity = view.findViewById(R.id.edit_university);
        editJobtitle = view.findViewById(R.id.edit_jobtitle);
        editPhone = view.findViewById(R.id.edit_phone);

        initInput();

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listner = (EditProfileListner) getTargetFragment();
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + " Must " +
                    "implements EditProfileListner");
        }
    }

    public void initInput() {
        editUsername.setText(professor.getUsername());
        editUniversity.setText(professor.getUniversity());
        editJobtitle.setText(professor.getJobTitle());
        editFirstname.setText(professor.getFirstName());
        editLastname.setText(professor.getLastName());
        editPhone.setText(professor.getPhone());
    }

    public void updateProfessorObject() {

        professor.setUsername(editUsername.getText().toString());
        professor.setFirstName(editFirstname.getText().toString());
        professor.setLastName(editLastname.getText().toString());
        professor.setJobTitle(editJobtitle.getText().toString());
        professor.setUniversity(editUniversity.getText().toString());
        professor.setPhone(editPhone.getText().toString());

    }

    public static EditProfileDialog newInstance(Professor professor) {
        EditProfileDialog dialog = new EditProfileDialog();

        Bundle bundle = new Bundle();
        bundle.putSerializable("professor", professor);
        dialog.setArguments(bundle);

        return dialog;
    }

    public interface EditProfileListner {
        // interface used to send data back to fragment
        void applyChange();
    }
}
