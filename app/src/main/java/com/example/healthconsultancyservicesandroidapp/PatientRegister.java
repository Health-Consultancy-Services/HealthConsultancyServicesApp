package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientRegister extends AppCompatActivity {
    EditText patient_name;
    EditText age;
    EditText phoneno;
    EditText email;
    EditText gender;
    Button btn;
private  HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        patient_name=(EditText) findViewById(R.id.name);
        age=(EditText) findViewById(R.id.age);
        phoneno=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email);
        gender=(EditText) findViewById(R.id.gender);
        btn = findViewById(R.id.btn);

        Retrofit retrofit = new Retrofit.Builder ()
                .baseUrl ("http://192.168.1.101:8080/")
                .addConverterFactory (GsonConverterFactory.create ())
                .build ();
        healthConsultancyServicesApi = retrofit.create (HealthConsultancyServicesApi.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Patient patient = new Patient(patient_name.getText().toString(), age.getText().toString(), phoneno.getText().toString(), email.getText().toString(), gender.getText().toString());
                if (patient_name.getText().toString().isEmpty()) {
                    patient_name.setError("Name can't be empty");
                } else if (age.getText().toString().isEmpty()) {
                    age.setError("age can't be empty");
                } else if (phoneno.getText().toString().isEmpty()) {
                    phoneno.setError("Enter a valid number");
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Email can't be empty");

                } else if (gender.getText().toString().isEmpty()) {
                    gender.setError("Gender can't be empty");

                } else
                    {
                    savePatient(patient);
                }
            }
        });
    }

    private void savePatient(Patient patient) {
        Call<Patient> call = healthConsultancyServicesApi.savePatient(patient);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                Intent intent = new Intent(PatientRegister.this,PaitentLoginCred.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {

            }
        });

        }
    }


