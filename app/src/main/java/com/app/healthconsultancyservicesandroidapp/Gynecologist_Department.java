package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Gynecologist_Department extends AppCompatActivity {
    TextView departmentname;
    ArrayList<Doctor> doctors=new ArrayList<>();
    private DepartmentAdapter departmentAdapter;
    private RecyclerView department_recyclerview;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gynecologist__department);
        department_recyclerview=(RecyclerView)findViewById(R.id.depart_recyclerview);
        department_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        departmentname =(TextView) findViewById(R.id.departmentname);
        getEodReport ();
    }
    private void getEodReport() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.100:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        final String depart = departmentname.getText().toString();
        Call<List<Doctor>> call = healthConsultancyServicesApi.findByDepartment(depart);
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                doctors=new ArrayList<>(response.body());
                departmentAdapter=new DepartmentAdapter(Gynecologist_Department.this,doctors);
                department_recyclerview.setAdapter(departmentAdapter);
                Toast.makeText(Gynecologist_Department.this,"Success",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });

    }
    public void appointment(View view){
        Intent intent = new Intent(this,BookAppointment.class);
        startActivity(intent);
    }
}