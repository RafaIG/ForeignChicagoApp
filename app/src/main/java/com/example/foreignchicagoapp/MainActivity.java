package com.example.foreignchicagoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonQuiz, buttonWeather, buttonPhotos, buttonCalculatorMetrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonQuiz = (Button) findViewById(R.id.buttonQuiz);
        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), QuizActivity.class));
            }
        });

        buttonWeather = (Button) findViewById(R.id.buttonWeather);
        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), WeatherActivity.class));
            }
        });

        buttonPhotos = (Button) findViewById(R.id.buttonPhoto);
        buttonPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PhotoActivity.class));
            }
        });


        buttonCalculatorMetrics = (Button) findViewById(R.id.buttonCalculatorMetrics);
        buttonCalculatorMetrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
            }
        });
    }

    //startActivity(new Intent(getApplicationContext(), MainActivity.class));
}