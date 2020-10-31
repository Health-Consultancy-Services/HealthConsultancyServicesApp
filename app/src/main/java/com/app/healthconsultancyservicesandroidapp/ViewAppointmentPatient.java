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

public class ViewAppointmentPatient extends AppCompatActivity {
    TextView patientname;
    ArrayList<Appointment> appointments=new ArrayList<>();
    private AppointmentPatientAdapter appointmentPatientAdapter;
    private RecyclerView appointment_recyclerview;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_appointment_patient);
        appointment_recyclerview=(RecyclerView)findViewById(R.id.appointment_recyclerview);
        appointment_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        patientname =(TextView) findViewById(R.id.patientname);
        Intent intent = getIntent ();
        final String name = intent.getStringExtra ("homepagePatientName");
        patientname.setText (name);
        findByPatientname();
    }

    private void findByPatientname() {
        healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create(HealthConsultancyServicesApi.class);
        Intent intent = getIntent ();
        final String pname = patientname.getText().toString();
        Call<List<Appointment>> call = healthConsultancyServicesApi.findByPatientname(pname);
        call.enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                appointments=new ArrayList<>(response.body());
                appointmentPatientAdapter=new AppointmentPatientAdapter(ViewAppointmentPatient.this,appointments);
                appointment_recyclerview.setAdapter(appointmentPatientAdapter);

            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {

            }
        });
    }
}