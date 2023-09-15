package com.gabri3445.dentistapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.gabri3445.dentistapi.models.User;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DentistData {
    private static final DentistData instance = new DentistData();

    private List<User> users = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    private DentistData() {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        JavaTimeModule module = new JavaTimeModule();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
        mapper.registerModule(module);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static DentistData getInstance() {
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> patientList) {
        this.users = patientList;
    }

    public void addUser(User patient) {
        this.users.add(patient);
    }

    public void save() {
        try {
            System.out.println(mapper);
            mapper.writeValue(new File("data.txt"), DentistData.getInstance().getUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            DentistData.getInstance().setUsers(mapper.readValue(new File("data.txt"), mapper.getTypeFactory().constructCollectionType(List.class, User.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
