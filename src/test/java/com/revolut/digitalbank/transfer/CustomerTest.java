package com.revolut.digitalbank.transfer;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void mustTransferValueToAnotherAccount() {
        Customer sender = new Customer(1, "Lucas", new Account(1, new BigDecimal("1500.00")));
        Customer recipient = new Customer(2, "João", new Account(2, new BigDecimal("500.00")));
        sender.transfer(new BigDecimal("500.00")).to(recipient).execute();
        assertEquals(new BigDecimal("1000.00"), sender.getBalance());
        assertEquals(new BigDecimal("1000.00"), recipient.getBalance());
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustNotTransferWhenValueIsMissing() {
        Customer sender = new Customer(1, "Lucas", new Account(1, new BigDecimal("1500.00")));
        Customer recipient = new Customer(2, "João", new Account(2, new BigDecimal("500.00")));
        sender.to(recipient).execute();
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustNotTransferWhenDestinationCustomerIsMissing() {
        Customer sender = new Customer(1, "Lucas", new Account(1, new BigDecimal("1500.00")));
        Customer recipient = new Customer(2, "João", new Account(2, new BigDecimal("500.00")));
        sender.transfer(new BigDecimal("500.00")).execute();
    }

}
