package com.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import static com.geekbrains.calculator.CalculatorInfo.lastOperation;
import static com.geekbrains.calculator.CalculatorInfo.operand;

public class MainActivity extends AppCompatActivity {

    static EditText numberField;
    static TextView resultField;
    ImageView imageViewLight;
    ImageView imageViewNight;
    TextView operationField;
    ImageButton backspace;
    MaterialButton clear;
    ImageButton settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.vertical_layout);
        } else {
            setContentView(R.layout.landscape_layout);
        }
        initViews();
    }

    public void initViews() {
        numberField = findViewById(R.id.numberField);
        resultField = findViewById(R.id.resultField);
        operationField = findViewById(R.id.operationField);
        clear = findViewById(R.id.clear);
        backspace = findViewById(R.id.backspace);
        settingsBtn = findViewById(R.id.settingsBtn);
        imageViewLight = findViewById(R.id.lightBackground);
        imageViewNight = findViewById(R.id.nightBackground);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("LastOperation", lastOperation);
        if (operand != null){
            outState.putDouble("operand", operand);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("LastOperation");
        operand = savedInstanceState.getDouble("operand");
        resultField.setText(String.valueOf(operand));
        operationField.setText(lastOperation);
    }

    public void onNumberClick(View view) {
        MaterialButton button = (MaterialButton) view;
        numberField.append(button.getText());

        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }

    public void onOperationClick(View view) {
        ImageButton imageButton = (ImageButton)view;
        String operation = imageButton.getContentDescription().toString();
        String number = numberField.getText().toString();

        if(number.length()>0){
            try{
                CalculatorInfo.operation(Double.valueOf(number), operation);
            }catch (NumberFormatException ex){
                numberField.setText("");
            }
        }
        lastOperation = operation;
        operationField.setText(lastOperation);
    }

    public void clear(View view) {
        clear.setOnClickListener(v -> {
            numberField.setText("");
            operationField.setText("");
            resultField.setText("");
        });
    }

    public void backspace(View view) {
        backspace.setOnClickListener(v -> {
            numberField.setText("");
            operationField.setText("");
        });
    }

    public void onSettingsClick(View view) {
        settingsBtn.setOnClickListener(v -> {
            Intent runSettings = new Intent(MainActivity.this, ThemeSettingsActivity.class);
            startActivity(runSettings);
        });
    }

    public void setTheme(){
        boolean theme = getIntent().getExtras().getBoolean("switch");
        if (theme){
            imageViewLight.setVisibility(View.VISIBLE);
            imageViewNight.setVisibility(View.INVISIBLE);
        }
        else {
            imageViewNight.setVisibility(View.VISIBLE);
            imageViewLight.setVisibility(View.INVISIBLE);
        }
    }
}