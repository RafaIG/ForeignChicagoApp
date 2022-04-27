package com.example.foreignchicagoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private EditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        text = findViewById(R.id.editTextNumberDecimal);
    }

    /* this method is called when user clicks the button and is handled
    because we assigned the name to the "OnClick property" of the
    button */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                RadioButton celsiusButton =
                        findViewById(R.id.radioButton2);
                RadioButton fahrenheitButton =
                        findViewById(R.id.radioButton);
                if (text.getText().length() == 0) {
                    Toast.makeText(this, "Please enter a valid number",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                float inputValue =
                        Float.parseFloat(text.getText().toString());
                if (celsiusButton.isChecked()) {
                    text.setText(String.valueOf(inputValue * (9 / 5.0) + 32));
                    celsiusButton.setChecked(false);
                    fahrenheitButton.setChecked(true);
                }
                else {
                    text.setText(String.valueOf((inputValue- 32) * 5.0 / 9.0));
                    fahrenheitButton.setChecked(false);
                    celsiusButton.setChecked(true);
                }
                break;
        }
    }
}
