package com.app.healthconsultancyservicesandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentPatientAdapter extends RecyclerView.Adapter<AppointmentPatientAdapter.ViewHolder> {
    private ArrayList<Appointment> appointments=new ArrayList<>();
    private Context context;


    public AppointmentPatientAdapter(Context context, ArrayList<Appointment> appointments){
        this.appointments=appointments;
        this.context=context;
    }

    @NonNull
    @Override
    public AppointmentPatientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.appointment_list_patient_item,viewGroup,false);
        return new AppointmentPatientAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AppointmentPatientAdapter.ViewHolder viewHolder, int i){
        viewHolder.patient_name.setText("Dr. "+appointments.get(i).getDoctorname());
        viewHolder.status.setText("Status : "+appointments.get(i).getStatus());
    }
    @Override
    public int getItemCount() {
        return appointments.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView patient_name,status;
        private Button decline;
        private HealthConsultancyServicesApi healthConsultancyServicesApi;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            patient_name = (TextView) itemView.findViewById(R.id.PatientName);
            status = (TextView) itemView.findViewById(R.id.appointmentstatus);
            decline = (Button) itemView.findViewById(R.id.declineappointment);

            decline.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeclineAppointment();
                }
            });
        }

        private void DeclineAppointment() {
            healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create(HealthConsultancyServicesApi.class);
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
    }

}
