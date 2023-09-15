package com.gabri3445.dentist.controllers;

import com.gabri3445.dentist.api.API;
import com.gabri3445.dentist.api.contracts.LoginRequest;
import com.gabri3445.dentist.shared.SharedData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        cancelButton.setOnAction(event -> cancelButton.getScene().getWindow().hide());
        submitButton.setOnAction(event -> {
            //validate fields
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid fields");
                alert.setContentText("Please fill all the fields correctly");
                alert.showAndWait();
            } else {
                try {
                    SharedData.getInstance().sessionId = SharedData.getInstance().api.register(new LoginRequest(usernameField.getText(), passwordField.getText()));
                    SharedData.getInstance().isLoggedIn = true;
                    SharedData.getInstance().date = LocalDate.now();
                    cancelButton.getScene().getWindow().hide();
                } catch (Exception e) {
                    if (e instanceof API.ConflictException) {
                        //TODO: show error message
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Invalid credentials");
                        alert.setContentText("Username already exists");
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
            }
        });
    }
}
