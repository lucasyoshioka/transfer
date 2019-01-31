package com.revolut.digitalbank.transfer.infrastructure.database;

import com.revolut.digitalbank.infrastructure.database.Datastore;
import com.revolut.digitalbank.transfer.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DatastoreTest {

    private Datastore datastore;

    @Before
    public void datastore() {
        datastore = Datastore.getInstance();
    }

    @Test
    public void mustFindByCustomer() {
        Customer customer = datastore.findBy(new Customer(5));
        Assert.assertEquals("Marcos", customer.getName());
    }

}
