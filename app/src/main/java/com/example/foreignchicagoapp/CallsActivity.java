package com.example.foreignchicagoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CallsActivity extends AppCompatActivity {

    private Button buttonEmergency, buttonNoEmergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);


        buttonEmergency = (Button) findViewById(R.id.btnEmergency);
        buttonEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:112";
                i.setData(Uri.parse(p));
                startActivity(i);
            }
        });

        buttonNoEmergency = (Button) findViewById(R.id.btnNoEmergency);
        buttonNoEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL);
                String p = "tel:311";
                i.setData(Uri.parse(p));
                startActivity(i);
            }
        });
    }
}

