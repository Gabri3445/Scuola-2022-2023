package com.gabri3445.dentistapi.contracts;

public class GetPatientRequest {
    public String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
