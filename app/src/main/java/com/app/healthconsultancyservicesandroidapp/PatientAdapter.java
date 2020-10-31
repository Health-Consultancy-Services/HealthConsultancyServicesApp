package com.app.healthconsultancyservicesandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private ArrayList<Appointment> appointments=new ArrayList<>();
    private Context context;

    public PatientAdapter(Context context, ArrayList<Appointment> appointments){
        this.appointments=appointments;
        this.context=context;
    }

    @NonNull
    @Override
    public PatientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_list_item,viewGroup,false);
        return new PatientAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PatientAdapter.ViewHolder viewHolder, int i){
        viewHolder.patient_name.setText(appointments.get(i).getPatientname());
    }
    @Override
    public int getItemCount() {
        return appointments.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView patient_name;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            patient_name = (TextView) itemView.findViewById(R.id.PatientName);
        }
    }
}
