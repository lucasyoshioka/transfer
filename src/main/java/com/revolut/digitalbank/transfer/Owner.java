package com.revolut.digitalbank.transfer;

import java.math.BigDecimal;
import java.util.Objects;

public class Owner {

    private Integer id;

    private String name;

    private Account account;

    private transient BigDecimal value;

    private transient Owner other;

    public Owner(Integer id) {
        this.id = id;
    }

    public Owner(Integer id, String name, Account account) {
        this.id = id;
        this.name = name;
        this.account = account;
    }

    public Owner transfer(BigDecimal value) {
        this.value = value;
        return this;
    }

    public Owner to(Owner other) {
        this.other = other;
        return this;
    }

    public void execute() {
        this.account.validate(this.value);
        this.account.subtract(this.value);
        other.deposit(this.value);
    }

    private void deposit(BigDecimal value) {
        this.account.deposit(value);
    }

    public BigDecimal getBalance() {
        return this.account.getBalance();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
