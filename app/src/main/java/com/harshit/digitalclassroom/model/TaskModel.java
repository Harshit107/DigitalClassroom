package com.harshit.digitalclassroom.model;

public class TaskModel {

    String id;
    String status;
    String creation;
    String lastupdate;
    String description;
    String title;

    public TaskModel(String id, String status, String creation, String lastupdate, String description, String title) {
        this.id = id;
        this.status = status;
        this.creation = creation;
        this.lastupdate = lastupdate;
        this.description = description;
        this.title = title;
    }

    public TaskModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreation() {
        return creation;
    }

    public void setCreation(String creation) {
        this.creation = creation;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
