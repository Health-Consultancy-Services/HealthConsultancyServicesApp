package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class update_patient extends AppCompatActivity {
    TextView name;
    TextView patient_id;
    TextView age;
    EditText phone;
    TextView email_id;
    TextView emailid;
    TextView gender;
    Button home;
    Button submit;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_patient);
        emailid = (TextView) findViewById (R.id.emailIdHide) ;
        email_id = (TextView) findViewById(R.id.email);
        patient_id = (TextView) findViewById(R.id.patient_id);
        name = (TextView) findViewById (R.id.name) ;
        age = (TextView) findViewById (R.id.age) ;
        phone = (EditText) findViewById (R.id.phone) ;
        gender = (TextView) findViewById (R.id.gender) ;
        home = (Button) findViewById(R.id.btnhome);
        submit = (Button) findViewById(R.id.btnsubmit);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailid.getText ().toString ();
                Intent intent =new Intent(update_patient.this,PatientDashboard.class);
                intent.putExtra("emailId", email);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient patient = new Patient(patient_id.getText().toString(),name.getText().toString(), age.getText().toString(), phone.getText().toString(), email_id.getText().toString(),gender.getText().toString());
                updatePatient(patient);
            }
        });
        Intent intent = getIntent ();
        final String EmailID = intent.getStringExtra ("PatientEmailId");
        emailid.setText (EmailID);
        findByEmail();
    }
    private void findByEmail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        Intent intent = getIntent ();
        final String email = emailid.getText().toString();
        Call<Patient> call = healthConsultancyServicesApi.findByEmail(email);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (!response.isSuccessful ()){
                    Toast.makeText (getApplicationContext (), response.code (), Toast.LENGTH_LONG).show ();
                    return;
                }
                Patient patient = response.body();
                patient_id.setText(patient.getPatient_id());
                name.setText(patient.getPatientname());
                age.setText(patient.getAge());
                phone.setText(patient.getPhoneno());
                email_id.setText(patient.getEmail());
                gender.setText(patient.getGender());
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });

    }
    private void updatePatient(Patient patient) {
        Call<Patient> call =healthConsultancyServicesApi.updatePatient(patient);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                final String email = emailid.getText ().toString ();
                Intent intent =new Intent(update_patient.this,PatientDashboard.class);
                intent.putExtra("emailId", email);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });
    }
}