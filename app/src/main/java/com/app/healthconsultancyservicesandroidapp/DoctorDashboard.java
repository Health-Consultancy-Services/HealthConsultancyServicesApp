package com.app.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorDashboard extends AppCompatActivity {

    ImageView logout;
    TextView emailId;
    String Email;
    TextView Name;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        logout =findViewById(R.id.logout);
        Name = (TextView) findViewById (R.id.nameHide) ;
        emailId = (TextView) findViewById (R.id.emailIdHide) ;
        Intent intent = getIntent ();
        Email =  intent.getStringExtra ("emailId");
        emailId.setText (Email);
        findDoctorByEmail();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(DoctorDashboard.this,MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(DoctorDashboard.this,"Successful Logout",Toast.LENGTH_SHORT).show();
            }
        });

        }

    private void findDoctorByEmail() {
        healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create(HealthConsultancyServicesApi.class);
        Intent intent = getIntent ();
        final String email = emailId.getText().toString();
        Call<Doctor> call = healthConsultancyServicesApi.findDoctorByEmail(email);
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if (!response.isSuccessful ()){
                    Toast.makeText (getApplicationContext (), response.code (), Toast.LENGTH_LONG).show ();
                    return;
                }
                Doctor doctor = response.body();
                Name.setText(doctor.getDoctorname());
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });

    }

    public void sendCall1(View view) {
        Intent intent = new Intent(this, DoctorProfile.class);
        String EmailID = emailId.getText ().toString ();
        intent.putExtra ("homepageDoctorEmailId", EmailID);
        startActivity(intent);
    }
    public void sendCall5(View view) {
        Intent intent = new Intent(this, ViewAppointment.class);
        String name= Name.getText ().toString ();
        intent.putExtra ("homepageDoctorName", name);
        startActivity(intent);
    }

    public void sendCall6(View view) {
        Intent intent = new Intent(this, ViewPatient.class);
        String name= Name.getText ().toString ();
        intent.putExtra ("homepageDoctorName", name);
        startActivity(intent);
    }
    }

