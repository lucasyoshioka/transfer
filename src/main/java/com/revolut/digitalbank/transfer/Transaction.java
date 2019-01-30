package com.revolut.digitalbank.transfer;

import java.time.LocalDateTime;

public class Transaction {

    private Owner from;

    private Owner to;

    private LocalDateTime dateTime;

    public Transaction(Owner from, Owner to, LocalDateTime dateTime) {
        this.from = from;
        this.to = to;
        this.dateTime = dateTime;
    }

    public Owner getFrom() {
        return from;
    }

    public Owner getTo() {
        return to;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

}
