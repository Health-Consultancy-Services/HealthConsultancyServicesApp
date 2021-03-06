package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorProfile extends AppCompatActivity {
    TextView name;
    TextView age;
    TextView phone;
    TextView email_id;
    TextView department;
    TextView experience;
    TextView address;
    TextView qualification;
    TextView emailid;
    TextView gender;
    Button back;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        emailid = (TextView) findViewById (R.id.emailIdHide) ;
        email_id = (TextView) findViewById(R.id.email);
        name = (TextView) findViewById (R.id.name) ;
        age = (TextView) findViewById (R.id.age) ;
        phone = (TextView) findViewById (R.id.phoneno) ;
        department = (TextView) findViewById(R.id.department);
        experience = (TextView) findViewById(R.id.experience);
        address = (TextView) findViewById(R.id.address);
        qualification = (TextView) findViewById(R.id.qualification);
        gender = (TextView) findViewById (R.id.gender) ;
        back = (Button) findViewById(R.id.btnback);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailid.getText ().toString ();
                Intent intent =new Intent(DoctorProfile.this,DoctorDashboard.class);
                intent.putExtra("emailId", email);
                startActivity(intent);
            }
        });

        Intent intent = getIntent ();
        final String EmailID = intent.getStringExtra ("homepageDoctorEmailId");
        emailid.setText (EmailID);
        findDoctorByEmail();
    }
    private void findDoctorByEmail() {
        healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create(HealthConsultancyServicesApi.class);
        Intent intent = getIntent ();
        final String email = emailid.getText().toString();
        Call<Doctor> call = healthConsultancyServicesApi.findDoctorByEmail(email);
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                if (!response.isSuccessful ()){
                    Toast.makeText (getApplicationContext (), response.code (), Toast.LENGTH_LONG).show ();
                    return;
                }
                Doctor doctor = response.body();
                name.setText(doctor.getDoctorname());
                age.setText(doctor.getAge());
                phone.setText(doctor.getPhoneno());
                email_id.setText(doctor.getEmail());
                department.setText(doctor.getDepartment());
                experience.setText(doctor.getExperience());
                address.setText(doctor.getAddress());
                qualification.setText(doctor.getQualification());
                gender.setText(doctor.getGender());
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });

    }
    public void Doctoredit(View view) {
        Intent intent = new Intent(this, update_doctor.class);
        String EmailID = emailid.getText ().toString ();
        intent.putExtra ("DoctorEmailId", EmailID);
        startActivity(intent);
    }
}