package com.revolut.digitalbank.transfer;

import com.google.gson.Gson;
import com.revolut.digitalbank.infrastructure.database.DatabaseException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/transaction")
public class Controller {

    private Service service;

    private Gson gson;

    public Controller() {
        service = new Service();
        gson = new Gson();
    }

    @POST
    @Path("/transfer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transfer(String data) {
        Response response;
        try {
            RequestDto requestDto = gson.fromJson(data, RequestDto.class);
            Transaction transaction = service.transfer(requestDto);
            response = Response.status(Response.Status.OK).entity(gson.toJson(transaction)).build();
        } catch (IllegalStateException | DatabaseException e) {
            response = Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(e.getMessage())).build();
        }
        return response;
    }

    @GET
    @Path("/balance/owner/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBalance(@PathParam("ownerId") Integer ownerId) {
        Response response;
        try {
            BalanceDto dto = service.getBalance(ownerId);
            response = Response.status(Response.Status.OK).entity(gson.toJson(dto)).build();
        } catch (DatabaseException e) {
            response = Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(e.getMessage())).build();
        }
        return response;
    }

}
