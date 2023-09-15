package com.gabri3445.dentistapi.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private String username;
    private String password;
    private String sessionId;
    private List<Patient> patients;

    private LocalDate date = LocalDate.now();

    public User() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public User(String username, String password, String sessionId, List<Patient> patients) {
        this.username = username;
        this.password = password;
        this.sessionId = sessionId;
        this.patients = patients;
    }

    public User(String username, String password, String sessionId) {
        this.username = username;
        this.password = password;
        this.sessionId = sessionId;
        this.patients = new ArrayList<>();
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void removePatient(String taxId) {
        patients.removeIf(patient -> patient.getTaxId().equals(taxId));
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
