package com.gabri3445.overloading;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<Person> personList = new ArrayList<>();
    private static final List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("""
                    [0] Insert a person
                    [1] View person
                    [2] Insert a student
                    [3] View student
                    [4] Exit""");
            choice = scanner.nextInt();
            switch (choice) {
                case 0 -> insertPerson();
                case 1 -> {
                    for (Person person :
                            personList) {
                        hello(person);
                    }
                }
                case 2 -> insertStudent();
                case 3 -> {
                    for (Student student :
                            studentList) {
                        hello(student);
                    }
                }
            }
        } while (choice != 4);
    }

    private static void insertPerson() {
        System.out.println("Enter name");
        String name = scanner.next();
        System.out.println("Enter surname");
        String surname = scanner.next();
        System.out.println("Enter age");
        int age = scanner.nextInt();
        personList.add(new Person(name, surname,age));
    }

    private static void insertStudent() {
        System.out.println("Enter name");
        String name = scanner.next();
        System.out.println("Enter surname");
        String surname = scanner.next();
        System.out.println("Enter age");
        int age = scanner.nextInt();
        System.out.println("Enter the course");
        String course = scanner.next();;
        System.out.println("Enter the school");
        String school = scanner.next();
        System.out.println("Enter the classroom");
        String classRoom = scanner.next();
        studentList.add(new Student(name, surname, age, course, school, classRoom));
    }

    private static void hello(Person person) {
        System.out.println("Name: " + person.getName());
        System.out.println("Surname: " + person.getSurname());
        System.out.println("Age: " + person.getAge());
    }

    private static  void hello(Student student) {
        System.out.println("Name: " + student.getName());
        System.out.println("Surname: " + student.getSurname());
        System.out.println("Age: " + student.getAge());
        System.out.println("Course: " + student.getCourse());
        System.out.println("School: " + student.getSchool());
        System.out.println("Classroom: " + student.getClassRoom());
    }
}
