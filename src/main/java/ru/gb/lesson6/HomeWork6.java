package ru.gb.lesson6;

public class HomeWork6 {

    public static void main(String[] args) {
        Cat cat = new Cat("Гав", "orange", "сиамский");
        Dog dog = new Dog("Шарик", "brown", "дворняга", true);

        cat.run(250); // Гав пробежал 200 м.
        cat.swim(100); // Гав не умеет плавать!

        dog.run(150); // Шарик пробежал 150 м.
        dog.swim(150); // Шарик проплыл 10 м.

        Dog dog2 = new Dog("Балто", "gray", "сибирский хаски", true);

        System.out.println("Создано котов: " + Cat.count); // Создано котов: 1
        System.out.println("Создано собак: " + Dog.count); // Создано собак: 2
        System.out.println("Создано животных: " + Animal.count); // Создано животных: 3

    }
}
