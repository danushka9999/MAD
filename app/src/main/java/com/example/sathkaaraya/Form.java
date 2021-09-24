package com.example.sathkaaraya;

import com.google.firebase.database.Exclude;

public class Form {
    private String name;
    private Integer roomNo;
    private String startingDate;
    private String endingDate;
    private Integer noOfAdults;
    private Integer noOfChildren;
//    private String userId;


//    @Exclude
//    public String getUserId() {
//        return userId;
//    }
//
//    @Exclude
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public Form() {
    }

    public Form (String name, Integer roomNo){
        this.name = name;
        this.roomNo = roomNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }

    public Integer getNoOfAdults() {
        return noOfAdults;
    }

    public void setNoOfAdults(Integer noOfAdults) {
        this.noOfAdults = noOfAdults;
    }

    public Integer getNoOfChildren() {
        return noOfChildren;
    }

    public void setNoOfChildren(Integer noOfChildren) {
        this.noOfChildren = noOfChildren;
    }
}
