package com.gabri3445.dentist.shared;

import com.gabri3445.dentist.api.API;
import com.gabri3445.dentist.models.Patient;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SharedData {
    private static final SharedData instance;

    static {
        try {
            instance = new SharedData();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Patient> patientList = new ArrayList<>();

    private SharedData() throws URISyntaxException {
    }

    public static SharedData getInstance() {
        return instance;
    }

    public String sessionId;

    public LocalDate date;

    public boolean isLoggedIn = false;

    public API api = new API(new URI("http://localhost:8080/Dentist"));

    public List<Patient> getPatientList() {
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public void addPatient(Patient patient) {
        this.patientList.add(patient);
    }
}