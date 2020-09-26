package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PatientDashboard extends AppCompatActivity {
    ImageView logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        logout =findViewById(R.id.logout);
        Intent intent = getIntent ();
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
    public void sendCall2(View view) {
        Intent intent = new Intent(this, PatientProfile.class);
        startActivity(intent);
    }
}
