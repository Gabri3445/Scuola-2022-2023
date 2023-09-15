package com.gabri3445.dentist.controllers;

import com.gabri3445.dentist.DentistApplication;
import com.gabri3445.dentist.helpers.WindowHelpers;
import com.gabri3445.dentist.models.Patient;
import com.gabri3445.dentist.shared.SharedData;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class MainController {
    @FXML
    private ListView<HBox> listView;
    @FXML
    private Label currentDayLabel;
    @FXML
    private MenuItem advanceDayMenuItem;
    @FXML
    private Label numberOfPatientsLabel;
    @FXML
    private Label lastPatientNameLabel;
    @FXML
    private Label lastPatientSurnameLabel;
    @FXML
    private Label lastPatientAgeLabel;
    @FXML
    private Label lastPatientTaxIDLabel;
    @FXML
    private Label lastPatientIllnessLabel;
    @FXML
    private MenuItem viewPatientsMenuItem;
    @FXML
    private MenuItem createPatientMenuItem;
    @FXML
    private MenuItem registerMenuItem;
    @FXML
    private MenuItem loginMenuItem;
    @FXML
    private MenuItem logoutMenuItem;


    @FXML
    private void initialize() {
        logoutMenuItem.setVisible(false);
        refreshData();
        viewPatientsMenuItem.setOnAction(event -> {
            if (SharedData.getInstance().isLoggedIn) {
                try {
                    WindowHelpers.createWindow("view-window.fxml", "View Patients", 850, 400, true, DentistApplication.class);
                    refreshData();
                } catch (IOException ignored) {

                }
            }
        });

        createPatientMenuItem.setOnAction(event -> {
           if (SharedData.getInstance().isLoggedIn) {
               try {
                   WindowHelpers.createWindow("create-window.fxml", "Create Patient", 650, 400, true, DentistApplication.class);
                   refreshData();
               } catch (IOException ignored) {
               }
           }
        });

        loginMenuItem.setOnAction(event -> {
            try {
                WindowHelpers.createWindow("login-window.fxml", "Login", 300, 200, true, DentistApplication.class);
                if (SharedData.getInstance().isLoggedIn) {
                    loginMenuItem.setVisible(false);
                    logoutMenuItem.setVisible(true);
                    registerMenuItem.setVisible(false);
                }
                refreshData();
            } catch (IOException ignored) {

            }
        });

        registerMenuItem.setOnAction(event -> {
            try {
                WindowHelpers.createWindow("register-window.fxml", "Register", 300, 200, true, DentistApplication.class);
                if (SharedData.getInstance().isLoggedIn) {
                    loginMenuItem.setVisible(false);
                    logoutMenuItem.setVisible(true);
                    registerMenuItem.setVisible(false);
                }
                refreshData();
            } catch (IOException ignored) {

            }
        });


        logoutMenuItem.setOnAction(event -> {
            if (SharedData.getInstance().isLoggedIn) {
                SharedData.getInstance().isLoggedIn = false;
                loginMenuItem.setVisible(true);
                logoutMenuItem.setVisible(false);
                registerMenuItem.setVisible(true);
                refreshData();
            }
        });

        advanceDayMenuItem.setOnAction(event -> {
            if (SharedData.getInstance().isLoggedIn) {
                try {
                    SharedData.getInstance().api.addDay(SharedData.getInstance().sessionId);
                    SharedData.getInstance().date = SharedData.getInstance().date.plusDays(1);
                    refreshData();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Unknown error");
                    alert.setContentText("Please try again later");
                    alert.showAndWait();
                }
                refreshData();
            }
        });
    }

    private void refreshData() {
        try {
            if (SharedData.getInstance().isLoggedIn) {
                SharedData.getInstance().setPatientList(SharedData.getInstance().api.getPatients(SharedData.getInstance().sessionId));
                numberOfPatientsLabel.setText(String.valueOf(SharedData.getInstance().getPatientList().size()));
                if (SharedData.getInstance().getPatientList().size() > 0) {
                    lastPatientNameLabel.setText("Name: " + SharedData.getInstance().getPatientList().get(SharedData.getInstance().getPatientList().size() - 1).getName());
                    lastPatientSurnameLabel.setText("Surname: " + SharedData.getInstance().getPatientList().get(SharedData.getInstance().getPatientList().size() - 1).getSurname());
                    lastPatientAgeLabel.setText("Age: " + SharedData.getInstance().getPatientList().get(SharedData.getInstance().getPatientList().size() - 1).getAge());
                    lastPatientTaxIDLabel.setText("TaxID: " + SharedData.getInstance().getPatientList().get(SharedData.getInstance().getPatientList().size() - 1).getTaxId());
                    lastPatientIllnessLabel.setText("Illness: " + SharedData.getInstance().getPatientList().get(SharedData.getInstance().getPatientList().size() - 1).getIllness());
                    listView.getItems().clear();
                    for (Patient patient : SharedData.getInstance().getPatientList()) {
                        if (patient.getAppointmentDate().equals(SharedData.getInstance().date) || patient.getAppointmentDate().isBefore(SharedData.getInstance().date)) {
                            listView.getItems().add(createListItem(patient.getTaxId(), patient.getName(), patient.getSurname()));
                        }
                    }
                } else {
                    lastPatientNameLabel.setText("Name: None");
                    lastPatientSurnameLabel.setText("Surname: None");
                    lastPatientAgeLabel.setText("Age: None");
                    lastPatientTaxIDLabel.setText("TaxID: None");
                    lastPatientIllnessLabel.setText("Illness: None");
                }
                currentDayLabel.setText("Current Day: " + SharedData.getInstance().date.getDayOfMonth() + "/" + SharedData.getInstance().date.getMonthValue() + "/" + SharedData.getInstance().date.getYear());
            }
        } catch (Exception ignored) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Unknown error");
            alert.setContentText("Please try again later");
            alert.showAndWait();
        }
    }

    private @NotNull HBox createListItem(String taxId, String name, String surname) {
        HBox hbox = new HBox();
        Label taxIdBox = new Label("TaxID: " + taxId);
        HBox.setMargin(taxIdBox, new Insets(0, 30, 0, 0));
        Label nameBox = new Label("Name: " + name);
        HBox.setMargin(nameBox, new Insets(0, 30, 0, 0));
        Label surnameBox = new Label("Surname: " + surname);
        hbox.getChildren().addAll(taxIdBox, nameBox, surnameBox);
        return hbox;
    }

}