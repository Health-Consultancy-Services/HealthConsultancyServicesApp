package com.example.healthconsultancyservicesandroidapp;

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
}
