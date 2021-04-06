package com.geekbrains.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Objects;

public class ThemeSettingsActivity extends AppCompatActivity {

    SwitchCompat switchCompat;
    ImageView imageViewLight;
    ImageView imageViewNight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_theme_settings);
        initViews();
        chooseTheme();
    }

    private void initViews(){
        switchCompat = findViewById(R.id.themeSwitch);
        imageViewLight = findViewById(R.id.lightBackground);
        imageViewNight = findViewById(R.id.imageViewNight);
    }

    private void chooseTheme() {
        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                imageViewLight.setVisibility(View.VISIBLE);
                imageViewNight.setVisibility(View.INVISIBLE);
            }
            else {
                imageViewNight.setVisibility(View.VISIBLE);
                imageViewLight.setVisibility(View.INVISIBLE);
            }
            Intent themeIntent = new Intent(ThemeSettingsActivity.this, MainActivity.class);
            themeIntent.putExtra("switch", isChecked);
        });
    }

    
}