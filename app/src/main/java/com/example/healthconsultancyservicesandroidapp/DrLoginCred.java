package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrLoginCred extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText confirm;
    TextView role;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Button btn;

    private  HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_login_cred);

        btn = findViewById(R.id.btn);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.newpassword);
        confirm =findViewById(R.id.confirmpass);
        role = (TextView) findViewById(R.id.role);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.35.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user= new User(email.getText().toString(),password.getText().toString(),"doctor");
                if (email.getText().toString().isEmpty()){
                    email.setError("Email can't be empty");
                }
                else if (!email.getText().toString().trim().matches(emailPattern)){
                    email.setError("Invalid email address");
                }
                else  if (password.getText().toString().isEmpty() ){
                    password.setError("Password can't be empty");
                }
                else if (confirm.getText().toString().isEmpty()){
                    password.setError("Password can't be empty");
                }
                else if (!confirm.getText().toString().equals(password.getText().toString())){
                    confirm.setError("Password did't match empty");
                }
                else
                    saveUser(user);
            }
        });
    }

    private void saveUser(User user) {
        Call<User> call = healthConsultancyServicesApi.saveUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent intent = new Intent(DrLoginCred.this,MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });
    }
}
