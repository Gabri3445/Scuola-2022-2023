package com.gabri3445.phonebookex;

import com.gabri3445.menu.Arguments;
import com.gabri3445.menu.Menu;

import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Menu menu = new Menu();
        Map<Integer, Arguments> actions = Map.of(
                0, new Arguments(() -> {
                    System.out.println("Enter the name");
                    String name = scanner.nextLine();
                    System.out.println("Enter the surname");
                    String surname = scanner.nextLine();
                    System.out.println("Enter the phone number");
                    String phoneNumber = scanner.nextLine();
                    phoneBook.addContact(name, surname, phoneNumber);
                }, "Add a contact"),
                1, new Arguments(() -> {
                    System.out.println("Enter the name");
                    String name = scanner.nextLine();
                    System.out.println("Enter the surname");
                    String surname = scanner.nextLine();
                    phoneBook.removeContact(name, surname);
                }, "Remove a contact"),
                2, new Arguments(() -> {
                    System.out.println("Enter the name");
                    String name = scanner.nextLine();
                    System.out.println("Enter the surname");
                    String surname = scanner.nextLine();
                    try {
                        System.out.println("Phone number: " + phoneBook.find(name, surname));
                    } catch (NullPointerException e) {
                        System.out.println("Cannot find user");
                    }
                }, "Find a contact"),
                3, new Arguments(() -> System.out.println(phoneBook), "Print the phone book")
        );
        menu.executeMenu(actions);
    }
}
