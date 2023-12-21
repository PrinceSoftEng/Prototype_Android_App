package com.example.prototypeapp;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginTab extends AppCompatActivity {

    EditText etEmailid,etpassword;
    Button btnlogin;
    TextView txtregisterlink,txtforgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_tab);

        etEmailid=(EditText) findViewById(R.id.emailid);
        etpassword=(EditText) findViewById(R.id.etpassword);
        btnlogin=(Button) findViewById(R.id.btnlogin);
        txtregisterlink=(TextView) findViewById(R.id.registerlink);
        txtforgetpassword=(TextView) findViewById(R.id.forgotpasswordlink);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


        txtforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginTab.this,ForgetPasswordTab.class));
            }
        });

        txtregisterlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginTab.this,RegisterTab.class));
            }
        });
    }
    private void loginUser() {
        String emailId = etEmailid.getText().toString().trim();
        String passwordTxt = etpassword.getText().toString().trim();
        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        String checkemailId = sharedPreferences.getString("", "Sandeep@gmail.com");
        String checkpassword = sharedPreferences.getString("", "Sandeep@123");
        if (emailId.isEmpty() && passwordTxt.isEmpty()){
            Toast.makeText(this, "EmailId and Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
        else if (emailId.equals(checkemailId) && passwordTxt.equals(checkpassword)) {
            Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginTab.this,MainActivity.class));
        } else if (emailId.isEmpty()) {
            Toast.makeText(this, "EmailId Cannot Be Empty", Toast.LENGTH_SHORT).show();
        } else if (!emailId.equals(checkemailId)) {
            Toast.makeText(this, "Incorrect EmailId", Toast.LENGTH_SHORT).show();
        } else if (passwordTxt.isEmpty()) {
            Toast.makeText(this, "Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
        } else if (!passwordTxt.equals(checkpassword)) {
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
        } else {
            // Login failed
            Toast.makeText(this, "Login failed. Invalid credentials.", Toast.LENGTH_SHORT).show();
        }
    }
}