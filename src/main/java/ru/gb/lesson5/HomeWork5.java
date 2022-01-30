package ru.gb.lesson5;

public class HomeWork5 {

    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Андреей", "Андреевич", "Андреев", 1979, "aaa@mail.com", "1-111-111-11-11", "Кассир", 15000);
        employees[1] = new Employee("Борис", "Борисович", "Борисов", 1980, "bbb@mail.com", "2-222-222-22-22", "Бухгалтер", 25000);
        employees[2] = new Employee("Валерий", "Валерьевич", "Валерьев", 1981, "vvv@mail.com", "3-333-333-33-33", "Грузчик", 13000);
        employees[3] = new Employee("Ганнадий", "Ганнадьевич", "Ганнадьев", 1982, "ggg@mail.com", "4-444-444-44-44", "Директор", 35000);
        employees[4] = new Employee("Дмитрий", "Дмитриевич", "Дмитриев", 1984, "ddd@mail.com", "5-555-555-55-55", "Менеджер", 20000);

        int age = 40;

        getEmployeesInfoOlderThanAge(employees, age);

    }

    private static void getEmployeesInfoOlderThanAge(Employee[] employees, int age) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getAge() > age) {
                System.out.println(employees[i].getInfo());
            }
        }
    }


}
