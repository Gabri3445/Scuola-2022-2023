package com.gabri3445.phonebookex;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Contact(String name, String surname, String phoneNumber) {

    @Contract(pure = true)
    @Override
    public @NotNull String toString() {
        return "Name: " + name + "\n" +
                "Surname: " + surname + "\n" +
                "Phone number: " + phoneNumber + "\n";
    }
}
