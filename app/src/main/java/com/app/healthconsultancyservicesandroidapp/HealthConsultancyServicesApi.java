package com.app.healthconsultancyservicesandroidapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface HealthConsultancyServicesApi {
    @POST("patient")
    Call<Patient> savePatient(@Body Patient patient);

    @POST("doctor")
    Call<Doctor> saveDoctor(@Body Doctor doctor);

    @POST("user")
    Call<User> saveUser(@Body User user);

    @POST("appointment")
    Call<Appointment> saveAppointment(@Body Appointment appointment);

    @PUT("doctor")
    Call<Doctor> update(@Body Doctor doctor);

    @PUT("patient")
    Call<Patient> updatePatient(@Body Patient patient);

    @GET("/appointmentbydoctorname/{doctorname}")
    Call<List<Appointment>> findByDoctorname(@Path("doctorname") String doctorname);

    @GET("login/{email}/{password}/{role}")
    Call<User> findByEmailAndPasswordAndRole(@Path("email") String email,@Path("password") String password,@Path("role") String role);

    @GET("/forgotpassword/{email}/{password}")
    Call<Integer> ForgotPassword(@Path("email") String email,@Path("password") String password);

    @GET("/patientbyemail/{email}")
    Call<Patient> findByEmail(@Path("email") String email);

    @GET("/doctorbyemail/{email}")
    Call<Doctor> findDoctorByEmail(@Path("email") String email);

    @GET("/doctorbydepartment/{department}")
    Call<List<Doctor>> findByDepartment(@Path("department") String department);

    @GET("/acceptappointment/{patientname}")
    Call<Appointment> AcceptAppointment(@Path("patientname") String patientname);

    @GET("/declineappointment/{patientname}")
    Call<Appointment> DeclineAppointment(@Path("patientname") String patientname);


}
