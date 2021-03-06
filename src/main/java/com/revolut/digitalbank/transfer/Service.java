package com.revolut.digitalbank.transfer;

import com.revolut.digitalbank.infrastructure.database.Datastore;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Service {

    private Datastore datastore;

    public Service() {
        datastore = Datastore.getInstance();
    }

    public BalanceDto getBalance(Integer ownerId) {
        Customer customer = datastore.findBy(new Customer(ownerId));
        return new BalanceDto(customer.getBalance());
    }

    public Transaction transfer(RequestDto dto) {
        Customer sender = datastore.findBy(new Customer(dto.getFrom()));
        Customer recipient = datastore.findBy(new Customer(dto.getTo()));
        sender.transfer(new BigDecimal(dto.getValue()))
                .to(recipient)
                .execute();
        datastore.update(sender);
        datastore.update(recipient);
        return createTransaction(sender, recipient);
    }

    private Transaction createTransaction(Customer from, Customer destination) {
        Transaction transaction = new Transaction(from, destination.getName(), LocalDateTime.now());
        datastore.save(transaction);
        return transaction;

    }

}
