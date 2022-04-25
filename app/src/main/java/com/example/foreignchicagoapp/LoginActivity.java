package com.example.foreignchicagoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String USER = "rafa";
    private static final String PASS = "itmd555";

    private int attempts = 3;

    Button buttonLogin;

    EditText editUsername;
    EditText editPassword;

    TextView textError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private String getUsername() {
        return editUsername.getText().toString();
    }

    private String getPassword() {
        return editPassword.getText().toString();
    }

    public void onClick(View view) {
        populateComponents();
        if (getUsername().equals(USER) && getPassword().equals(PASS)) {
            Toast.makeText(getApplicationContext(), "Valid Credentials\nRedirecting...", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        else {
            if (--attempts == 0)
                finish();
            textError.setText(String.format("Wrong Credentials.\nYou have %d attempts left.",attempts));
        }
    }

    public void populateComponents() {
        buttonLogin = findViewById(R.id.buttonLogin);
        textError = findViewById(R.id.textError);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
    }
}