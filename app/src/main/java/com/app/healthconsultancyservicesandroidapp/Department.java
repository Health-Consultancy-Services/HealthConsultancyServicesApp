package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Department extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
    }
    public void department1(View view) {
        Intent intent = new Intent(this, Gastrology_Department.class);
        startActivity(intent);
    }

    public void department2(View view) {
        Intent intent = new Intent(this, Eyespecialist_Department.class);
        startActivity(intent);
    }
    public void department3(View view) {
        Intent intent = new Intent(this, ENTspecialist_Department.class);
        startActivity(intent);
    }
    public void department4(View view) {
        Intent intent = new Intent(this, Gynecologist_Department.class);
        startActivity(intent);
    }
    public void department5(View view) {
        Intent intent = new Intent(this, Dentist_Department.class);
        startActivity(intent);
    }
    public void department6(View view) {
        Intent intent = new Intent(this, Cardiologist_Department.class);
        startActivity(intent);
    }
}