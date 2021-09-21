package com.example.sathkaaraya;

public class registrationDetails {

    private String firstname;
    private String lastName;
    private String email;
    private String country;
    private String phoneCode;
    private String phoneNumber;
    private String password;



    public registrationDetails() {
    }


    public registrationDetails(String firstname, String lastName, String email, String country,String phoneCode, String phoneNumber, String password) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.phoneCode=phoneCode;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }


    public String getFirstname() {
        return firstname;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
