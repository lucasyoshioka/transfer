package com.revolut.digitalbank.transfer;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void mustDepositValue() {
        Account account = new Account(1, new BigDecimal("1500.00"));
        account.deposit(new BigDecimal("2000.00"));
        assertEquals(new BigDecimal("3500.00"), account.getBalance());
    }

    @Test(expected = IllegalStateException.class)
    public void mustValidateIfBalanceIsEnoughToTransfer() {
        Account account = new Account(1, new BigDecimal("1500.00"));
        account.withdraw(new BigDecimal("2000.00"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustValidateIfValueIsNegativeToTransfer() {
        Account account = new Account(1, new BigDecimal("1500.00"));
        account.withdraw(new BigDecimal("-2000.00"));
    }

}
