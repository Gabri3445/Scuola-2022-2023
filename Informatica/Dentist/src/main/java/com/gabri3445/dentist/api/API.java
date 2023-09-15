package com.gabri3445.dentist.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.gabri3445.dentist.api.contracts.AddPatientRequest;
import com.gabri3445.dentist.api.contracts.LoginRequest;
import com.gabri3445.dentist.api.contracts.RemovePatientRequest;
import com.gabri3445.dentist.models.Patient;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class API {

    private final URI uri;

    public API(URI uri) {
        this.uri = uri;
    }

    /*
    @GET
    @Path("/ping")
    public Response hello() {
        return Response.ok().build();
    }
     */
    public void ping() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/ping"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            return;
        } else {
            throw new IOException();
        }
    }

    public String login(@NotNull LoginRequest request) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"username\": \"" + request.username + "\", \"password\": \"" + request.password + "\"}";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            return response.body();
        } else if (statusCode == 401) {
            throw new UnauthorizedException();
        } else {
            throw new Exception("Unexpected status code: " + statusCode);
        }
    }

    public static class UnauthorizedException extends Exception {
        public UnauthorizedException() {
            super("Invalid username or password");
        }
    }

    public String register(@NotNull LoginRequest request) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"username\": \"" + request.username + "\", \"password\": \"" + request.password + "\"}";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            return response.body();
        } else if (statusCode == 409) {
            throw new ConflictException();
        } else {
            throw new Exception("Unexpected status code: " + statusCode);
        }
    }

    public static class ConflictException extends Exception {
        public ConflictException() {
            super("Username already exists");
        }
    }

    public List<Patient> getPatients(String sessionId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/getPatients?sessionId=" + sessionId))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            String responseBody = response.body();
            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule module = new JavaTimeModule();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
            module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
            module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
            mapper.registerModule(module);
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.readValue(responseBody, new TypeReference<List<Patient>>() {
            });
        } else if (statusCode == 401) {
            throw new UnauthorizedException();
        } else {
            throw new Exception("Unexpected status code: " + statusCode);
        }
    }

    public void addPatient(AddPatientRequest request) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"sessionId\": \"" + request.sessionId + "\", \"patient\": " + request.patient.toJson() + "}";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/addPatient"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            // Success
        } else if (statusCode == 401) {
            throw new UnauthorizedException();
        } else if (statusCode == 409) {
            throw new ConflictException();
        } else {
            throw new Exception("Unexpected status code: " + statusCode);
        }
    }


    public void removePatient(RemovePatientRequest request) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"sessionId\": \"" + request.sessionId + "\", \"taxId\": \"" + request.taxId + "\"}";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/removePatient"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            // do something on success
        } else if (statusCode == 401) {
            throw new UnauthorizedException();
        } else {
            throw new Exception("Unexpected status code: " + statusCode);
        }
    }


    public void addDay(String sessionId) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = "{\"sessionId\": \"" + sessionId + "\"}";
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/addDay"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        int statusCode = response.statusCode();
        if (statusCode == 200) {

        } else if (statusCode == 401) {
            throw new UnauthorizedException();
        } else {
            throw new Exception("Unexpected status code: " + statusCode);
        }
    }
}
