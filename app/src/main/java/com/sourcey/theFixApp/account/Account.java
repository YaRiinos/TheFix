package com.sourcey.theFixApp.account;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Account {

    private String id;
    private String name,
            address,
            email,
            mobile,
            password;

    public Account(){}

    public Account(String id, String name, String address, String email, String mobile, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
