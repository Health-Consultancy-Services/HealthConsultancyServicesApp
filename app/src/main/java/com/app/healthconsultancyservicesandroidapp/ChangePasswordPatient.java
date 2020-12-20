package com.app.healthconsultancyservicesandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPatient extends AppCompatActivity {
    EditText _password,_oldpassword, _confirmpass;
    TextView _email,patientname;
    Button _btn;
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_patient);
        patientname = (TextView) findViewById(R.id.patientname);
        _email = (TextView) findViewById(R.id.email);
        _oldpassword = (EditText) findViewById(R.id.oldpassword);
        _password = (EditText) findViewById(R.id.newpassword);
        _confirmpass = (EditText) findViewById(R.id.confirmpass);
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("homepagePatientName");
        String EmailID = bundle.getString("homepagePatientEmailId");
        patientname.setText (name);
        _email.setText(EmailID);
        _btn = (Button) findViewById(R.id.btn);
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_oldpassword.getText().toString().isEmpty()) {
                    _oldpassword.setError("Old Password can't be empty");
                }else if (_password.getText().toString().isEmpty()) {
                    _password.setError("Password can't be empty");
                }else if (_confirmpass.getText().toString().isEmpty()) {
                    _confirmpass.setError("Confirm Password can't be empty");
                }else if (!_confirmpass.getText().toString().equals(_password.getText().toString())) {
                    _confirmpass.setError("Password Dosen't match ");
                }
                else
                {
                    ChangePassword();
                }}
        });
    }

    private void ChangePassword() {
        healthConsultancyServicesApi = healthConsultancyServicesApi.retrofit.create(HealthConsultancyServicesApi.class);
        String email = _email.getText ().toString ();
        String password = _oldpassword.getText ().toString ();
        String newpassword = _password.getText ().toString ();
        Call<Integer> call = healthConsultancyServicesApi.ChangePassword(email,password,newpassword);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                Integer message = response.body ();
                String email = _email.getText ().toString ();
                String password = _oldpassword.getText ().toString ();
                String newpassword = _password.getText ().toString ();
                if (message == 1){
                    Intent intent =new Intent(ChangePasswordPatient.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText (getApplicationContext (), "Invalid User Details :" +response.code (), Toast.LENGTH_LONG).show ();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });
    }
}