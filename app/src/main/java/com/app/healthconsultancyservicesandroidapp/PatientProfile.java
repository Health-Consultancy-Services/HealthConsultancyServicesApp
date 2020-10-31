package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button back;
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
        back = (Button) findViewById(R.id.btnback);

        Intent intent = getIntent ();
        final String EmailID = intent.getStringExtra ("homepagePatientEmailId");
        emailid.setText (EmailID);
        findByEmail();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailid.getText ().toString ();
                Intent intent =new Intent(PatientProfile.this,PatientDashboard.class);
                intent.putExtra("emailId", email);
                startActivity(intent);
            }
        });
    }

    private void findByEmail() {
        healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create(HealthConsultancyServicesApi.class);
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
    public void patientedit(View view) {
        Intent intent = new Intent(this, update_patient.class);
        String EmailID = emailid.getText ().toString ();
        intent.putExtra ("PatientEmailId", EmailID);
        startActivity(intent);
    }
}