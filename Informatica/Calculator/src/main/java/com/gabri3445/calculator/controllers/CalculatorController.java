package com.gabri3445.calculator.controllers;

import com.gabri3445.calculator.models.CalculatorModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;

public class CalculatorController {
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;
    @FXML
    private Button divide;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button multiply;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button subtract;
    @FXML
    private Button decimal;
    @FXML
    private Button zero;
    @FXML
    private Button equal;
    @FXML
    private Button addition;
    @FXML
    private Label screen;

    // TODO add a delete all button and delete one button. Maybe on the bottom row

    private CalculatorModel calculatorModel;

    @FXML
    private void initialize() {
        calculatorModel = new CalculatorModel();
        /*
        button.setOnAction(event -> {});
         */
        List<Button> buttons = new ArrayList<>() {
            {
                add(seven);
                add(eight);
                add(nine);
                add(divide);
                add(four);
                add(five);
                add(six);
                add(multiply);
                add(one);
                add(two);
                add(three);
                add(subtract);
                add(decimal);
                add(zero);
                add(equal);
                add(addition);
            }
        };
        for (Button button : buttons) {
            button.setOnAction(event -> {

            });
        }
    }
    private void updateData(String screen) {
        this.screen.setText(screen);
    }
}