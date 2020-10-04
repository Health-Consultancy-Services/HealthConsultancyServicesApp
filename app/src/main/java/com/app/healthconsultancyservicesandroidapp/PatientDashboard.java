package com.app.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PatientDashboard extends AppCompatActivity {
    ImageView logout;
    TextView emailId;
    String Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);
        logout =findViewById(R.id.logout);
        emailId = (TextView) findViewById (R.id.emailIdHide) ;
        Intent intent = getIntent ();
        Email =  intent.getStringExtra ("emailId");
        emailId.setText (Email);
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
        String EmailID = emailId.getText ().toString ();
        intent.putExtra ("homepagePatientEmailId", EmailID);
        startActivity(intent);
    }

    public void sendCall4(View view) {
        Intent intent = new Intent(this, Department.class);
        startActivity(intent);
    }

}
