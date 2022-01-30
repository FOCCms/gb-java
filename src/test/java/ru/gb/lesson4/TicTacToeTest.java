package ru.gb.lesson4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TicTacToeTest {

    @Test
    public void shouldPrintEmptyField() {
        // arrange
        TicTacToe.setupField();

        // act
        String actualField = TicTacToe.getActualField();

        // assert
        String expectedField =
                "-------------\n" +
                        "|   |   |   |\n" +
                        "-------------\n" +
                        "|   |   |   |\n" +
                        "-------------\n" +
                        "|   |   |   |\n" +
                        "-------------\n";

        assertEquals(expectedField, actualField);

    }

    @Test
    public void shouldCheckIsCellValid() {
        // arrange
        TicTacToe.field = new char[1][1];

        // act
        TicTacToe.field[0][0] = TicTacToe.EMPTY_CELL;

        // assert
        assertFalse(TicTacToe.isCellValid(-1, -1)); // x and y wrong
        assertFalse(TicTacToe.isCellValid(0, -1)); // y wrong
        assertFalse(TicTacToe.isCellValid(-1, 0)); // x wrong
        assertTrue(TicTacToe.isCellValid(0, 0)); // valid
        assertFalse(TicTacToe.isCellValid(3, 0)); // x wrong
        assertFalse(TicTacToe.isCellValid(0, 3)); // y wrong
        assertFalse(TicTacToe.isCellValid(3, 3)); // x and y wrong
    }

    @Test
    public void shouldPrintFieldWithXAnd0() {
        // arrange
        TicTacToe.SIZE = 3;
        TicTacToe.setupField();
        TicTacToe.field[0][0] = TicTacToe.CELL_X;
        TicTacToe.field[1][1] = TicTacToe.CELL_0;
        // act
        String actualField = TicTacToe.getActualField();

        // assert
        String expectedField =
                "-------------\n" +
                        "| X |   |   |\n" +
                        "-------------\n" +
                        "|   | 0 |   |\n" +
                        "-------------\n" +
                        "|   |   |   |\n" +
                        "-------------\n";

        assertEquals(expectedField, actualField);

    }

    @Test
    public void shouldCheckIsMapFullWhenMapIsFull() {
        // arrange
        TicTacToe.setupField();
        for (int i = 0; i < TicTacToe.SIZE; i++) {
            for (int j = 0; j < TicTacToe.SIZE; j++) {
                TicTacToe.field[i][j] = TicTacToe.CELL_X;
            }
        }

        // act
        boolean isFull = TicTacToe.isMapFull();

        // assert
        assertTrue(isFull);
    }

    @Test
    public void shouldCheckIsMapFullWhenMapIsNotFull() {
        // arrange
        TicTacToe.setupField();
        for (int i = 1; i < TicTacToe.SIZE; i++) {
            for (int j = 1; j < TicTacToe.SIZE; j++) {
                TicTacToe.field[i][j] = TicTacToe.CELL_X;
            }
        }

        // act
        boolean isFull = TicTacToe.isMapFull();

        // assert
        assertFalse(isFull);
    }

    @Test
    public void shouldCheckPlayerRowWin() {
        // arrange
        TicTacToe.setupField();
        TicTacToe.field[0][0] = TicTacToe.CELL_X;
        TicTacToe.field[0][1] = TicTacToe.CELL_X;
        TicTacToe.field[0][2] = TicTacToe.CELL_X;

        // act
        boolean isWin = TicTacToe.checkPlayersWin(TicTacToe.CELL_X);

        // assert
        assertTrue(isWin);
    }

    @Test
    public void shouldCheckPlayerColumnWin() {
        // arrange
        TicTacToe.setupField();
        TicTacToe.field[0][0] = TicTacToe.CELL_X;
        TicTacToe.field[1][0] = TicTacToe.CELL_X;
        TicTacToe.field[2][0] = TicTacToe.CELL_X;

        // act
        boolean isWin = TicTacToe.checkPlayersWin(TicTacToe.CELL_X);

        // assert
        assertTrue(isWin);
    }

    @Test
    public void shouldCheckPlayerMainDiagonalWin() {
        // arrange
        TicTacToe.setupField();
        TicTacToe.field[0][0] = TicTacToe.CELL_X;
        TicTacToe.field[1][1] = TicTacToe.CELL_X;
        TicTacToe.field[2][2] = TicTacToe.CELL_X;

        // act
        boolean isWin = TicTacToe.checkPlayersWin(TicTacToe.CELL_X);

        // assert
        assertTrue(isWin);
    }

    @Test
    public void shouldCheckPlayerMainDiagonalWin5x5() {
        // arrange
        TicTacToe.SIZE = 5;
        TicTacToe.CELLS_TO_WIN = 3;
        TicTacToe.setupField();
        TicTacToe.field[2][2] = TicTacToe.CELL_X;
        TicTacToe.field[3][3] = TicTacToe.CELL_X;
        TicTacToe.field[4][4] = TicTacToe.CELL_X;

        // act
        boolean isWin = TicTacToe.checkPlayersWin(TicTacToe.CELL_X);

        // assert
        assertTrue(isWin);
    }

    @Test
    public void shouldCheckPlayerSideDiagonalWin5x5() {
        // arrange
        TicTacToe.SIZE = 5;
        TicTacToe.CELLS_TO_WIN = 3;
        TicTacToe.setupField();
        TicTacToe.field[2][3] = TicTacToe.CELL_X;
        TicTacToe.field[3][2] = TicTacToe.CELL_X;
        TicTacToe.field[4][1] = TicTacToe.CELL_X;

        // act
        boolean isWin = TicTacToe.checkPlayersWin(TicTacToe.CELL_X);

        // assert
        assertTrue(isWin);
    }

    @Test
    public void shouldCheckPlayerSideDiagonalWin() {
        // arrange
        TicTacToe.setupField();
        TicTacToe.field[0][2] = TicTacToe.CELL_X;
        TicTacToe.field[1][1] = TicTacToe.CELL_X;
        TicTacToe.field[2][0] = TicTacToe.CELL_X;

        // act
        boolean isWin = TicTacToe.checkPlayersWin(TicTacToe.CELL_X);

        // assert
        assertTrue(isWin);
    }

}
