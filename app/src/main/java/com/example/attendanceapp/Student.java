package com.example.attendanceapp;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable {

    private String uuid;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String gender;

    private Attendance attendance;

    public Student() {
        // neede by firebase
    }

    public Student(String uuid, String email, String firstName, String lastName, String username, String gender) {
        this.uuid = uuid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.gender = gender;
    }

    public static List<Student> datasource(int i) {
        return null;
    }

    public String getFullname() {
        return lastName + " " + firstName;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
