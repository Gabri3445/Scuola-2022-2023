package com.gabri3445.dentistapi;

import com.gabri3445.dentistapi.contracts.AddPatientRequest;
import com.gabri3445.dentistapi.contracts.GetPatientRequest;
import com.gabri3445.dentistapi.contracts.LoginRequest;
import com.gabri3445.dentistapi.contracts.RemovePatientRequest;
import com.gabri3445.dentistapi.models.Patient;
import com.gabri3445.dentistapi.models.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Path("")
public class DentistResource {
    @GET
    @Path("/ping")
    public Response hello() {
        return Response.ok().build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@NotNull LoginRequest request){
        DentistData.getInstance().load();
        //find if there is a user registered with the given credentials
        //if there is, return the session id
        //if not, return null
        User user = findUser(DentistData.getInstance().getUsers(), request.username, request.password);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(user.getSessionId() + "\n" + user.getDate()).build();
    }
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@NotNull LoginRequest request){
        DentistData.getInstance().load();
        //find if there is a user registered with the given credentials
        //if there is, return null
        //if not, create a new user
        User user = findUser(DentistData.getInstance().getUsers(), request.username);
        if (user != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        StringBuilder sessionId = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            sessionId.append((char) (Math.random() * 26 + 97));
        }
        User newUser = new User(request.username, request.password, sessionId.toString());
        DentistData.getInstance().getUsers().add(newUser);
        DentistData.getInstance().save();
        return Response.ok(newUser.getSessionId()).build();
    }

    @GET
    @Path("/getPatients")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatients(@QueryParam("sessionId") String sessionId) {
        DentistData.getInstance().load();
        User user = findUserById(DentistData.getInstance().getUsers(), sessionId);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(user.getPatients()).build();
    }

    @POST
    @Path("/addPatient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(@NotNull AddPatientRequest request){
        DentistData.getInstance().load();
        User user = findUserById(DentistData.getInstance().getUsers(), request.sessionId);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        Patient patient = findPatient(user.getPatients(), request.patient.getTaxId());
        if (patient != null) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        user.addPatient(request.patient);
        DentistData.getInstance().save();
        return Response.ok().build();
    }

    @POST
    @Path("/removePatient")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removePatient(@NotNull RemovePatientRequest request){
        DentistData.getInstance().load();
        User user = findUserById(DentistData.getInstance().getUsers(), request.sessionId);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        user.removePatient(request.taxId);
        DentistData.getInstance().save();
        return Response.ok().build();
    }

    @POST
    @Path("/addDay")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDay(GetPatientRequest request) {
        DentistData.getInstance().load();
        User user = findUserById(DentistData.getInstance().getUsers(), request.sessionId);
        if (user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        user.setDate(user.getDate().plusDays(1));
        DentistData.getInstance().save();
        return Response.ok().build();
    }

    private User findUser(@NotNull List<User> users, String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null); // user not found
    }

    private User findUser(@NotNull List<User> users, String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null); // user not found
    }

    private User findUserById(@NotNull List<User> users, String sessionId) {
        return users.stream()
                .filter(u -> u.getSessionId().equals(sessionId))
                .findFirst()
                .orElse(null); // user not found
    }

    private Patient findPatient(@NotNull List<Patient> patients, String taxId) {
        return patients.stream()
                .filter(p -> p.getTaxId().equals(taxId))
                .findFirst()
                .orElse(null); // patient not found
    }
}