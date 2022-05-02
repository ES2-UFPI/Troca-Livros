package com.rubick.tinli.App.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import com.rubick.tinli.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button startSeason = findViewById(R.id.start_season);
        Button registerBt = findViewById(R.id.CreateAccountBt);

        startSeason.setOnClickListener(v ->{
            Intent login = new Intent(getApplicationContext(), Login.class);
            startActivity(login);
        });

        registerBt.setOnClickListener(v ->{
            Intent register = new Intent(getApplicationContext(), Register.class);
            startActivity(register);
        });
    }
}