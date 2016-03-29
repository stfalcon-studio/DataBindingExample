package com.troy379.databindingexample.data.models;

public class User {

    public User(String name, String surname, String photo, String status,
                Counters counters, boolean isOnline, boolean isFriend) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.status = status;
        this.counters = counters;
        this.isOnline = isOnline;
        this.isFriend = isFriend;
    }

    private String name;
    private String surname;
    private String photo;
    private String status;
    private Counters counters;
    private boolean isOnline;
    private boolean isFriend;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoto() {
        return photo;
    }

    public String getStatus() {
        return status;
    }

    public Counters getCounters() {
        return counters;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public boolean isFriend() {
        return isFriend;
    }
}
