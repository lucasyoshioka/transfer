package com.revolut.digitalbank.transfer;

import com.google.gson.Gson;
import com.revolut.digitalbank.infrastructure.server.GrizzlyServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ControllerTest {

    private GrizzlyServer server;

    private Gson gson;

    @Before
    public void startServer() throws IOException {
        server = new GrizzlyServer();
        gson = new Gson();
        server.startServer();
    }

    @After
    public void stopServer() {
        server.stopServer();
    }

    @Test
    public void mustGetTheRightAccountBalance() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Response response = target.path("/transaction/balance/owner/1").request().get();
        BalanceDto balanceDto = gson.fromJson(response.readEntity(String.class), BalanceDto.class);
        assertEquals(new BigDecimal("1000.00"), balanceDto.getBalance());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void mustNotReturnBalanceWhenOwnerNotFound() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Response response = target.path("/transaction/balance/owner/6").request().get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void mustTransferAmountFromAccountToAnother() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Response response = target.path("/transaction/transfer").request()
                .post(Entity.entity(oneTransfer(), MediaType.APPLICATION_JSON));
        Transaction transaction = gson.fromJson(response.readEntity(String.class), Transaction.class);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Lucas", transaction.getFrom().getName());
        assertEquals("Larissa", transaction.getTo().getName());
        verifyBalance(target, 1, "920.00");
        verifyBalance(target, 2, "2080.00");
    }

    @Test
    public void mustNotTransferWhenOwnerNotFound() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Response response = target.path("/transaction/transfer").request()
                .post(Entity.entity(oneTransferWithWrongOwner(), MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void mustNotTransferWhenBalanceIsNotEnough() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        Response response = target.path("/transaction/transfer").request()
                .post(Entity.entity(oneTransferWithNoBalanceEnough(), MediaType.APPLICATION_JSON));
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    private void verifyBalance(WebTarget target, Integer ownerId, String value) {
        Response balance = target.path("/transaction/balance/owner/" + ownerId).request().get();
        BalanceDto balanceDto = gson.fromJson(balance.readEntity(String.class), BalanceDto.class);
        assertEquals(new BigDecimal(value), balanceDto.getBalance());
    }

    private String oneTransfer() {
        RequestDto dto = new RequestDto(1, 2, "80.00");
        return gson.toJson(dto);
    }

    private String oneTransferWithWrongOwner() {
        RequestDto dto = new RequestDto(1, 12, "80.00");
        return gson.toJson(dto);
    }

    private String oneTransferWithNoBalanceEnough() {
        RequestDto dto = new RequestDto(1, 12, "15000.00");
        return gson.toJson(dto);
    }

}
