package com.example.healthconsultancyservicesandroidapp;

public class Patient {
    private int patient_id;
    private String patient_name;
    private String age;
    private String phoneno;
    private String email;
    private String gender;

    public Patient(){
    }

    public Patient(String patient_name, String age, String phoneno, String email, String gender) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.age = age;
        this.phoneno = phoneno;
        this.email = email;
        this.gender = gender;
    }

    public int getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }
    public String getPatient_name() {
        return patient_name;
    }
    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }
    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getPhoneno() {
        return phoneno;
    }
    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }


}

