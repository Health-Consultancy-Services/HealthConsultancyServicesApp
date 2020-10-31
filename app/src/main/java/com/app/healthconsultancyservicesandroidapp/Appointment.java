package com.app.healthconsultancyservicesandroidapp;




public class Appointment {


    private int appointment_id;
    private String doctorname;
    private String patientname;
    private String date;
    private String time_slot;
    private String status;

    public  Appointment(){}

    public Appointment(String doctorname, String patientname, String date, String time_slot, String status) {
        this.appointment_id = appointment_id;
        this.doctorname = doctorname;
        this.patientname = patientname;
        this.date = date;
        this.time_slot = time_slot;
        this.status = status;
    }

    public int getAppointment_id() {
        return appointment_id;
    }
    public void setAppointment_id(int appointment_id) {
        this.appointment_id = appointment_id;
    }
    public String getDoctorname() {
        return doctorname;
    }
    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }
    public String getPatientname() {
        return patientname;
    }
    public void setPatient_name(String patientname) {
        this.patientname = patientname;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime_slot() {
        return time_slot;
    }
    public void setTime_slot(String time_slot) {
        this.time_slot = time_slot;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}
