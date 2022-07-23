package com.example.foreignchicagoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CalculatorActivity extends AppCompatActivity {

    CardView cv_tmp;
    CardView cv_weight;
    CardView cv_length;
    CardView cv_speed;
    CardView cv_volume;
    CardView cv_time;
    CardView cv_area;
    CardView cv_fuel;
    CardView cv_pressure;
    CardView cv_energy;
    CardView cv_storage;
    CardView cv_current;
    CardView cv_force;
    CardView cv_freq;
    CardView cv_resistance;
    CardView cv_power;
    CardView cv_torque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        cv_tmp = findViewById(R.id.cv_tmp);
        cv_weight = findViewById(R.id.cv_weight);
        cv_length = findViewById(R.id.cv_length);
        cv_speed = findViewById(R.id.cv_speed);
        cv_volume = findViewById(R.id.cv_Volume);
        cv_time = findViewById(R.id.cv_time);
        cv_area = findViewById(R.id.cv_area);
        cv_fuel = findViewById(R.id.cv_fuel);
        cv_pressure = findViewById(R.id.cv_pressure);
        cv_energy = findViewById(R.id.cv_energy);
        cv_storage = findViewById(R.id.cv_storage);
        cv_current = findViewById(R.id.cv_current);
        cv_force = findViewById(R.id.cv_force);
        cv_freq = findViewById(R.id.cv_frequency);
        cv_resistance = findViewById(R.id.cv_resistence);
        cv_power = findViewById(R.id.cv_power);
        cv_torque = findViewById(R.id.cv_torque);

        cv_tmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, temp_cal.class));
            }
        });
        cv_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, weight_cal.class));
            }
        });
        cv_length.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, length_cal.class));
            }
        });
        cv_speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, speed_cal.class));
            }
        });
        cv_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, volume_cal.class));
            }
        });
        cv_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, time_cal.class));
            }
        });
        cv_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, area_cal.class));
            }
        });
        cv_fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, fuel_cal.class));
            }
        });
        cv_pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, pressure_cal.class));
            }
        });
        cv_energy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, energy_cal.class));
            }
        });
        cv_storage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, storage_cal.class));
            }
        });
        cv_current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, current_cal.class));
            }
        });
        cv_force.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, force_cal.class));
            }
        });
        cv_freq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, freq_cal.class));
            }
        });
        cv_resistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, resistance_cal.class));
            }
        });
        cv_power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, power_cal.class));
            }
        });
        cv_torque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CalculatorActivity.this, torque_cal.class));
            }
        });

    }
}