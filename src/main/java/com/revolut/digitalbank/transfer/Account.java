package com.revolut.digitalbank.transfer;

import java.math.BigDecimal;
import java.util.Objects;

public class Account {

    private Integer id;

    private BigDecimal balance;

    public Account(Integer id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void validate(BigDecimal value) {
        if (isBalanceNotEnough(value)) {
            throw new IllegalStateException("Balance is not enough.");
        }
    }

    private boolean isBalanceNotEnough(BigDecimal value) {
        return this.balance.compareTo(value) < 0;
    }

    public void subtract(BigDecimal value) {
        this.balance = this.balance.subtract(value);
    }

    public void deposit(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
