package com.rubick.tinli.App.Controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rubick.tinli.R;
import com.rubick.tinli.Services.Validator;
import com.santalu.maskara.widget.MaskEditText;

public class Register extends AppCompatActivity {

    private ImageView backBt;
    private Button CreateAccountBt;
    private EditText name;
    private EditText email;
    private MaskEditText cpf;
    private MaskEditText cellphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiter);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setItems();
    }

    private void setItems(){
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        cpf = findViewById(R.id.cpf);
        cellphone = findViewById(R.id.cellphoneInput);

        backBt = findViewById(R.id.back_bt);
        CreateAccountBt = findViewById(R.id.CreateAccountBt);

        backBt.setOnClickListener(v -> {
            this.finish();
        });

        CreateAccountBt.setOnClickListener(v ->{
            if(!isValidRegister()) return;
            //go to passwordregistration
            Toast.makeText(getApplicationContext(), "Dados de usuário registrados com sucesso!", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isValidRegister(){
        String nameSt = name.getText().toString().trim();
        String emailSt = email.getText().toString().trim();

        if(Validator.isEmpty(nameSt) || Validator.isEmpty(emailSt)){
            Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
            return false;
        }

        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if(!emailSt.matches(emailRegex)){
            Toast.makeText(getApplicationContext(), "Email inválido!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!Validator.isDone(cpf)){
            Toast.makeText(getApplicationContext(), "CPF inválido!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!Validator.isDone(cellphone)){
            Toast.makeText(getApplicationContext(), "Celular inválido", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}