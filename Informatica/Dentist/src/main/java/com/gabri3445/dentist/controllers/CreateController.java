package com.gabri3445.dentist.controllers;

import com.gabri3445.dentist.api.API;
import com.gabri3445.dentist.api.contracts.AddPatientRequest;
import com.gabri3445.dentist.helpers.WindowHelpers;
import com.gabri3445.dentist.models.Patient;
import com.gabri3445.dentist.shared.SharedData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CreateController {
    @FXML
    private TextField ageField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField taxIdField;
    @FXML
    private TextArea illnessField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;
    @FXML
    private TextField dateField;

    @FXML
    private void initialize() {
        submitButton.setOnAction(event -> {
            if (validateFields()) {
                LocalDate date = LocalDate.parse(dateField.getText());
                Patient patient = new Patient(nameField.getText(), surnameField.getText(), taxIdField.getText(), Integer.parseInt(ageField.getText()), illnessField.getText(), date);
                try {
                    SharedData.getInstance().api.addPatient(new AddPatientRequest(SharedData.getInstance().sessionId, patient));
                    SharedData.getInstance().addPatient(patient);
                } catch (Exception e) {
                    if (e instanceof API.ConflictException) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Invalid credentials");
                        alert.setContentText("TaxId already registered");
                        alert.showAndWait();
                    } else {
                        //TODO: show error message
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Unknown error");
                        alert.setContentText("Please try again later");
                        alert.showAndWait();
                        e.printStackTrace();
                    }
                }
                exit();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid fields");
                alert.setContentText("Please fill all the fields correctly");
                alert.showAndWait();
            }
        });

        cancelButton.setOnAction(event -> {
            if (!checkFieldsPopulated()) {
                exit();
            } else {
                if (WindowHelpers.createAlert("Alert", "Are you sure you want to cancel?", "Some fields are compiled")) {
                    exit();
                }
            }
        });
    }

    private void exit() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private boolean validateFields() {
        if (checkFieldsPopulated()) {
            //check that the age field is a number between 0 and 120
            return ageField.getText().matches("\\d+") && Integer.parseInt(ageField.getText()) >= 0 && Integer.parseInt(ageField.getText()) <= 120;
        }
        return false;
    }

    private boolean checkFieldsPopulated() {
        return !nameField.getText().isEmpty() && !surnameField.getText().isEmpty() && !taxIdField.getText().isEmpty() && !illnessField.getText().isEmpty() && !ageField.getText().isEmpty() && !dateField.getText().isEmpty();
    }
}
