package com.gabri3445.dentistapi.models;

import java.time.LocalDate;
import java.util.Date;

public class Patient {
    private String name;
    private String surname;
    private String taxId;
    private int age;
    private String illness;

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    private LocalDate appointmentDate;

    public Patient(String name, String surname, String taxId, int age, String illness,  LocalDate appointmentDate) {
        this.name = name;
        this.surname = surname;
        this.taxId = taxId;
        this.age = age;
        this.illness = illness;
        this.appointmentDate = appointmentDate;
    }

    public Patient() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public int getAge() {
        return age;
    }

    public String getIllness() {
        return illness;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
