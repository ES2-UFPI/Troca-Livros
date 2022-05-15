package com.rubick.tinli.Controllers;

import static java.time.temporal.ChronoUnit.DAYS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rubick.tinli.R;
import com.rubick.tinli.Services.Validators.TransferValidator;
import com.rubick.tinli.model.Tranfer;
import com.rubick.tinli.model.TransferStatus;

import java.time.LocalDate;

public class TransferView extends AppCompatActivity {

    private TextView leftBookName;
    private TextView rightBookName;
    private TextView localTextView;
    private TextView dateTextView;
    private Tranfer transfer;
    private TextView cancel;
    private Button finishBt;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_view);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setItems();
        GetTransferData();
    }

    private void setItems(){
        leftBookName = findViewById(R.id.left_book_name);
        rightBookName = findViewById(R.id.right_book_name);
        localTextView = findViewById(R.id.transfer_local);
        dateTextView = findViewById(R.id.transfer_date_textview);
        cancel = findViewById(R.id.cancelTransfer);
        finishBt = findViewById(R.id.finish_bt);

        cancel.setOnClickListener(click ->{
            if(transfer.getStatus() == TransferStatus.CANCELED) return;

            AlertDialog.Builder confirmCancel = new AlertDialog.Builder(TransferView.this);
            confirmCancel.setTitle("Cancelar transferência");
            confirmCancel.setMessage("Você realmente deseja cancelar a transferência?");
            confirmCancel.setCancelable(false);

            confirmCancel.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Aqui o enviariamos para o servidor um sinal dizendo que a transferência foi cancelada
                    finishBt.setEnabled(false);
                    transfer.setStatus(TransferStatus.CANCELED);
                    Toast.makeText(getApplicationContext(), "Transferência cancelada", Toast.LENGTH_SHORT).show();
                }
            });

            confirmCancel.setNegativeButton("Não", null);

            confirmCancel.create().show();
        });

        finishBt.setOnClickListener(click -> {
            //Aqui o enviariamos para o servidor um sinal dizendo que a transferência foi finalizada
            transfer.setStatus(TransferStatus.FINISHED);
            Toast.makeText(getApplicationContext(), "Transferência finalizada", Toast.LENGTH_SHORT).show();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void SetTransferData(String ownerBookName, String offeredBookName, String local, LocalDate date, TransferStatus status){
        transfer = new Tranfer(ownerBookName,offeredBookName,local,date,status);

        leftBookName.setText(transfer.getOwnerBookName());
        rightBookName.setText(transfer.getOfferedBookName());
        localTextView.setText(transfer.getLocal());
        dateTextView.setText(transfer.getDate().toString());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void GetTransferData(){
        //Nessa função receberíamos os dados do servidor

        String ownerBook = "O príncipe";
        String offeredBook = "Maus";
        String local = "Parque Pais e filhos";
        LocalDate date = LocalDate.now();
        TransferStatus status = TransferStatus.OPEN;
        boolean isValidTransfer = TransferValidator.ValidateTransferData(ownerBook, offeredBook, local, date, status);
        if(isValidTransfer){
            SetTransferData(ownerBook, offeredBook, local, date, status);
        }else{
            finishBt.setEnabled(false);
            transfer.setStatus(TransferStatus.CANCELED);
            Toast.makeText(getApplicationContext(), "Transferência inválida", Toast.LENGTH_SHORT).show();
        }
    }
}