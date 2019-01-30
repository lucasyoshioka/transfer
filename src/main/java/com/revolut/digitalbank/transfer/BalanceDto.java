package com.revolut.digitalbank.transfer;

import java.math.BigDecimal;

public class BalanceDto {

    private BigDecimal balance;

    public BalanceDto(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
