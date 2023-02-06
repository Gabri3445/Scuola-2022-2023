package com.gabri3445.overloading;

public class Student extends Person {
    public String getCourse() {
        return course;
    }

    public String getSchool() {
        return school;
    }

    public String getClassRoom() {
        return classRoom;
    }

    private final String course;
    private final String school;
    private final String classRoom;

    public Student(String name, String surname, int age, String course, String school, String classRoom) {
        super(name, surname, age);
        this.course = course;
        this.school = school;
        this.classRoom = classRoom;
    }
}
