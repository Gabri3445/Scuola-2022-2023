package com.gabri3445.calculator.models;

import javafx.scene.control.Alert;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum Operator {
    Addition, Subtraction, Multiplication, Division, Radical, Power
}

public class CalculatorModel {
    private String expression = "";
    private String displayExpression = "";


    public String handleButton(String symbol) {
        final String symbolRegex = "[+\\-*/^√]";
        final Pattern pattern = Pattern.compile(symbolRegex);
        final Matcher matcher = pattern.matcher(symbol);
        // Check for equal, if so execute the operation
        if (Objects.equals(symbol, "=")) {
            if (expression.length() > 2) {
                displayExpression = executeOperation();
                expression = displayExpression;
            }
            // Branch for symbol
        } else if (matcher.find()) {
            // If there is already a symbol or the display is empty do nothing, otherwise add the symbol to the expression
            final Pattern pattern1 = Pattern.compile(symbolRegex);
            final Matcher matcher1 = pattern1.matcher(displayExpression);
            if (!matcher1.find() && displayExpression.length() != 0) {
                displayExpression = symbol;
                expression += " " + symbol + " ";
            }
            // Branch for numbers
        } else if (symbol.matches("\\d")) {
            // If there is a symbol on the screen reset the display
            if (displayExpression.matches(symbolRegex)) {
                displayExpression = "";
            }
            expression += symbol;
            displayExpression += symbol;
            // Branch for delete one or delete all
        } else if (symbol.matches("^(CE|C)$")) {
            if (Objects.equals(symbol, "CE")) {
                expression = "";
                displayExpression = "";
            } else {
                // Deletes the last character from both the expression and display expression
                displayExpression = displayExpression.substring(0, displayExpression.length() - 1); // This throws if the display is empty making the next line not execute and potentially delete more than it needs to TODO maybe add a try catch
                expression = expression.substring(0, expression.length() - 1);
            }
            // Branch for decimal point
        } else {
            // Only put the decimal point if there is a number
            if (displayExpression.matches("\\d")) {
                displayExpression += symbol;
                expression += symbol;
            }
        }
        return displayExpression;
    }

    // TODO implement just one operation, look into more operations later
    public String executeOperation() {
        String[] tokens = expression.split("\\s");
        // Temporary
        if (tokens.length > 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Multiple operations not supported");
            alert.show();
            return "Giglo";
        }
        double firstNumber = Double.parseDouble(tokens[0]);
        Operator operator = switch (tokens[1]) {
            case "+" -> Operator.Addition;
            case "-" -> Operator.Subtraction;
            case "*" -> Operator.Multiplication;
            case "/" -> Operator.Division;
            case "^" -> Operator.Power;
            case "√" -> Operator.Radical;
            default -> null;
        };
        if (tokens.length == 3) {
            double secondNumber = Double.parseDouble(tokens[2]);
            Operation operation = new Operation(firstNumber, secondNumber, operator);
            return String.valueOf(operation.execute());
        }
        if (operator == Operator.Power || operator == Operator.Radical) {
            Operation operation = new Operation(firstNumber, operator);
            return String.valueOf(operation.execute());
        }
        return "NaN";
    }
}

class Operation {
    private final double firstNumber;
    private final double secondNumber;
    private final Operator operator;

    public Operation(double firstNumber, double secondNumber, Operator operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }
    public Operation(double firstNumber, Operator operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = 2;
        this.operator = operator;
    }

    public double execute() {
        switch (operator) {
            case Addition -> {
                return firstNumber + secondNumber;
            }
            case Subtraction -> {
                return firstNumber - secondNumber;
            }
            case Multiplication -> {
                return firstNumber * secondNumber;
            }
            case Division -> {
                return firstNumber / secondNumber;
            }
            case Power -> {
                return Math.pow(firstNumber, secondNumber);
            }
            case Radical -> {
                return Math.pow(firstNumber, 1 / secondNumber);
            }
        }
        return 0;
    }
}
