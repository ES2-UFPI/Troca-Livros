package com.rubick.tinli.model;

import java.time.LocalDate;

public class Tranfer {
    private String ownerBookName;
    private String offeredBookName;
    private String local;
    private LocalDate date;
    private TransferStatus status;

    public Tranfer(String ownerBookName, String offeredBookName, String local, LocalDate date, TransferStatus status) {
        this.ownerBookName = ownerBookName;
        this.offeredBookName = offeredBookName;
        this.local = local;
        this.date = date;
        this.status = status;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public void setStatus(TransferStatus status) {
        this.status = status;
    }

    public String getOwnerBookName() {
        return ownerBookName;
    }

    public String getOfferedBookName() {
        return offeredBookName;
    }

    public String getLocal() {
        return local;
    }

    public LocalDate getDate() {
        return date;
    }

}
