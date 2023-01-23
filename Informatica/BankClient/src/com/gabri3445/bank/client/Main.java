package com.gabri3445.bank.client;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        /*
        String url = "http://localhost:8080/Bank-1.0-SNAPSHOT/bank/";
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(url += "ping");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status code : " + response.statusCode() + " OK");
        */
        int choice;
        HttpClient client = HttpClient.newHttpClient();
        String url = "http://localhost:8080/Bank-1.0-SNAPSHOT/bank/";
        do {
            System.out.println("""
                    [0] Ping the server
                    [1] Login
                    [2] Create an account
                    [3] Exit
                    """);
            choice = scanner.nextInt();
            switch (choice) {
                case 0 -> {
                    switch (ping(url, client)) {
                        case 0 -> System.out.println("Server reachable");
                        case -1 -> System.out.println("Server not reachable");
                    }
                }
                case 1 -> {
                    if (ping(url, client) == 0) {
                        login(url, client);
                    } else {
                        System.out.println("Server not reachable");
                    }
                }
                case 2 -> {
                    if (ping(url, client) == 0) {
                        create(url, client);
                    } else {
                        System.out.println("Server not reachable");
                    }
                }
            }

        } while (choice != 3);
    }

    private static void create(String url, HttpClient httpClient) throws IOException, InterruptedException {
        int responseCode;
        url += "create";
        String tempUrl = url;
        do {
            url = tempUrl;
            System.out.println();
            System.out.println("Enter the users name");
            String name = scanner.next();
            System.out.println("Enter the users surname");
            String surname = scanner.next();
            System.out.println("Enter the users initial balance");
            float initBalance = scanner.nextFloat();
            System.out.println("Enter the user password");
            String password = scanner.next();
            String queryString = "?name=" + URLEncoder.encode(name, StandardCharsets.UTF_8) + "&surname=" + URLEncoder.encode(surname, StandardCharsets.UTF_8) + "&balance=" + initBalance + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);
            URI uri = URI.create(url += queryString);
            HttpRequest request = HttpRequest.newBuilder().uri(uri).POST(HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            responseCode = response.statusCode();
        } while (responseCode != 200);
    }

    private static void login(String url, HttpClient httpClient) throws IOException, InterruptedException {
        int responseCode;
        String tempUrl = url;
        url += "login";
        int choice;
            System.out.println("""
                    [0] Login
                    [1] Exit""");
            choice = scanner.nextInt();
        if (choice == 0) {
            System.out.println("Enter your password");
            String password = scanner.next();
            String queryString = "?password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);
            URI uri = URI.create(url += queryString);
            HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                System.out.println("Login Successful");
                userMenu(password, tempUrl, httpClient);
            }
            else {
                System.out.println("Login unsuccessful");
            }
        }
    }

    private static void userMenu(String password, String url, HttpClient httpClient) throws IOException, InterruptedException {
        int choice;
        int responseCode;
        String depositURL = url + "deposit";
        String withdrawalURL = url + "withdraw";
        String balanceURL = url + "getBalance";
        String movementsURL = url + "viewMovements";

        do {
            System.out.println("""
                [0] Deposit
                [1] Withdraw
                [2] View Movements
                [3] View Balance
                [4] Exit""");
            choice = scanner.nextInt();
            switch (choice) {
                case 0 -> {
                    System.out.println("How much do you wish to deposit?");
                    float balance = scanner.nextFloat();
                    if (balance > 0) {
                        String queryString = "?password=" + URLEncoder.encode(password, StandardCharsets.UTF_8) + "&depositBalance=" + URLEncoder.encode(String.valueOf(balance), StandardCharsets.UTF_8);
                        URI uri = URI.create(depositURL + queryString);
                        HttpRequest request = HttpRequest.newBuilder().uri(uri).PUT(HttpRequest.BodyPublishers.noBody()).build();
                        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        responseCode = response.statusCode();
                        if (responseCode == 400) {
                            System.out.println("Provide a password");
                        }
                    }
                }
                case 1 -> {
                    System.out.println("How much do you wish to withdraw?");
                    float balance = scanner.nextFloat();
                    if (balance > 0) {
                        String queryString = "?password=" + URLEncoder.encode(password, StandardCharsets.UTF_8) + "&withdrawBalance=" + URLEncoder.encode(String.valueOf(balance), StandardCharsets.UTF_8);
                        URI uri = URI.create(withdrawalURL + queryString);
                        HttpRequest request = HttpRequest.newBuilder().uri(uri).PUT(HttpRequest.BodyPublishers.noBody()).build();
                        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                        responseCode = response.statusCode();
                        if (responseCode == 400) {
                            System.out.println("Provide a password");
                        }
                    }
                }
                case 2 -> {
                    String queryString = "?password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);
                    URI uri = URI.create(movementsURL + queryString);
                    HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                }
                case 3 -> {
                    String queryString = "?password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);
                    URI uri = URI.create(balanceURL + queryString);
                    HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    System.out.println(response.body());
                }
            }
        } while (choice != 4);
    }

    private static int ping(String url, @NotNull HttpClient httpClient) {
        URI uri = URI.create(url += "ping");
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return 0;
        } catch (IOException | InterruptedException e) {
            return -1;
        }
    }
}
