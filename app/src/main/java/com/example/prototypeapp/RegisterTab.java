package com.example.prototypeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterTab extends AppCompatActivity {
    EditText username,name,emailId,phoneno,password,conpass;
    CheckBox male,female,transgender;
    Button btnregister;
    TextView txtloginbutton;
    private  int MAX_LENGTH=10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_tab);

        username=(EditText) findViewById(R.id.et_username);
        name=(EditText) findViewById(R.id.et_name);
        emailId=(EditText) findViewById(R.id.et_emailid);
        phoneno=(EditText) findViewById(R.id.et_phoneno);
        password=(EditText)findViewById(R.id.et_password);
        conpass=(EditText) findViewById(R.id.et_comfirmpassword);

        male=(CheckBox) findViewById(R.id.chk_isMale);
        female=(CheckBox) findViewById(R.id.chk_isFemale);
        transgender=(CheckBox) findViewById(R.id.chk_isTransgender);

        btnregister=(Button) findViewById(R.id.btn_register);
        txtloginbutton=(TextView) findViewById(R.id.txt_loginlink);

        male.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    female.setChecked(false);
                    transgender.setChecked(false);
                }
            }
        });

        female.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    male.setChecked(false);
                    transgender.setChecked(false);
                }
            }
        });

        transgender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    male.setChecked(false);
                    female.setChecked(false);
                }
            }
        });

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String usernametxt = s.toString().trim();
                if(usernametxt.isEmpty()||usernametxt.length()<8)
                {
                    username.setError("UserName must be Greater than 8 characters");
                }else {
                    username.setError(null);//clear the error
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nametxt=s.toString().trim();
                if(nametxt.isEmpty()){
                    name.setError("Name Cannot be Empty");
                }else {
                    name.setError(null);//clear the error
                }
            }
        });
        emailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                String email = editable.toString().trim();
                if(!isValidEmail(email)){
                    emailId.setError("Invalid Email Format");
                }else {
                    emailId.setError(null);//clear the error
                }
            }
        });

        phoneno.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > MAX_LENGTH) {
                    phoneno.removeTextChangedListener(this);
                    String trimmedText = editable.toString().substring(0, MAX_LENGTH);
                    phoneno.setText(trimmedText);
                    phoneno.setSelection(MAX_LENGTH); // Move cursor to the end
                    phoneno.addTextChangedListener(this);
                }else if (editable.length() < MAX_LENGTH) {
                    // Show an error message for less than 10 digits
                    phoneno.setError("Enter only 10 digits Phone Number");
                } else {
                    // Clear any previous error message when valid
                    phoneno.setError(null);
                }
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String passwordTxt=s.toString().trim();
                if(passwordTxt.isEmpty() || passwordTxt.length()<8){
                    password.setError("It Must be > 8 Characters");
                }
                else{
                    password.setError(null);//Clear the error
                }
            }
        });

        conpass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String conpasstxt = s.toString().trim();
                String passwordTxt = password.getText().toString().trim(); // Get the password text
                if (!conpasstxt.equals(passwordTxt)) {
                    conpass.setError("Password Does Not Match");
                }
                else{
                    conpass.setError(null);//clear the errors
                }
            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {

             String usernametxt = username.getText().toString();
             String nameTxt = name.getText().toString();
             String emailidTxt = emailId.getText().toString();
             String phoneTxt = phoneno.getText().toString();
             String passwordTxt = password.getText().toString();
             String confirmpasstxt=conpass.getText().toString();
            @Override
            public void onClick(View v) {
                if(!usernametxt.isEmpty()||nameTxt.isEmpty()||emailidTxt.isEmpty()||phoneTxt.isEmpty()||passwordTxt.isEmpty()||confirmpasstxt.isEmpty()){
                    Toast.makeText(RegisterTab.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                }
                else if(!(male.isChecked() || female.isChecked() || transgender.isChecked()))
                {
                    Toast.makeText(RegisterTab.this, "Please Select the Gender", Toast.LENGTH_SHORT).show();
                } else{
                    startActivity(new Intent(RegisterTab.this,MainActivity.class));
                }
            }
        });

        txtloginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterTab.this,LoginTab.class));
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return (Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}