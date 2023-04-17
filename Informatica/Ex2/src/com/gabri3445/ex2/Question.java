package com.gabri3445.ex2;

public class Question {
    private final String question;
    private final String answer;
    private final int points;

    public Question(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public String getQuestion() {
        return question;
    }

    public int ask(String answer) {

        if (this.answer.equals(answer)) {
            return points;
        }
        return 0;
    }
}
