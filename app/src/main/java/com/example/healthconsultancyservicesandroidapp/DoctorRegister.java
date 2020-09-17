package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DoctorRegister extends AppCompatActivity {
    Button btn;
    EditText doctor_name;
    EditText age;
    EditText phoneno;
    EditText email;
    EditText department;
    EditText experience;
    EditText address;
    EditText qualification;
    EditText gender;
    TextView status;


    private HealthConsultancyServicesApi healthConsultancyServicesApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);
        btn = findViewById(R.id.btn);
        doctor_name =(EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        phoneno = (EditText) findViewById(R.id.phoneno);
        email = (EditText) findViewById(R.id.email);
        department = findViewById(R.id.department);
        experience = findViewById(R.id.experience);
        address = findViewById(R.id.address);
        qualification = findViewById(R.id.qualification);
        gender = (EditText) findViewById(R.id.gender);
        status = (TextView) findViewById(R.id.status);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.35.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Doctor doctor = new Doctor(doctor_name.getText().toString(), age.getText().toString(), phoneno.getText().toString(), email.getText().toString(), department.getText().toString(), experience.getText().toString(), address.getText().toString(), qualification.getText().toString(), gender.getText().toString(), "inhold");
                if(doctor_name.getText().toString().isEmpty()){
                    doctor_name.setError("Name can't be empty");
                }

                else if (age.getText().toString().isEmpty()){

                    age.setError("age can't be empty");
                }
                else if (phoneno.getText().toString().isEmpty()|| phoneno.getText().toString().length() <10 ){
                    phoneno.setError("Enter a valid number");
                }
                else if (email.getText().toString().isEmpty()){
                    email.setError("Email can't be empty");

                }
                else if (department.getText().toString().isEmpty()){
                    department.setError("department can't be empty");
                }
                else if(experience.getText().toString().isEmpty()){
                    experience.setError("experience can't be empty");
                }
                else if(address.getText().toString().isEmpty()){
                    address.setError("address can't be empty");
                }
                else if(qualification.getText().toString().isEmpty()){
                    qualification.setError("qualification can't be empty");
                }
                else if(gender.getText().toString().isEmpty()){
                    gender.setError("gender can't be empty");
                }
                else
                    saveDoctor(doctor);


            }
            });

        }

    private void saveDoctor(Doctor doctor) {
        Call<Doctor> call =healthConsultancyServicesApi.saveDoctor(doctor);
        call.enqueue(new Callback<Doctor>() {
            @Override
            public void onResponse(Call<Doctor> call, Response<Doctor> response) {
                Intent intent = new Intent(DoctorRegister.this,DrLoginCred.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<Doctor> call, Throwable t) {

            }
        });
    }
}