package com.example.sathkaaraya;

public class Meals {
    private String roomNo;
    private String foodtype;
    private String quantity;
//    private String unitPrice;

    public Meals() {
    }

    //Overloaded constructor
    public Meals(String roomNo, String foodtype, String quantity, String unitPrice) {
        this.roomNo = roomNo;
        this.foodtype = foodtype;
        this.quantity = quantity;
//        this.unitPrice = unitPrice;
    }

    //Getters
    public String getRoomNo() {
        return roomNo;
    }

    public String getFoodtype() {
        return foodtype;
    }

    public String getQuantity() {
        return quantity;
    }

//    public String getUnitPrice() {
//        return unitPrice;
//    }

    //Setters
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setFoodtype(String foodtype) {
        this.foodtype = foodtype;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

//    public void setUnitPrice(String unitPrice) {
//        this.unitPrice = unitPrice;
//    }
}
