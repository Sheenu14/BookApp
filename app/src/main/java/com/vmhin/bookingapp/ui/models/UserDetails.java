package com.vmhin.bookingapp.ui.models;

public class UserDetails {

    public String name, email, gender, comment, id, userID;

    public UserDetails() {
    }

    public UserDetails(String name, String email, String gender, String comment, String userID, String id) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.comment = comment;
        this.userID = userID;
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
