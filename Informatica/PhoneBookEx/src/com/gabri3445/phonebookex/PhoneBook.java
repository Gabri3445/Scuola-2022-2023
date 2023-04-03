package com.gabri3445.phonebookex;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PhoneBook {
    private final List<Contact> contacts;

    public PhoneBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String surname, String number) {
        contacts.add(new Contact(name, surname, number));
    }

    public void removeContact(String name, String surname) {
        contacts.remove(findContact(name, surname));
    }

    public String find(String name, String surname) {
        return Objects.requireNonNull(findContact(name, surname)).getPhoneNumber();
    }

    private @Nullable Contact findContact(String name, String surname) {
        for (Contact contact :
                contacts) {
            if (Objects.equals(contact.getName(), name) && Objects.equals(contact.getSurname(), surname)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        for (Contact contact :
                contacts) {
            returnString.append(contact.toString());
            returnString.append("\n");
        }
        return returnString.toString();
    }
}
