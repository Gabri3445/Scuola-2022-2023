package com.gabri3445.bank.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankServerData {
    public static List<AccountHolder> accountHolderList = new ArrayList<>();

    private static boolean isLoaded = false;

    public static void saveStatus() {
        if (accountHolderList.size() > 0) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(new File("accounts.json"), accountHolderList);
            } catch (IOException e) {
                System.out.println("Can't write");
            }
        }
    }

    public static void loadStatus() {
        if (!isLoaded) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<AccountHolder> accountHolders = new ArrayList<>();
            try {
                accountHolders = objectMapper.readValue(new File("accounts.json"), new TypeReference<List<AccountHolder>>() {
                });
            } catch (IOException e) {
                System.out.println("Can't read");
            }
            if (accountHolders != null) {
                accountHolderList = accountHolders;
            }
            isLoaded = true;
        }
    }
}
