package com.revolut.digitalbank;

import com.revolut.digitalbank.infrastructure.server.GrizzlyServer;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        new GrizzlyServer().start();
    }
}
