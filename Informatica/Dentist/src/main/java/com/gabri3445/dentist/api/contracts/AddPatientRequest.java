package com.gabri3445.dentist.api.contracts;

import com.gabri3445.dentist.models.Patient;

public class AddPatientRequest {
    public AddPatientRequest(String sessionId, Patient patient) {
        this.sessionId = sessionId;
        this.patient = patient;
    }

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
