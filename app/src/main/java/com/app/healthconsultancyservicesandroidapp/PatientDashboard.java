package com.app.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientDashboard extends AppCompatActivity {
    ImageView logout;
    TextView emailId;
    TextView Name;
    String Email;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        logout =findViewById(R.id.logout);
        emailId = (TextView) findViewById (R.id.emailIdHide) ;
        Name = (TextView) findViewById (R.id.nameHide) ;
        Intent intent = getIntent ();
        Email =  intent.getStringExtra ("emailId");
        emailId.setText (Email);
        findByEmail();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(PatientDashboard.this,MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(PatientDashboard.this,"Successful Logout",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void findByEmail() {
        healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create(HealthConsultancyServicesApi.class);
        Intent intent = getIntent ();
        final String email = emailId.getText().toString();
        Call<Patient> call = healthConsultancyServicesApi.findByEmail(email);
        call.enqueue(new Callback<Patient>() {
            @Override
            public void onResponse(Call<Patient> call, Response<Patient> response) {
                if (!response.isSuccessful ()){
                    Toast.makeText (getApplicationContext (), response.code (), Toast.LENGTH_LONG).show ();
                    return;
                }
                Patient doctor = response.body();
                Name.setText(doctor.getPatientname());
            }

            @Override
            public void onFailure(Call<Patient> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });

    }


    public void sendCall2(View view) {
        Intent intent = new Intent(this, PatientProfile.class);
        String EmailID = emailId.getText ().toString ();
        intent.putExtra ("homepagePatientEmailId", EmailID);
        startActivity(intent);
    }

    public void sendCall7(View view) {
        Intent intent = new Intent(this, ViewAppointmentPatient.class);
        String name= Name.getText ().toString ();
        intent.putExtra ("homepagePatientName", name);
        startActivity(intent);
    }

    public void sendCall4(View view) {
        Intent intent = new Intent(this, Department.class);
        startActivity(intent);
    }

}
