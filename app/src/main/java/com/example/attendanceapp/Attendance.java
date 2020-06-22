package com.example.attendanceapp;

import java.io.Serializable;
import java.util.Date;

public class Attendance implements Serializable {

    private String student;
    private String classroom;
    private Date joinedAt;
    private Integer points;
    private Integer absence;
    private Integer presence;

    public Attendance() {
        // neede by firebase
    }

    public Attendance(String student, String classroom, Date joinedAt, Integer points, Integer absence, Integer presence) {
        this.student = student;
        this.classroom = classroom;
        this.joinedAt = joinedAt;
        this.points = points;
        this.absence = absence;
        this.presence = presence;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getAbsence() {
        return absence;
    }

    public void setAbsence(Integer absence) {
        this.absence = absence;
    }

    public Integer getPresence() {
        return presence;
    }

    public void setPresence(Integer presence) {
        this.presence = presence;
    }
}
