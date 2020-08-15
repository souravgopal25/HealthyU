package com.example.healthyu.model;

import android.os.Parcelable;

public class Doctor {
    String name,drid,age,sex,phone,email,uid,dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrid() {
        return drid;
    }

    public void setDrid(String drid) {
        this.drid = drid;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String gender) {
        this.sex = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Doctor(String name, String drid, String age, String sex, String phone, String email, String dept) {
        this.name = name;
        this.drid = drid;
        this.age = age;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.dept = dept;
    }

    public Doctor() {
    }
}
