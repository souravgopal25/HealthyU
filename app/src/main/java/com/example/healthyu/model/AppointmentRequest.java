package com.example.healthyu.model;

public class AppointmentRequest {
   String dept;
   String description;
   String doctorId;
   String name;
   int position;

   /* 0  => pending
    1  =>accepted
    -1=>Rejected;*/

    public AppointmentRequest(String dept, String description, String doctorId, String name) {
        this.dept = dept;
        this.description = description;
        this.doctorId = doctorId;
        this.name = name;
        this.position=0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public AppointmentRequest() {
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
