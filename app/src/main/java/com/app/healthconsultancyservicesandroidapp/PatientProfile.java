package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PatientProfile extends AppCompatActivity {
    TextView name;
    TextView age;
    TextView phone;
    TextView email_id;
    TextView emailid;
    TextView gender;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);
        emailid = (TextView) findViewById (R.id.emailIdHide) ;
        email_id = (TextView) findViewById(R.id.email);
        name = (TextView) findViewById (R.id.name) ;
        age = (TextView) findViewById (R.id.age) ;
        phone = (TextView) findViewById (R.id.phone) ;
        gender = (TextView) findViewById (R.id.gender) ;

        Intent intent = getIntent ();
        final String EmailID = intent.getStringExtra ("homepagePatientEmailId");
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
                        name.setText(patient.getPatient_name());
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
}