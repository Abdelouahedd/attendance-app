package com.example.attendanceapp;

import java.io.Serializable;
import java.util.Date;

public class Justification implements Serializable {

    private String title;
    private String content;
    private Date createAt;
    private Boolean status;

    public Justification(String title, String content, Date createAt, Boolean status) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}