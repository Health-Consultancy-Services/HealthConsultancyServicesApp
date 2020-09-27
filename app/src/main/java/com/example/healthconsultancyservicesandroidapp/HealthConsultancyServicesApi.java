package com.example.healthconsultancyservicesandroidapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface HealthConsultancyServicesApi {
    @POST("patient")
    Call<Patient> savePatient(@Body Patient patient);

    @POST("doctor")
    Call<Doctor> saveDoctor(@Body Doctor doctor);

    @POST("user")
    Call<User> saveUser(@Body User user);

    @GET("login/{email}/{password}/{role}")
    Call<User> findByEmailAndPasswordAndRole(@Path("email") String email,@Path("password") String password,@Path("role") String role);

    @GET("/forgotpassword/{email}/{password}")
    Call<Integer> ForgotPassword(@Path("email") String email,@Path("password") String password);

    @GET("/patientbyemail/{email}")
    Call<Patient> findByEmail(@Path("email") String email);

    @GET("/doctorbyemail/{email}")
    Call<Doctor> findDoctorByEmail(@Path("email") String email);

    @GET("/doctorbyemail/{department}")
    Call<List<Doctor>> findByDepartment(@Path("department") String department);
}
