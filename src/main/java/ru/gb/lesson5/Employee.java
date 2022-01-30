package ru.gb.lesson5;

import java.util.Calendar;

public class Employee {

    private String lastName; // фамилия
    private String firstName; // имя
    private String sureName; // отчество
    private int yearOfBirth;

    private String email;
    private String phoneNumber;

    private String post;
    private int salary;

    public Employee(String firstName, String sureName, String lastName, int yearOfBirth, String email, String phoneNumber, String post, int salary) {
        this.firstName = firstName;
        this.sureName = sureName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.post = post;
        this.salary = salary;
    }

    public int getAge() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        return year - yearOfBirth;
    }

    private String getFIO() {
        return String.join(" ", lastName, firstName, sureName);
    }

    public String getInfo() {
        return "ФИО: " + getFIO() +
                ", должность: " + post +
                ", email: " + email +
                ", телефон: " + phoneNumber +
                ", зарплата: " + salary +
                ", возраст: " + getAge();
    }
}
