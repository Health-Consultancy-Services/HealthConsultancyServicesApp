package com.app.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    Button btn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";
    String alphabetPattern=  "[a-zA-Z ]+";
private  HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        patient_name=(EditText) findViewById(R.id.name);
        age=(EditText) findViewById(R.id.age);
        phoneno=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btn = findViewById(R.id.btn);

        healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create (HealthConsultancyServicesApi.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedRadioButton  = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                Patient patient = new Patient(patient_name.getText().toString(), age.getText().toString(), phoneno.getText().toString(), email.getText().toString(), selectedRadioButton.getText().toString());
                if (patient_name.getText().toString().isEmpty()) {
                    patient_name.setError("Name can't be empty");
                }else if (!patient_name.getText().toString().trim().matches(alphabetPattern)){
                    patient_name.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }else if (age.getText().toString().isEmpty()) {
                    age.setError("age can't be empty");
                } else if (phoneno.getText().toString().isEmpty()) {
                    phoneno.setError("Enter a valid number");
                }else if (!phoneno.getText().toString().trim().matches(MobilePattern)){
                    phoneno.setError("Invalid Phone Number");
                } else if (email.getText().toString().isEmpty()) {
                    email.setError("Email can't be empty");
                }else if (!email.getText().toString().trim().matches(emailPattern)){
                    email.setError("Invalid email address");
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
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });

        }
    }


