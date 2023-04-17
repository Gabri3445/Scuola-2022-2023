package com.gabri3445.ex2;

import com.gabri3445.menu.Arguments;
import com.gabri3445.menu.Menu;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        var questions = new ArrayList<Question>();
        var menu = new Menu();
        Map<Integer, Arguments> arguments = Map.of(
                0, new Arguments(() -> {
                    System.out.println("Enter the question");
                    var question = scanner.nextLine();
                    System.out.println("Enter the answer");
                    var answer = scanner.nextLine();
                    System.out.println("Enter the points");
                    var points = scanner.nextInt();
                    questions.add(new Question(question, answer, points));
                }, "Add a question"),
                1, new Arguments(() -> {
                    printQuestionsWithIndex(questions);
                    System.out.println("Enter the index of the question");
                    var index = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the answer");
                    var answer = scanner.nextLine();
                    var responsePoints = questions.get(index).ask(answer);
                    if (responsePoints > 0) {
                        System.out.println("Correct with " + responsePoints + " points");
                    } else {
                        System.out.println("Incorrect");
                    }
                }, "Answer a question")
        );
        menu.executeMenu(arguments);
    }

    private static void printQuestionsWithIndex(@NotNull List<Question> questions) {
        questions.forEach(question -> System.out.println(questions.indexOf(question) + " " + question.getQuestion()));
    }
}
