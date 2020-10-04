package com.app.healthconsultancyservicesandroidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class  DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder> {
    private ArrayList<Doctor> doctors=new ArrayList<>();
    private Context context;

    public DepartmentAdapter(Context context, ArrayList<Doctor> doctors){
        this.doctors=doctors;
        this.context=context;
    }

    @NonNull
    @Override
    public DepartmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.department_list_item,viewGroup,false);
        return new DepartmentAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DepartmentAdapter.ViewHolder viewHolder, int i){
        viewHolder.doctor_name.setText(doctors.get(i).getDoctor_name());
        viewHolder.department.setText(doctors.get(i).getDepartment());
    }
    @Override
    public int getItemCount() {
        return doctors.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView doctor_name,department;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            doctor_name = (TextView) itemView.findViewById(R.id.departmentDoctorName);
            department =   (TextView) itemView.findViewById(R.id.departmentName);
        }
    }
}
