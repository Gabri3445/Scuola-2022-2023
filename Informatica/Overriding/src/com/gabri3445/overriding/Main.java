package com.gabri3445.overriding;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choice;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("""
                    [0] Cat
                    [1] Dog
                    [2] Exit""");
            choice = scanner.nextInt();
            switch (choice) {
                case 0 -> {
                    Cat cat = new Cat();
                    cat.makeSound();
                }
                case 1 -> {
                    Dog dog = new Dog();
                    dog.makeSound();
                }
            }
        }while (choice != 2);
    }
}
