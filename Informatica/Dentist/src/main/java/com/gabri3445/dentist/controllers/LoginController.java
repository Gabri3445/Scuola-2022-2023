package com.gabri3445.dentist.controllers;

import com.gabri3445.dentist.api.API;
import com.gabri3445.dentist.api.contracts.LoginRequest;
import com.gabri3445.dentist.shared.SharedData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class LoginController {
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
                //TODO: show error message
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("Invalid fields");
                alert.setContentText("Please fill all the fields correctly");
                alert.showAndWait();
            }
            else {
                try {
                    String response = SharedData.getInstance().api.login(new LoginRequest(usernameField.getText(), passwordField.getText()));
                    String[] tokens = response.split("\\r?\\n");
                    SharedData.getInstance().sessionId = tokens[0];
                    SharedData.getInstance().date = LocalDate.parse(tokens[1]);
                    SharedData.getInstance().isLoggedIn = true;
                    cancelButton.getScene().getWindow().hide();
                } catch (Exception e) {
                    if (e instanceof API.UnauthorizedException) {
                        //TODO: show error message
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Invalid credentials");
                        alert.setContentText("Please check your credentials");
                        alert.showAndWait();
                    }
                    else {
                        //TODO: show error message
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText("Unknown error");
                        alert.setContentText("Please try again later");
                        alert.showAndWait();
                    }
                }
            }
        });
    }
}
