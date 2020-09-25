package com.harshit.digitalclassroom.utils;

public class TaskList {

    String title;
    String creationDate;
    String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TaskList(String title, String creationDate, String status) {
        this.title = title;
        this.creationDate = creationDate;
        this.status = status;
    }
}
