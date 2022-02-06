package ru.gb.lesson6;

public class Cat extends Animal {

    static int count = 0;

    public Cat(String name, String color, String breed) {
        super(name, color, breed, 200, 0);
        count += 1;
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать!");
    }
}
