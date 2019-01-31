package com.revolut.digitalbank.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
public class Transaction {

    @Getter
    private Customer from;

    @Getter
    private Customer to;

    private LocalDateTime dateTime;

}
