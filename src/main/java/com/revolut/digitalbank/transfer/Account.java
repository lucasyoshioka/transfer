package com.revolut.digitalbank.transfer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Account {

    private Integer id;

    @Getter
    private BigDecimal balance;

    public void deposit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }
    
    public void withdraw(BigDecimal value) {
        if (isBalanceNotEnough(value)) {
            throw new IllegalStateException("Balance is not enough.");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Value to transfer must be bigger than zero.");
        }
        this.balance = this.balance.subtract(value);
    }

    private boolean isBalanceNotEnough(BigDecimal value) {
        return this.balance.compareTo(value) < 0;
    }

}
