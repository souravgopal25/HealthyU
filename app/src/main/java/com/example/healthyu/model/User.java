package com.example.healthyu.model;

public class User {
    String name;
    String password;
    String mobile;
    String uid;
    String email;

    public User() {
    }

    public User(String name, String password, String mobile, String uid, String email) {
        this.name = name;
        this.password = password;
        this.mobile = mobile;
        this.uid = uid;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
