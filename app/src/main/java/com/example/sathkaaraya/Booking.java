package com.example.sathkaaraya;

public class Booking {
    private String time;
    private String date;
    //Default Constructor
    public Booking() {
    }
    //Paramterized Constructor
    public Booking(String time, String date) {
        this.time = time;
        this.date = date;
    }
    //Getters
    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
    //Setters
    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
