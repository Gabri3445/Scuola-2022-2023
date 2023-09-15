package com.gabri3445.dentist.controllers;

import com.gabri3445.dentist.api.contracts.RemovePatientRequest;
import com.gabri3445.dentist.models.Patient;
import com.gabri3445.dentist.shared.SharedData;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import org.jetbrains.annotations.NotNull;

public class ViewController {
    @FXML
    private Label patientAppDate;
    @FXML
    private Label patientNameLabel;
    @FXML
    private Label patientSurnameLabel;
    @FXML
    private Label patientAgeLabel;
    @FXML
    private Label patientTaxIDLabel;
    @FXML
    private Label patientIllnessLabel;
    @FXML
    private ListView<HBox> listView;
    @FXML
    private Label selectedPatientTaxIDLabel;
    @FXML
    private Button removeButton;

    private HBox selectedPatientHBox;

    @FXML
    private void initialize() {
        populateListView();

        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                selectedPatientHBox = newValue;
                selectedPatientTaxIDLabel = (Label) selectedPatientHBox.getChildren().get(0);
                String taxId = selectedPatientTaxIDLabel.getText().substring(7);
                refreshData(taxId);
            } catch (Exception ignored) {
            }
        });
        removeButton.setOnAction(event -> {
            if (selectedPatientHBox != null) {
                try {
                    String taxId = selectedPatientTaxIDLabel.getText().substring(7);
                    SharedData.getInstance().getPatientList().removeIf(p -> p.getTaxId().equals(taxId));
                    SharedData.getInstance().api.removePatient(new RemovePatientRequest(SharedData.getInstance().sessionId, taxId));
                    listView.getItems().remove(selectedPatientHBox);
                    selectedPatientHBox = null;
                    selectedPatientTaxIDLabel = null;
                    refreshData(taxId);
                } catch (Exception ignored) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText("Unknown error");
                    alert.setContentText("Please try again later");
                    alert.showAndWait();
                }
            }
        });
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

    private void populateListView() {
        for (Patient patient : SharedData.getInstance().getPatientList()) {
            HBox hbox = createListItem(patient.getTaxId(), patient.getName(), patient.getSurname());
            listView.getItems().add(hbox);
        }
    }

    private void refreshData(String taxId) {
        Patient patient = SharedData.getInstance().getPatientList().stream()
                .filter(p -> p.getTaxId().equals(taxId))
                .findFirst().orElse(null);
        if (patient != null) {
            patientNameLabel.setText("Name: " + patient.getName());
            patientSurnameLabel.setText("Surname: " + patient.getSurname());
            patientAgeLabel.setText("Age: " + patient.getAge());
            patientTaxIDLabel.setText("TaxID: " + patient.getTaxId());
            patientIllnessLabel.setText("Illness: " + patient.getIllness());
            patientAppDate.setText("Appointment date: " + patient.getAppointmentDate());
        } else {
            //set to default "*: None"
            patientNameLabel.setText("Name: None");
            patientSurnameLabel.setText("Surname: None");
            patientAgeLabel.setText("Age: None");
            patientTaxIDLabel.setText("TaxID: None");
            patientIllnessLabel.setText("Illness: None");
            patientAppDate.setText("Appointment date: None");
        }
    }
}
