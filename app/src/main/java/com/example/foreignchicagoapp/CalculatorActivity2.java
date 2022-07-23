package com.example.foreignchicagoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity2 extends AppCompatActivity {

    private EditText text;
    private Button buttonCalculate;
    RadioButton imperialButton, metricButton;

    TextView textOptions;
    boolean[] selectedLanguage;
    int option;
    String[] langArray = {"Temperature", "Distance", "Weight"};
    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        text = findViewById(R.id.editTextNumberDecimal);

        metricButton = findViewById(R.id.radioButton2);
        imperialButton = findViewById(R.id.radioButton);

        textOptions = findViewById(R.id.textOptions);
        selectedLanguage = new boolean[langArray.length];
        textOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int auxOption = option;
                option = 1;
                AlertDialog.Builder builder = new AlertDialog.Builder(CalculatorActivity2.this);
                builder.setTitle("Select Metric");
                builder.setCancelable(false);

                builder.setSingleChoiceItems(langArray, 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        option = i;
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textOptions.setText(langArray[option]);
                        switch (option) {
                            case 0:
                                metricButton.setText("°C -> °F");
                                imperialButton.setText("°F -> °C");
                                break;
                            case 1:
                                metricButton.setText("Metre -> Feet");
                                imperialButton.setText("Feet -> Metre");
                                break;
                            case 2:
                                metricButton.setText("Kilogram -> Pound");
                                imperialButton.setText("Pound -> Kilogram");
                                break;
                        }
                        if (first) {
                            findViewById(R.id.radioGroup).setVisibility(View.VISIBLE);
                            text.setVisibility(View.VISIBLE);
                            buttonCalculate.setVisibility(View.VISIBLE);
                        }
                        first = false;
                        text.setText("");
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // dismiss dialog
                        option = auxOption;
                        dialogInterface.dismiss();
                    }
                });
                // show dialog
                builder.show();
            }
        });

        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText().length() == 0) {
                    Toast.makeText(CalculatorActivity2.this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                float inputValue = Float.parseFloat(text.getText().toString());
                if (option > 0 && inputValue <= 0) {
                    Toast.makeText(CalculatorActivity2.this, "Please enter a positive number",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                switch (option) {
                    case 0:
                        if (metricButton.isChecked())
                            text.setText(String.format("%.2f", inputValue * (9 / 5.0) + 32));
                        else
                            text.setText(String.format("%.2f", (inputValue - 32) * 5.0 / 9.0));
                        break;
                    case 1:
                        if (metricButton.isChecked())
                            text.setText(String.format("%.2f", inputValue / 0.3048));
                        else
                            text.setText(String.format("%.2f", inputValue * 0.3048));
                        break;
                    case 2:
                        if (metricButton.isChecked())
                            text.setText(String.format("%.2f", inputValue * 22));
                        else
                            text.setText(String.format("%.2f", inputValue * 0.45359237));
                        break;

                }
            }
        });
    }

}
