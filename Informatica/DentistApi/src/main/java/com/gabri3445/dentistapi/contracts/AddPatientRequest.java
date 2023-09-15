package com.gabri3445.dentistapi.contracts;

import com.gabri3445.dentistapi.models.Patient;

public class AddPatientRequest {
    public String sessionId;
    public Patient patient;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
