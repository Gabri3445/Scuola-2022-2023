package com.gabri3445.phonebookex;

public record Contact(String name, String surname, String phoneNumber) {

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Phone number: " + phoneNumber + "\n";
    }
}
