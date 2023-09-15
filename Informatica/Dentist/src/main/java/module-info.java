module com.gabri3445.dentist {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    opens com.gabri3445.dentist to javafx.fxml;
    exports com.gabri3445.dentist;
    exports com.gabri3445.dentist.controllers;
    exports com.gabri3445.dentist.models;
    opens com.gabri3445.dentist.controllers to javafx.fxml;
}