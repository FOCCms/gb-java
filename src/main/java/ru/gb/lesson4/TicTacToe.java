package ru.gb.lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static int SIZE = 3;
    public static int CELLS_TO_WIN = 2;
    public static char[][] field;

    public static char EMPTY_CELL = ' ';
    public static char CELL_X = 'X';
    public static char CELL_0 = '0';

    public static String AI_NAME = "Машина";
    public static String DRAW_NAME = "Дружба";
    public static String USER_NAME = "Игрок";

    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        setupGame();
        setupField();
        System.out.println(getActualField());

        String winnerName = "";

        while (true) {
            // user turn
            makeUsersTurn();
            System.out.println(getActualField());

            winnerName = getWinnerName();
            if (!winnerName.isEmpty()) {
                break;
            }

            // AI turn
            makeAITurn();
            System.out.println(getActualField());

            winnerName = getWinnerName();
            if (!winnerName.isEmpty()) {
                break;
            }
        }

        System.out.println("Ура, игра окончена! И победитель это - " + winnerName + "!");

    }

    //////////////////  <SETUP>  //////////////////////////

    private static void setupGame() {
        System.out.println("Привет! Прежде чем мы начнём игру ответь на несколько вопросов.");
        System.out.print("Как тебя зовут: ");
        USER_NAME = scanner.next();
        System.out.print("Какой размер поля: ");
        SIZE = scanner.nextInt();
        System.out.print("Сколько клеток подряд нужно занять для победы: ");
        CELLS_TO_WIN = scanner.nextInt();
        System.out.println("Отлично! Мы готовы к игре!");
    }

    public static void setupField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    //////////////////  </SETUP>  //////////////////////////

    //////////////////  <SERVICE>  //////////////////////////

    public static String getWinnerName() {
        if (checkPlayersWin(CELL_X)) {
            return USER_NAME;
        }
        if (checkPlayersWin(CELL_0)) {
            return AI_NAME;
        }
        if (isMapFull()) {
            return DRAW_NAME;
        }
        return "";
    }

    public static String getActualField() {
        String actualField = "";
        for (int i = 0; i < SIZE; i++) {
            actualField += "----".repeat(SIZE) + "-\n";
            for (int j = 0; j < SIZE; j++) {
                actualField += "| " + field[i][j] + " ";
            }
            actualField += "|\n";
        }
        actualField += "----".repeat(SIZE) + "-\n";
        return actualField;
    }

    public static boolean isCellValid(int x, int y) {
        boolean isCoordinateOutOfField = x < 0 || x >= SIZE || y < 0 || y >= SIZE;
        if (isCoordinateOutOfField) {
            return false;
        }
        boolean isCellEmpty = field[x][y] == EMPTY_CELL;
        return isCellEmpty;
    }

    public static void makeUsersTurn() {
        int x;
        int y;
        do {
            System.out.println("Введите координаты в формате X Y");
            System.out.println("Примечание X и Y ∈ [1;" + SIZE + "]");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));

        field[x][y] = CELL_X;
    }

    //////////////////  </SERVICE>  //////////////////////////


    ///////////////////  <WIN CHECKS>  /////////////////////////

    /**
     * Для проверки необходимо пройти по массиву, размером [SIZE x SIZE], избирая на каждом шаге подмассив размером [CELLS_TO_WIN х CELLS_TO_WIN]
     * и делая в нём проверку наличия ряда по вертикали, горизонтали или диагонали из исмволов cellChar(CELL_X или CELL_0) длинной CELLS_TO_WIN.
     */
    public static boolean checkPlayersWin(char cellChar) {
        for (int i = 0; i + CELLS_TO_WIN <= SIZE; i++) {
            for (int j = 0; j + CELLS_TO_WIN <= SIZE; j++) {
                if (checkSubArray(i, j, cellChar)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * Проходим по подмассиву до тех пор пока один из счётчиков (mainDiagonal, sideDiagonal, verticalCount, horizontalCount) не станет равен CELLS_TO_WIN
     * или пока не дойдём до конца подмассива.
     *
     * k и l координаты подмассива
     * i и j координаты смещения относительно основного массива
     *
     */
    private static boolean checkSubArray(int i, int j, char cellChar) {
        int mainDiagonal = 0;
        int sideDiagonal = 0;

        for (int k = 0; k < CELLS_TO_WIN; k++) {

            // проверяем, есть ли линия по диагоналям
            if (field[k + i][k + j] == cellChar) {
                mainDiagonal += 1;
            }
            if (field[k + i][CELLS_TO_WIN - 1 - k + j] == cellChar) {
                sideDiagonal += 1;
            }

            // проверяем совпадения по вертикали и горизонтали, если их нет
            // обнуляем счётчики и переходим на следующую строку/столбец
            int verticalCount = 0;
            int horizontalCount = 0;
            for (int l = 0; l < CELLS_TO_WIN; l++) {

                if (field[l + j][k + i] == cellChar) {
                    verticalCount += 1;
                }
                if (field[k + i][l + j] == cellChar) {
                    horizontalCount += 1;
                }
            }
            // если нашли совпадение по вертикали или горизонтали -- возвращаем true
            if (verticalCount == CELLS_TO_WIN || horizontalCount == CELLS_TO_WIN) {
                return true;
            }
        }

        // если нашли совпадение по одной из диагоналей -- возвращаем true
        if (mainDiagonal == CELLS_TO_WIN || sideDiagonal == CELLS_TO_WIN) {
            return true;
        }

        // если победителя нет -- возвращаем false
        return false;
    }

    ///////////////////  </WIN CHECKS>  /////////////////////////

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    ///////////////////  <AI>  /////////////////////////

    /**
     * ИИ играет по следующему принципу:
     * 1) методом перебора ищет поле, в которое можно поставить "0" и выиграть. Если нашёл -- ставит, если нет -- переход к п.2
     * 2) методом перебора ищет поле, в которое можно поставить "X" и выиграть как игрок. Если нашёл -- ставит в него "0", блокируя победу игрока, если нет -- переход к п.3
     * 3) генерирует координаты случайным образом до тех пор, пока не получится найти пустое поде. Когда находит -- ставит в него "0".
     */
    private static void makeAITurn() {
        // проверяем, удалось ли найти выигрышную ячейку
        boolean isTurnDone = makeAIPredictTurn();

        // если нет -- случайный ход
        if (!isTurnDone) {
            makeAIRandomTurn();
        }
    }

    private static boolean makeAIPredictTurn() {
        if (makePredict(CELL_0)) return true; // пытаемся победить, как ИИ
        if (makePredict(CELL_X)) return true; // пытаемся заблокировать игрока
        return false;
    }

    private static boolean makePredict(char predictChar) {
        // идём по всему полю
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == EMPTY_CELL) {
                    // когда находим пустую ячейку, подставляем X или 0 и рассчитываем исход
                    field[i][j] = predictChar;
                    if (checkPlayersWin(predictChar)) {
                        // если исход успешен, занимаем данную ячейку и выходим из метода
                        field[i][j] = CELL_0;
                        return true;
                    } else {
                        // иначе возвращаем пустой символ назад
                        field[i][j] = EMPTY_CELL;
                    }
                }
            }
        }
        return false;
    }

    private static void makeAIRandomTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(0, SIZE);
            y = random.nextInt(0, SIZE);
        } while (!isCellValid(x, y));

        field[x][y] = CELL_0;
    }

    ///////////////////  </AI>  /////////////////////////
}
