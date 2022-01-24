package ru.gb.lesson3;

public class HomeWork3 {

    public static void main(String[] args) {

        // 1
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        invertBinaryArrayData(binaryArray);

        // 2
        int[] arrayToFill = new int[100];
        fillArray(arrayToFill);

        // 3
        int[] arrayToDoubleIfLessThanSix = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        doubleIfLessThanSix(arrayToDoubleIfLessThanSix);

        // 4
        int matrixSize = 5;
        int[][] matrix = new int[matrixSize][matrixSize];
        fillMatrixDiagonals(matrix);

        // 5
        int len = 5;
        int initialValue = 11;
        int[] initiatedArray = initArray(len, initialValue);

        // 6
        int[] arrayToFindMinAndMax = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        System.out.println("Min value is:" + findMin(arrayToFindMinAndMax));
        System.out.println("Max value is:" + findMax(arrayToFindMinAndMax));

    }

    // 1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    // С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void invertBinaryArrayData(int[] binaryArray) {
        for (int i = 0; i < binaryArray.length; i++) {
            binaryArray[i] = binaryArray[i] == 1 ? 0 : 1;
        }
    }

    // 2. Задать пустой целочисленный массив длиной 100. С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100;
    public static void fillArray(int[] arrayToFill) {
        for (int i = 0; i < arrayToFill.length; i++) {
            arrayToFill[i] = i + 1;
        }
    }

    // 3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2 в массиве;
    public static void doubleIfLessThanSix(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] < 6 ? array[i] * 2 : array[i];
        }
    }

    // 4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    // и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей, если обе сложно).
    // Определить элементы одной из диагоналей можно по следующему принципу: индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n];
    public static void fillMatrixDiagonals(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i + j == size - 1) {
                    matrix[i][j] = 1;
                }
            }
        }
    }

    // 5. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue;
    public static int[] initArray(int len, int initialValue) {
        int[] array = new int[len];
        for (int i = 0; i < array.length; i++) {
            array[i] = initialValue;
        }
        return array;
    }

    // 6. * Задать одномерный массив и найти в нем минимальный и максимальный значения элементов
    public static int findMin(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        return min;
    }

    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }

        return max;
    }
}
