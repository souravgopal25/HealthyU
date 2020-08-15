package com.example.healthyu.model;

public class AppointmentRequest {

   String description;
   String doctorId;
   String name;


   /* 0  => pending
    1  =>accepted
    -1=>Rejected;*/

    public AppointmentRequest( String description, String doctorId, String name) {

        this.description = description;
        this.doctorId = doctorId;
        this.name = name;

    }



    public AppointmentRequest() {
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
