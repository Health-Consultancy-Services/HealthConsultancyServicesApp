package com.example.healthconsultancyservicesandroidapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {


    EditText _txtUser, _txtPass, _txtRole;
    Button _btn;
    RadioGroup radioGroup;
    RadioButton selectedRadioButton;
    TextView _link,_link1;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private HealthConsultancyServicesApi healthConsultancyServicesApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _txtUser = (EditText) findViewById(R.id.txtUser);
        _txtPass = (EditText) findViewById(R.id.txtPass);
        _btn = (Button) findViewById(R.id.btn);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        _link =(TextView) findViewById(R.id.link);
        _link1 = findViewById(R.id.link1);

        _link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,Registration.class);
                startActivity(intent);
            }
        });

        _link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,ForgetPassword.class);
                startActivity(intent);
            }
        });
        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_txtUser.getText().toString().isEmpty()) {
                    _txtUser.setError("Email can't be empty");
                }else if (!_txtUser.getText().toString().trim().matches(emailPattern)) {
                    _txtUser.setError("Invalid email address");
                }else if (_txtPass.getText().toString().isEmpty()) {
                    _txtPass.setError("Password can't be empty");
                }
                else
                { selectedRadioButton  = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
                findByEmailAndPasswordAndRole();
            }}
        });

    }

    private void findByEmailAndPasswordAndRole() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        healthConsultancyServicesApi = retrofit.create(HealthConsultancyServicesApi.class);
        String email = _txtUser.getText ().toString ();
        String password = _txtPass.getText ().toString ();

        String role = selectedRadioButton.getText().toString();

        Call<User> call = healthConsultancyServicesApi.findByEmailAndPasswordAndRole(email,password,role);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body ();
                String email = _txtUser.getText ().toString ();
                String password = _txtPass.getText ().toString ();
                String role = selectedRadioButton.getText().toString();
                if (user == null){
                    Toast.makeText (getApplicationContext (), "Please Enter Valid Credentials :" +response.code (), Toast.LENGTH_LONG).show ();
                }
                else if(selectedRadioButton.getText().toString().equals("patient")){
                    Intent intent =new Intent(MainActivity.this, PatientDashboard.class);
                    startActivity(intent);
                }
                else if(selectedRadioButton.getText().toString().equals("doctor")) {
                    Intent intent = new Intent(MainActivity.this, DoctorDashboard.class);
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
