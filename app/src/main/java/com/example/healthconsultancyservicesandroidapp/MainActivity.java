package com.example.healthconsultancyservicesandroidapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    EditText _txtUser, _txtPass;
    Button _btn;
    CheckBox remember;
    Spinner _spinner;
    TextView _link,_link1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _txtUser = (EditText) findViewById(R.id.txtUser);
        _txtPass = (EditText) findViewById(R.id.txtPass);
        _btn = (Button) findViewById(R.id.btn);
        remember =(CheckBox) findViewById(R.id.rememberMe);
        _spinner = (Spinner) findViewById(R.id.spinner);
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


        ArrayAdapter <CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.usertype,R.layout.support_simple_spinner_dropdown_item);
        _spinner.setAdapter(adapter);

        _btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item = _spinner.getSelectedItem().toString();
                if (_txtUser.getText().toString().equals("DoctorDashboard")&& _txtPass.getText().toString().equals("DoctorDashboard")&& item.equals("DoctorDashboard")){
                    Intent intent =new Intent(MainActivity.this, DoctorDashboard.class);
                    startActivity(intent);
                }
                else if (_txtUser.getText().toString().equals("DoctorDashboard")&& _txtPass.getText().toString().equals("DoctorDashboard")&& item.equals("PatientDashboard")){
                    Intent intent =new Intent(MainActivity.this, PatientDashboard.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                }
            }
        });





    }
}
