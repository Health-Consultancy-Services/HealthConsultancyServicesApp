package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPatient extends AppCompatActivity {
    TextView doctorname;
    ArrayList<Appointment> appointments=new ArrayList<>();
    private PatientAdapter patientAdapter;
    private RecyclerView appointment_recyclerview;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);
        appointment_recyclerview=(RecyclerView)findViewById(R.id.appointment_recyclerview);
        appointment_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        doctorname =(TextView) findViewById(R.id.doctorname);
        Intent intent = getIntent ();
        final String name = intent.getStringExtra ("homepageDoctorName");
        doctorname.setText (name);
        findByDoctorname();
    }

    private void findByDoctorname() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        Intent intent = getIntent ();
        final String dname = doctorname.getText().toString();
        Call<List<Appointment>> call = healthConsultancyServicesApi.findByDoctorname(dname);
        call.enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                appointments=new ArrayList<>(response.body());
                patientAdapter=new PatientAdapter(ViewPatient.this,appointments);
                appointment_recyclerview.setAdapter(patientAdapter);
                Toast.makeText(ViewPatient.this,"Success",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {

            }
        });
    }
}