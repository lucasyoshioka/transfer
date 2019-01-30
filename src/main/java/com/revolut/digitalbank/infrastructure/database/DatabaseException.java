package com.revolut.digitalbank.infrastructure.database;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        super(message);
    }

}
