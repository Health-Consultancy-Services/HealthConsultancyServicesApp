package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgetPassword extends AppCompatActivity {
    EditText _email,_password;
    Button _btn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        _email = (EditText) findViewById(R.id.email);
        _password = (EditText) findViewById(R.id.code);
        _btn = (Button) findViewById(R.id.btn);
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_email.getText().toString().isEmpty()) {
                    _email.setError("Email can't be empty");
                }else if (!_email.getText().toString().trim().matches(emailPattern)) {
                    _email.setError("Invalid email address");
                }else if (_password.getText().toString().isEmpty()) {
                    _password.setError("Password can't be empty");
                }
                else
                {
                    ForgetPassword();
                }}
        });
    }

    private void ForgetPassword(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.10:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        String email = _email.getText ().toString ();
        String password = _password.getText ().toString ();
        Call<User> call = healthConsultancyServicesApi.ForgotPassword(email,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body ();
                String email = _email.getText ().toString ();
                String password = _password.getText ().toString ();
                if (user.getEmail() == null){
                    Toast.makeText (getApplicationContext (), "Invalid Email_id :" +response.code (), Toast.LENGTH_LONG).show ();
                }
                else{
                    Intent intent =new Intent(ForgetPassword.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText (getApplicationContext (), t.getMessage (), Toast.LENGTH_LONG).show ();
            }
        });
    }
}
