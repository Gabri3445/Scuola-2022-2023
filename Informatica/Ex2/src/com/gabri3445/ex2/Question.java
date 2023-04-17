package com.gabri3445.ex2;

public class Question {
    private String question;
    private String answer;
    private int points;

    public int ask(String answer) {
        if (this.answer.equals(answer)) {
            return points;
        }
        return 0;
    }
}
