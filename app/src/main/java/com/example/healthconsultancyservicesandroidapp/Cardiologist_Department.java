package com.example.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Cardiologist_Department extends AppCompatActivity {
    TextView department,departmentname;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardiologist__department);
        department =(TextView) findViewById(R.id.department6);
        departmentname =(TextView) findViewById(R.id.departmentname);
        getEodReport ();
    }
    private void getEodReport() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        final String depart = departmentname.getText().toString();
        Call<List<Doctor>> call = healthConsultancyServicesApi.findByDepartment(depart);
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                if (!response.isSuccessful ()) {
                    Toast.makeText (getApplicationContext (), response.code (), Toast.LENGTH_LONG).show ();
                    return;
                }
                List<Doctor> d = response.body();
                for(Doctor depart: d){
                    department.append(depart.getDoctor_name()+ "\n");
                }

            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });

    }
}