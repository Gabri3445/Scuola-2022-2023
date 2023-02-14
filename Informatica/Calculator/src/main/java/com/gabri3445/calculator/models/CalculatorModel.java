package com.gabri3445.calculator.models;

import javafx.scene.control.Alert;

import java.util.Objects;
import java.util.Stack;
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
        final Matcher displayMatcher = pattern.matcher(displayExpression);
        final Matcher symbolMatcher = pattern.matcher(symbol);
        // Check for equal, if so execute the operation
        if (Objects.equals(symbol, "=")) {
            if (expression.length() > 2) {
                displayExpression = executeOperation();
                expression = displayExpression;
            }
            // Branch for symbol
        } else if (symbolMatcher.find()) {
            // If there is already a symbol or the display is empty do nothing, otherwise add the symbol to the expression

            if (!displayMatcher.find() && displayExpression.length() != 0) {
                displayExpression = symbol;
                expression += " " + symbol + " ";
            }
            // Branch for numbers
        } else if (symbol.matches("\\d")) {
            // If there is a symbol on the screen reset the display
            if (displayMatcher.find()) {
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
public String executeOperation() {
    String[] tokens = expression.split("\\s");
    Stack<Double> numbers = new Stack<>();
    Stack<Character> operators = new Stack<>();
    for (String token : tokens) {
        if (token.matches("\\d+\\.?\\d*")) {
            numbers.push(Double.parseDouble(token));
        } else if (isOperator(token.charAt(0))) {
            // If the operator has precedence over the current operator, push onto the numbers stack the operation result and pop the two numbers and the operator
            while (!operators.empty() && hasPrecedence(token.charAt(0), operators.peek())) {
                numbers.push(applyOperation(numbers.pop(), numbers.pop(), operators.pop()));
            }
            operators.push(token.charAt(0));
        }
    }
    while (!operators.empty()) {
        // Perform any last operations
        numbers.push(applyOperation(numbers.pop(), numbers.pop(), operators.pop()));
    }
    return String.valueOf(numbers.pop());
}

    private  boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '√';
    }

    private boolean hasPrecedence(char op1, char op2) {
        if ((op1 == '^' || op1 == '√') && (op2 == '*' || op2 == '/')) {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }

    private  double applyOperation(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                if (a == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return b / a;
            case '^':
                return Math.pow(b, a);
            case '√':
                return Math.sqrt(b);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}