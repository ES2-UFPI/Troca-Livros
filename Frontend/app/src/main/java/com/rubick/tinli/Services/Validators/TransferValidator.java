package com.rubick.tinli.Services.Validators;

import static java.time.temporal.ChronoUnit.DAYS;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.rubick.tinli.model.TransferStatus;

import java.time.LocalDate;

public class TransferValidator {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean ValidateTransferData(String ownerBookName, String offeredBookName, String local, LocalDate date, TransferStatus status){
        if(ownerBookName.isEmpty() || offeredBookName.isEmpty() || local.isEmpty()) return false;
        if(date.getYear() < 2020) return false;

        long daysBetween = DAYS.between(LocalDate.now(), date);
        if(daysBetween > 30) return false;

        return status == TransferStatus.OPEN;
    }
}
