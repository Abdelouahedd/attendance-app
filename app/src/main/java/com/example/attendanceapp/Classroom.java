package com.example.attendanceapp;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Classroom implements Serializable {

    private String label;
    private String subject;
    private Integer numberStudents;
    private Date createdAt;
    private String invitationCode;
    private Integer numberSession;
    private String professorUUID;

    public Classroom() {
        // needed by firebase
    }

    public Classroom(String label, String subject, Integer numberStudents, Date createdAt, String invitationCode, Integer numberSession, String professorUUID) {
        this.label = label;
        this.subject = subject;
        this.numberStudents = numberStudents;
        this.createdAt = createdAt;
        this.invitationCode = invitationCode;
        this.numberSession = numberSession;
        this.professorUUID = professorUUID;
    }

    public Classroom(String label, String subject, Integer numberStudents, Date createdAt) {
        this.label = label;
        this.subject = subject;
        this.numberStudents = numberStudents;
        this.createdAt = createdAt;
    }

    public static List<Classroom> dataSource (Integer size) {
        List<Classroom> data = new ArrayList<>(size);

        for (int i = 0; i < size; i++)
            data.add(new Classroom(
                    "Classe " + i,
                    "subject " + i,
                    i * 10,
                    new Date()
            ));

        return data;
    }

    @NonNull
    @Override
    public String toString() {
        return label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(Integer numberStudents) {
        this.numberStudents = numberStudents;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public Integer getNumberSession() {
        return numberSession;
    }

    public void setNumberSession(Integer numberSession) {
        this.numberSession = numberSession;
    }

    public String getProfessorUUID() {
        return professorUUID;
    }

    public void setProfessorUUID(String professorUUID) {
        this.professorUUID = professorUUID;
    }
}
