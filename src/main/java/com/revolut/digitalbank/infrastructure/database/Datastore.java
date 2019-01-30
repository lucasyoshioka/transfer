package com.revolut.digitalbank.infrastructure.database;

import com.revolut.digitalbank.transfer.Account;
import com.revolut.digitalbank.transfer.Owner;
import com.revolut.digitalbank.transfer.Transaction;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

public class Datastore {

    private static Datastore datastore;

    private Set<Owner> owners = new HashSet<>();

    private Map<Owner, List<Transaction>> transactions = new HashMap<>();

    private Datastore() {
        save(new Owner(1, "Lucas", new Account(1, new BigDecimal("1000.00"))));
        save(new Owner(2, "Larissa", new Account(2, new BigDecimal("2000.00"))));
        save(new Owner(3, "Thiago", new Account(3, new BigDecimal("4000.00"))));
        save(new Owner(4, "Daniel", new Account(4, new BigDecimal("8000.00"))));
        save(new Owner(5, "Marcos", new Account(5, new BigDecimal("16000.00"))));
    }

    public static Datastore getInstance() {
        if (datastore == null) {
            datastore = new Datastore();
        }
        return datastore;
    }

    public void save(Owner owner) {
        owners.add(owner);
    }

    public void remove(Owner owner) {
        owners.remove(owner);
    }

    public void update(Owner owner) {
        remove(owner);
        save(owner);
    }

    public Owner findBy(Owner owner) {
        return owners.stream()
                .filter(a -> a.equals(owner))
                .findFirst()
                .orElseThrow(() -> new DatabaseException("Owner not found."));
    }

    public void save(Transaction transaction) {
        if (transactions.get(transaction.getFrom()) == null) {
            transactions.put(transaction.getFrom(), new ArrayList<>());
        }
        transactions.get(transaction.getFrom()).add(transaction);
    }

    public List<Transaction> getAllTransactions(Owner owner) {
        List<Transaction> list = transactions.get(owner);
        if (CollectionUtils.isEmpty(list)) {
            throw new DatabaseException("No transactions for this owner.");
        }
        return list;
    }

}
