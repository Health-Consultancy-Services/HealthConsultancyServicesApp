package com.app.healthconsultancyservicesandroidapp;

public class Patient {
    private String patient_id;
    private String patientname;
    private String age;
    private String phoneno;
    private String email;
    private String gender;

    public Patient(){
    }

    public Patient(String patientname, String age, String phoneno, String email, String gender) {
        this.patient_id = patient_id;
        this.patientname = patientname;
        this.age = age;
        this.phoneno = phoneno;
        this.email = email;
        this.gender = gender;
    }

    public Patient(String patient_id,String patientname, String age, String phoneno, String email, String gender) {
        this.patient_id = patient_id;
        this.patientname = patientname;
        this.age = age;
        this.phoneno = phoneno;
        this.email = email;
        this.gender = gender;
    }

    public String getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
    public String getPatientname() {
        return patientname;
    }
    public void setPatientname(String patientname) {
        this.patientname = patientname;
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

