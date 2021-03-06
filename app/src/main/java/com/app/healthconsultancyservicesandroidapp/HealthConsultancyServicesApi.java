package com.app.healthconsultancyservicesandroidapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface HealthConsultancyServicesApi {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://172.20.10.3:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @POST("patient")
    Call<Patient> savePatient(@Body Patient patient);

    @POST("doctor")
    Call<Doctor> saveDoctor(@Body Doctor doctor);

    @PUT("doctor")
    Call<Doctor> update(@Body Doctor doctor);

    @PUT("patient")
    Call<Patient> updatePatient(@Body Patient patient);

    @POST("user")
    Call<User> saveUser(@Body User user);

    @POST("appointment")
    Call<Appointment> saveAppointment(@Body Appointment appointment);

    @GET("login/{email}/{password}/{role}")
    Call<User> findByEmailAndPasswordAndRole(@Path("email") String email,@Path("password") String password,@Path("role") String role);

    @GET("/forgotpassword/{email}/{password}")
    Call<Integer> ForgotPassword(@Path("email") String email,@Path("password") String password);


    @GET("/changepassword/{email}/{password}/{newpassword}")
    Call<Integer> ChangePassword(@Path("email") String email,@Path("password") String password,@Path("newpassword") String newpassword);

    @GET("/patientbyemail/{email}")
    Call<Patient> findByEmail(@Path("email") String email);

    @GET("/doctorbyemail/{email}")
    Call<Doctor> findDoctorByEmail(@Path("email") String email);

    @GET("/doctorbydepartment/{department}")
    Call<List<Doctor>> findByDepartment(@Path("department") String department);

    @GET("/appointmentbydoctorname/{doctorname}")
    Call<List<Appointment>> findByDoctorname(@Path("doctorname") String doctorname);

    @GET("/appointmentbypatientname/{patientname}")
    Call<List<Appointment>> findByPatientname(@Path("patientname") String patientname);

    @GET("/acceptappointment/{patientname}")
    Call<Appointment> AcceptAppointment(@Path("patientname") String patientname);

    @GET("/declineappointment/{patientname}")
    Call<Appointment> DeclineAppointment(@Path("patientname") String patientname);

}
