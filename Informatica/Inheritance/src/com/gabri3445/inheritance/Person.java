package com.gabri3445.inheritance;

public class Person {
    private final String name;
    private final String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getPersonalData() {
        return name + " " + surname;
    }
}
