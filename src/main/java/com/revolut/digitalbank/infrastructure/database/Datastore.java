package com.revolut.digitalbank.infrastructure.database;

import com.revolut.digitalbank.transfer.Account;
import com.revolut.digitalbank.transfer.Customer;
import com.revolut.digitalbank.transfer.Transaction;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

public class Datastore {

    private static Datastore datastore;

    private Set<Customer> customers = new HashSet<>();

    private Map<Customer, List<Transaction>> transactions = new HashMap<>();

    private Datastore() {
        save(new Customer(1, "Lucas", new Account(1, new BigDecimal("1000.00"))));
        save(new Customer(2, "Larissa", new Account(2, new BigDecimal("2000.00"))));
        save(new Customer(3, "Thiago", new Account(3, new BigDecimal("4000.00"))));
        save(new Customer(4, "Daniel", new Account(4, new BigDecimal("8000.00"))));
        save(new Customer(5, "Marcos", new Account(5, new BigDecimal("16000.00"))));
    }

    public static Datastore getInstance() {
        if (datastore == null) {
            datastore = new Datastore();
        }
        return datastore;
    }

    public void save(Customer customer) {
        customers.add(customer);
    }

    public void remove(Customer customer) {
        customers.remove(customer);
    }

    public void update(Customer customer) {
        remove(customer);
        save(customer);
    }

    public Customer findBy(Customer customer) {
        return customers.stream()
                .filter(a -> a.equals(customer))
                .findFirst()
                .orElseThrow(() -> new DatabaseException("Customer not found."));
    }

    public void save(Transaction transaction) {
        transactions.computeIfAbsent(transaction.getFrom(), k -> new ArrayList<>());
        transactions.get(transaction.getFrom()).add(transaction);
    }

}
