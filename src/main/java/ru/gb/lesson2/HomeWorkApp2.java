package ru.gb.lesson2;

public class HomeWorkApp2 {

    /*1. Написать метод, принимающий на вход два целых числа и проверяющий,
    что их сумма лежит в пределах от 10 до 20 (включительно), если да – вернуть true,
    в противном случае – false.*/
    public static boolean isSumBetween10and20(int a, int b) {
        int sum = a + b;
        return 10 <= sum && sum <= 20;
    }

    /*2. Написать метод, которому в качестве параметра передается целое число,
    метод должен напечатать в консоль, положительное ли число передали или отрицательное.
    Замечание: ноль считаем положительным числом.*/
    public static void checkTheInteger(int a) {
        if (!isNegative(a)) {
            System.out.println("Число " + a + " пололжительное");
        } else {
            System.out.println("Число " + a + " отрицательное");
        }
    }

    /*3. Написать метод, которому в качестве параметра передается целое число.
    Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.*/
    public static boolean isNegative(int a) {
        return a < 0;
    }

    /*4. Написать метод, которому в качестве аргументов передается строка и число,
    метод должен отпечатать в консоль указанную строку, указанное количество раз;*/
    public static void printTextNTimes(String text, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(text);
        }
    }

    /*5.* Написать метод, который определяет, является ли год високосным,
    и возвращает boolean (високосный - true, не високосный - false).
    Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.*/
    public static boolean isLeapYear(int year) {
        boolean isEveryFourth = year % 4 == 0;
        boolean isNotEveryHundredth = year % 100 != 0;

        boolean isEvery400th = year % 400 == 0;
        return (isEveryFourth && isNotEveryHundredth) || isEvery400th;
    }

}
