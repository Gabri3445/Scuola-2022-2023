module com.gabri3445.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.gabri3445.calculator to javafx.fxml;
    exports com.gabri3445.calculator;
    exports com.gabri3445.calculator.controllers;
    opens com.gabri3445.calculator.controllers to javafx.fxml;
}