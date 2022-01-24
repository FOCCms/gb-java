package ru.gb.lesson3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomeWork3Test {

    @Test
    public void shouldInvertArray() {
        // arrange
        int[] binaryArray = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        // act
        HomeWork3.invertBinaryArrayData(binaryArray);

        // assert
        int[] expectedArray = {0, 0, 1, 1, 0, 1, 0, 0, 1, 1};

        assertEquals(expectedArray.length, binaryArray.length);
        for (int i = 0; i < expectedArray.length; i++) {
            assertEquals(expectedArray[i], binaryArray[i]);
        }

    }

    @Test
    public void shouldFillArray() {
        // arrange
        int[] arrayToFill = new int[100];

        // act
        HomeWork3.fillArray(arrayToFill);

        // assert
        for (int i = 0; i < arrayToFill.length; i++) {
            assertEquals(i + 1, arrayToFill[i]);
        }

    }

    @Test
    public void shouldDoubleIfLessThanSix() {
        // arrange
        int[] arrayToDoubleIfLessThanSix = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        // act
        HomeWork3.doubleIfLessThanSix(arrayToDoubleIfLessThanSix);

        // assert
        int[] expectedArray = {2, 10, 6, 4, 11, 8, 10, 4, 8, 8, 9, 2};
        assertEquals(expectedArray.length, arrayToDoubleIfLessThanSix.length);

        for (int i = 0; i < arrayToDoubleIfLessThanSix.length; i++) {
            assertEquals(expectedArray[i], arrayToDoubleIfLessThanSix[i]);
        }

    }

    @Test
    public void shouldFillMatrixDiagonals() {
        // arrange
        int matrixSize = 5;
        int[][] matrix = new int[matrixSize][matrixSize];

        // act
        HomeWork3.fillMatrixDiagonals(matrix);

        // assert
        int[][] expectedMatrix = {
                {1, 0, 0, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 0, 0, 1},
        };

        assertEquals(expectedMatrix.length, matrix.length);
        assertEquals(expectedMatrix[0].length, matrix[0].length);

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                assertEquals(expectedMatrix[i][j], matrix[i][j]);
            }
        }
    }

    @Test
    public void shouldInitArray() {
        // arrange
        int len = 5;
        int initialValue = 11;

        // act
        int[] actualArray = HomeWork3.initArray(len, initialValue);

        // assert
        int[] expectedArray = {11, 11, 11, 11, 11};

        assertEquals(expectedArray.length, actualArray.length);

        for (int i = 0; i < len; i++) {
                assertEquals(expectedArray[i], actualArray[i]);
        }
    }

    @Test
    public void shouldFindMinAndMax() {
        // arrange
        int[] arrayToFindMinAndMax = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        // act
//        HomeWork3.findMinAndMax(arrayToFindMinAndMax);

    }

}
