package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class Registration extends AppCompatActivity {

    ImageView Doctor;
    ImageView Paitent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Doctor = findViewById(R.id.doc);
        Paitent= findViewById(R.id.pg);
        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Registration.this,DoctorRegister.class);
                startActivity(intent);
            }
        });

        Paitent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this,PatientRegister.class);
                startActivity(intent);
            }
        });


    }
}
