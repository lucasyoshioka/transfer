package com.revolut.digitalbank.transfer;

import com.revolut.digitalbank.infrastructure.database.Datastore;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Service {

    private Datastore datastore;

    public Service() {
        datastore = Datastore.getInstance();
    }

    public BalanceDto getBalance(Integer ownerId) {
        Owner owner = datastore.findBy(new Owner(ownerId));
        return new BalanceDto(owner.getBalance());
    }

    public List<Transaction> getTransactions(Integer ownerId) {
        return datastore.getAllTransactions(new Owner(ownerId));
    }

    public Transaction transfer(RequestDto dto) {
        Owner sender = datastore.findBy(new Owner(dto.getFrom()));
        Owner recipient = datastore.findBy(new Owner(dto.getTo()));
        sender.transfer(new BigDecimal(dto.getValue()))
                .to(recipient)
                .execute();
        datastore.update(sender);
        datastore.update(recipient);
        return createTransaction(sender, recipient);
    }

    private Transaction createTransaction(Owner from, Owner destination) {
        Transaction transaction = new Transaction(from, destination, LocalDateTime.now());
        datastore.save(transaction);
        return transaction;

    }

}
