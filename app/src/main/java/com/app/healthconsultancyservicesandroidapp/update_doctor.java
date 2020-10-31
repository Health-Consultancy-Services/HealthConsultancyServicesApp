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

public class update_doctor extends AppCompatActivity {
    TextView name;
    TextView age;
    EditText phone;
    TextView email_id;
    TextView department;
    TextView experience;
    EditText address;
    TextView qualification;
    TextView emailid;
    TextView doctor_id;
    TextView gender;
    TextView status;
    Button home;
    Button submit;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_doctor);
        emailid = (TextView) findViewById (R.id.emailIdHide) ;
        email_id = (TextView) findViewById(R.id.email);
        doctor_id = (TextView) findViewById(R.id.doctor_id);
        name = (TextView) findViewById (R.id.name) ;
        age = (TextView) findViewById (R.id.age) ;
        phone = (EditText) findViewById (R.id.phoneno) ;
        department = (TextView) findViewById(R.id.department);
        experience = (TextView) findViewById(R.id.experience);
        address = (EditText) findViewById(R.id.address);
        qualification = (TextView) findViewById(R.id.qualification);
        gender = (TextView) findViewById (R.id.gender);
        status = (TextView) findViewById (R.id.status);
        home = (Button) findViewById(R.id.btnhome);
        submit = (Button) findViewById(R.id.btnsubmit);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailid.getText ().toString ();
                Intent intent =new Intent(update_doctor.this,DoctorDashboard.class);
                intent.putExtra("emailId", email);
                startActivity(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doctor doctor = new Doctor(doctor_id.getText().toString(), name.getText().toString(), age.getText().toString(), phone.getText().toString(), email_id.getText().toString(), department.getText().toString(), experience.getText().toString(), address.getText().toString(), qualification.getText().toString(), gender.getText().toString(),status.getText().toString());
                update(doctor);
            }
        });
        Intent intent = getIntent ();
        final String EmailID = intent.getStringExtra ("DoctorEmailId");
        emailid.setText (EmailID);
        findDoctorByEmail();
    }
    private void findDoctorByEmail() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
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
                doctor_id.setText(doctor.getDoctor_id());
                name.setText(doctor.getDoctorname());
                age.setText(doctor.getAge());
                phone.setText(doctor.getPhoneno());
                email_id.setText(doctor.getEmail());
                department.setText(doctor.getDepartment());
                experience.setText(doctor.getExperience());
                address.setText(doctor.getAddress());
                qualification.setText(doctor.getQualification());
                gender.setText(doctor.getGender());
                status.setText(doctor.getStatus());
            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });

    }
    private void update(Doctor doctor) {
        Call<Doctor> call =healthConsultancyServicesApi.update(doctor);
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                final String email = emailid.getText ().toString ();
                Intent intent =new Intent(update_doctor.this,DoctorDashboard.class);
                intent.putExtra("emailId", email);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });
    }
}