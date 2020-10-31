package com.app.healthconsultancyservicesandroidapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.ViewHolder> {
    private ArrayList<Appointment> appointments=new ArrayList<>();
    private Context context;


    public AppointmentAdapter(Context context, ArrayList<Appointment> appointments){
        this.appointments=appointments;
        this.context=context;
    }

    @NonNull
    @Override
    public AppointmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appointment_list_item,viewGroup,false);
        return new AppointmentAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.ViewHolder viewHolder, int i){
        viewHolder.patient_name.setText(appointments.get(i).getPatientname());
    }
    @Override
    public int getItemCount() {
        return appointments.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView patient_name;
        private Button accept,decline;
        private HealthConsultancyServicesApi healthConsultancyServicesApi;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            patient_name = (TextView) itemView.findViewById(R.id.PatientName);
            accept = (Button) itemView.findViewById(R.id.acceptappointment);
            decline = (Button) itemView.findViewById(R.id.declineappointment);

            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AcceptAppointment();
                }
            });
            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeclineAppointment();
                }
            });
        }

        private void DeclineAppointment() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.20.10.3:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
            final String PatientName = patient_name.getText().toString();
            Call<Appointment> call = healthConsultancyServicesApi.DeclineAppointment(PatientName);
            call.enqueue(new Callback<Appointment>() {
                @Override
                public void onResponse(Call<Appointment> call, Response<Appointment> response) {
                }

                @Override
                public void onFailure(Call<Appointment> call, Throwable t) {

                }
            });
        }

        private void AcceptAppointment() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://172.20.10.3:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
            final String PatientName = patient_name.getText().toString();
            Call<Appointment> call = healthConsultancyServicesApi.AcceptAppointment(PatientName);
            call.enqueue(new Callback<Appointment>() {
                @Override
                public void onResponse(Call<Appointment> call, Response<Appointment> response) {

                }

                @Override
                public void onFailure(Call<Appointment> call, Throwable t) {

                }
            });
        }
    }

}
