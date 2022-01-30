package ru.gb.lesson6;

public class Dog extends Animal {

    static int count = 0;

    private boolean isSledDog; // ездовая?

    public Dog(String name, String color, String breed, boolean isSledDog) {
        super(name, color, breed, 500, 10);
        this.isSledDog = isSledDog;
        count += 1;
    }

}
