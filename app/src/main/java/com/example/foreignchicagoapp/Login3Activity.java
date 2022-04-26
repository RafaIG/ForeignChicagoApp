package com.example.foreignchicagoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login3Activity extends AppCompatActivity {

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
        setContentView(R.layout.activity_login3);
        //mAuth = FirebaseAuth.getInstance();
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

    /*private FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }*/


    public void populateComponents() {
        buttonLogin = findViewById(R.id.buttonLogin);
        textError = findViewById(R.id.textError);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
    }
}