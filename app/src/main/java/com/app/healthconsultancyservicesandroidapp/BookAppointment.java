package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {

    EditText doctor_name, patient_name, time_slot, date;
    TextView status;
    DatePickerDialog datePickerDialog;
    Button button;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        doctor_name = findViewById(R.id.dname);
        patient_name = findViewById(R.id.pname);
        date= findViewById(R.id.date);
        time_slot = findViewById(R.id.time);
        status = findViewById(R.id.status);
        button = findViewById(R.id.btn);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appointment appointment = new Appointment(doctor_name.getText().toString(), patient_name.getText().toString(), date.getText().toString(), time_slot.getText().toString(), "inhold");
                saveAppointment(appointment);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar =Calendar.getInstance();
                final int year=calendar.get(Calendar.YEAR);
                final int month=calendar.get(Calendar.MONTH);
                final int day=calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog=new DatePickerDialog(BookAppointment.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(year+"-"+(month+1)+"-"+day);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
  }

        private void saveAppointment (Appointment appointment){
            Call<Appointment> call = healthConsultancyServicesApi.saveAppointment(appointment);
            call.enqueue(new Callback<Appointment>() {
                @Override
                public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                    Intent intent = new Intent(BookAppointment.this, Department.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Appointment> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
