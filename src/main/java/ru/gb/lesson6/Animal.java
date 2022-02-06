package ru.gb.lesson6;

public abstract class Animal {

    static int count = 0;

    protected String name;
    protected String color;
    protected String breed;
    protected int maxRunDistance;
    protected int maxSwimDistance;

    public Animal(String name, String color, String breed, int maxRunDistance, int maxSwimDistance) {
        this.name = name;
        this.color = color;
        this.breed = breed;
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;

        count += 1;

    }

    public void run(int distance) {
        int currDistance = Math.min(distance, maxRunDistance);
        System.out.println(name + " пробежал " + currDistance + " м.");
    }

    public void swim(int distance) {
        int currDistance = Math.min(distance, maxSwimDistance);
        System.out.println(name + " проплыл " + currDistance + " м.");
    }

}
