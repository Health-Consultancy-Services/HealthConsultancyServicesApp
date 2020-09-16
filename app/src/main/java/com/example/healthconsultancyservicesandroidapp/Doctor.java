package com.example.healthconsultancyservicesandroidapp;
public class Doctor {
    private int doctor_id;
    private String doctor_name;
    private String age;
    private String phoneno;
    private String email;
    private String department;
    private String experience;
    private String address;
    private String qualification;
    private String gender;
    private String status;

    public Doctor(){}

    public Doctor(String doctor_name, String age, String phoneno, String email, String department, String experience, String address, String qualification, String gender, String status) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.age = age;
        this.phoneno = phoneno;
        this.email = email;
        this.department = department;
        this.experience = experience;
        this.address = address;
        this.qualification = qualification;
        this.gender = gender;
        this.status = status;
    }

    public int getDoctor_id() {
        return doctor_id;
    }
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }
    public String getDoctor_name() {
        return doctor_name;
    }
    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
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
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getExperience() {
        return experience;
    }
    public void setExperience(String experience) {
        this.experience = experience;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
