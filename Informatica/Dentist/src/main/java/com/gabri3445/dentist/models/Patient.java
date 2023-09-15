package com.gabri3445.dentist.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Patient {
    private String name;
    private String surname;
    private String taxId;
    private int age;
    private String illness;

    private LocalDate appointmentDate;

    public Patient(String name, String surname, String taxId, int age, String illness, LocalDate appointmentDate) {
        this.name = name;
        this.surname = surname;
        this.taxId = taxId;
        this.age = age;
        this.illness = illness;
        this.appointmentDate = appointmentDate;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setIllness(String illness) {
        this.illness = illness;
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

    public float getAge() {
        return age;
    }

    public String toJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(this);
    }
}
