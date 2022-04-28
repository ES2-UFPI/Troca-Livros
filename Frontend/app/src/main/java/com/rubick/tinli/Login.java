package com.rubick.tinli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private ImageView loginBt;
    private ImageView backBt;
    private TextView createAccount;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setItems();
    }

    private void setItems() {
        loginBt = findViewById(R.id.login_bt);
        backBt = findViewById(R.id.back_bt);
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);
        createAccount = findViewById(R.id.createNewAccount);

        createAccount.setOnClickListener(v -> {
            Intent register = new Intent(getApplicationContext(), Regiter.class);
            startActivity(register);
        });

        loginBt.setOnClickListener(v ->{
            if(email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
            }
        });

        backBt.setOnClickListener(v -> {
            this.finish();
        });
    }
}