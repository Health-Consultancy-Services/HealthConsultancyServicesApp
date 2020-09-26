package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DoctorDashboard extends AppCompatActivity {

    ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        logout =findViewById(R.id.logout);

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
        public void sendCall1(View view) {
            Intent intent = new Intent(this, DoctorProfile.class);
            startActivity(intent);
        }
    }

