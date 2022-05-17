package com.rubick.tinli.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rubick.tinli.R;
import com.rubick.tinli.Services.Validator;

public class LoginDataView extends AppCompatActivity {

    private ImageView backBt;
    private ImageView createBt;
    private EditText usernameInput;
    private EditText passwordInput;
    private EditText confirmPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_data_view);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setItems();
    }

    private void setItems(){
        usernameInput = findViewById(R.id.input_name);
        passwordInput = findViewById(R.id.input_password);
        confirmPasswordInput = findViewById(R.id.input_confirm_password);

        backBt = findViewById(R.id.login_data_back_bt);
        createBt = findViewById(R.id.login_data_create_login_bt);

        backBt.setOnClickListener(v -> {
            this.finish();
        });

        createBt.setOnClickListener(v -> {
            if(!isValidRegister()) return;
            Toast.makeText(getApplicationContext(), "Usuário registrado com sucesso", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isValidRegister(){
        String name = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();
        String passwordRegex = "\\S*(\\S*([a-zA-Z]\\S*[0-9])|([0-9]\\S*[a-zA-Z]))\\S*";

        if(Validator.isEmpty(name) || Validator.isEmpty(password) || Validator.isEmpty(confirmPassword)){
            Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Validator.hasMinimumLength(name) || !Validator.hasMinimumLength(password)){
            Toast.makeText(getApplicationContext(), "Os campos devem ter mais de 6 caracteres!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Validator.PasswordsMatch(password, confirmPassword)){
            Toast.makeText(getApplicationContext(), "As senhas não são iguais!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!password.matches(passwordRegex)){
            Toast.makeText(getApplicationContext(), "A senha deve ter letras e números, também não pode ter espaço", Toast.LENGTH_SHORT).show();
            return false;
        }

        //TODO: Conferir se já existe um usuário com o mesmo username
        return true;
    }
}