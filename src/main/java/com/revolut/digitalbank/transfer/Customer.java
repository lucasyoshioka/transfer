package com.revolut.digitalbank.transfer;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@EqualsAndHashCode(of = "id")
public class Customer {

    private Integer id;

    @Getter
    private String name;

    private Account account;

    private transient BigDecimal value;

    private transient Customer other;

    public Customer(Integer id) {
        this.id = id;
    }

    public Customer(Integer id, String name, Account account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public Customer transfer(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Customer to(Customer other) {
        this.other = other;
        return this;
    }

    public void execute() {
        if (this.value == null) {
            throw new IllegalArgumentException("Must specify a value to transfer.");
        }
        if (this.other == null) {
            throw new IllegalArgumentException("Must specify a destination customer.");
        }
        this.account.withdraw(this.value);
        other.deposit(this.value);
    }

    private void deposit(BigDecimal value) {
        this.account.deposit(value);
    }

    public BigDecimal getBalance() {
        return this.account.getBalance();
    }

}