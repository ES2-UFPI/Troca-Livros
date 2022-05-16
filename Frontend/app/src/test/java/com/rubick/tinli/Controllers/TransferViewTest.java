package com.rubick.tinli.Controllers;

import static org.junit.Assert.assertEquals;

import static java.time.temporal.ChronoUnit.DAYS;

import com.rubick.tinli.Services.Validators.TransferValidator;
import com.rubick.tinli.model.TransferStatus;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class TransferViewTest {

    @Test
    public void CorrectTransfer() {
        String ownerBook = "O príncipe";
        String offeredBook = "Maus";
        String local = "Parque Pais e filhos";
        LocalDate date = LocalDate.now();
        TransferStatus status = TransferStatus.OPEN;
        boolean isValidTransfer = TransferValidator.ValidateTransferData(ownerBook, offeredBook, local, date, status);
        Assert.assertTrue(isValidTransfer);
    }

    @Test
    public void InvalidBookName() {
        String ownerBook = "";
        String offeredBook = "Maus";
        String local = "Parque Pais e filhos";
        LocalDate date = LocalDate.now();
        TransferStatus status = TransferStatus.OPEN;
        boolean isValidTransfer = TransferValidator.ValidateTransferData(ownerBook, offeredBook, local, date, status);
        Assert.assertFalse(isValidTransfer);
    }

    @Test
    public void InvalidLocal() {
        String ownerBook = "O príncipe";
        String offeredBook = "Maus";
        String local = "";
        LocalDate date = LocalDate.now();
        TransferStatus status = TransferStatus.OPEN;
        boolean isValidTransfer = TransferValidator.ValidateTransferData(ownerBook, offeredBook, local, date, status);
        Assert.assertFalse(isValidTransfer);
    }

    @Test
    public void DateBefore2020() {
        String ownerBook = "O príncipe";
        String offeredBook = "Maus";
        String local = "Parque Pais e filhos";
        LocalDate date = LocalDate.of(2019, Month.JANUARY, 8);
        TransferStatus status = TransferStatus.OPEN;
        boolean isValidTransfer = TransferValidator.ValidateTransferData(ownerBook, offeredBook, local, date, status);
        Assert.assertFalse(isValidTransfer);
    }

    @Test
    public void Date30DaysAfter() {
        String ownerBook = "O príncipe";
        String offeredBook = "Maus";
        String local = "Parque Pais e filhos";
        LocalDate date = LocalDate.of(2022, Month.AUGUST, 15);
        TransferStatus status = TransferStatus.OPEN;
        boolean isValidTransfer = TransferValidator.ValidateTransferData(ownerBook, offeredBook, local, date, status);
        Assert.assertFalse(isValidTransfer);
    }

    @Test
    public void TransferStatusNotOpen() {
        String ownerBook = "O príncipe";
        String offeredBook = "Maus";
        String local = "Parque Pais e filhos";
        LocalDate date = LocalDate.now();
        TransferStatus status = TransferStatus.CANCELED;
        boolean isValidTransfer = TransferValidator.ValidateTransferData(ownerBook, offeredBook, local, date, status);
        Assert.assertFalse(isValidTransfer);
    }
}