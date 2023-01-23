package com.gabri3445.bank.server;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.Objects;

@Path("/bank")
public class BankServerResource {

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok().build();
    }

    @POST
    @Path("/create")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createAccount(@QueryParam("name") String name, @QueryParam("surname") String surname, @QueryParam("balance") float balance, @QueryParam("password") String password) {
        if (name == null || surname == null || password == null) {
            return Response.status(400).build();
        }
        BigDecimal bigDecimalBalance = BigDecimal.valueOf(balance);
        for (AccountHolder accountHolder : BankServerData.accountHolderList) {
            if (accountHolder.getPassword().equals(password)) {
                return Response.status(400).build();
            }
        }
        BankServerData.accountHolderList.add(new AccountHolder(password, name, surname, bigDecimalBalance));
        return Response.ok().build();
    }

    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response login(@QueryParam("password") String password) {
        if (password == null) {
            return Response.status(400).build();
        }
        for (AccountHolder accountHolder : BankServerData.accountHolderList) {
            if (Objects.equals(accountHolder.getPassword(), password)) {
                return Response.ok().build();
            }
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/viewMovements")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response viewMovements(@QueryParam("password") String password) {
        if (password == null) {
            return Response.status(400).build();
        }
        for (AccountHolder accountHolder : BankServerData.accountHolderList) {
            if (Objects.equals(accountHolder.getPassword(), password)) {
                String plainText = String.join("/n", accountHolder.getMovements());
                return Response.ok(plainText).build();
            }
        }
        return Response.status(404).build();
    }

    @GET
    @Path("/getBalance")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getBalance(@QueryParam("password") String password) {
        if (password == null) {
            return Response.status(400).build();
        }
        for (AccountHolder accountHolder : BankServerData.accountHolderList) {
            if (Objects.equals(accountHolder.getPassword(), password)) {
                return Response.ok(accountHolder.getBalance()).build();
            }
        }
        return Response.status(404).build();
    }

    @PUT
    @Path("/withdraw")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response withdraw(@QueryParam("password") String password, @QueryParam("withdrawBalance") float balance) {
        if (password == null) {
            return Response.status(400).build();
        }
        BigDecimal bigDecimalBalance = BigDecimal.valueOf(balance);
        for (AccountHolder accountHolder : BankServerData.accountHolderList) {
            if (accountHolder.getPassword().equals(password)) {
                if (accountHolder.withdraw(bigDecimalBalance) == AccountHolder.Result.Success) {
                    return Response.ok().build();
                }
                return Response.status(400).build();
            }
        }
        return Response.status(404).build();
    }

    @PUT
    @Path("/deposit")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deposit(@QueryParam("password") String password, @QueryParam("depositBalance") float balance) {
        if (password == null) {
            return Response.status(400).build();
        }
        BigDecimal bigDecimalBalance = BigDecimal.valueOf(balance);
        for (AccountHolder accountHolder : BankServerData.accountHolderList) {
            if (accountHolder.getPassword().equals(password)) {
                accountHolder.deposit(bigDecimalBalance);
                return Response.ok().build();
            }
        }
        return Response.status(404).build();
    }
}