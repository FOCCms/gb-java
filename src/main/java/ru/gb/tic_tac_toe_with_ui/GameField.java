package ru.gb.tic_tac_toe_with_ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GameField extends JPanel {

    private int SIZE = 3;
    private int CELLS_TO_WIN = 2;
    private char[][] field;

    private char EMPTY_CELL = ' ';
    private char CELL_X = 'X';
    private char CELL_0 = '0';

    private String AI_NAME = "Машина";
    private String DRAW_NAME = "Дружба";
    private String USER_NAME = "Игрок";

    private Random random = new Random();

    private int cellWidth;
    private int cellHeight;

    private boolean isExistGameField;
    private boolean isGameOver;

    private String winnerName = "";

    private GameWindow gameWindow;

    public GameField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(new Color(255, 255, 255));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouse) {
                super.mouseReleased(mouse);
                updater(mouse);
            }
        });

        isExistGameField = false;
        isGameOver = true;
    }

    //////////////////  <SETUP>  //////////////////////////

    public void setupGame(String userName, int size, int cellsToWin) {
        USER_NAME = userName;
        SIZE = size;
        CELLS_TO_WIN = cellsToWin;

        isExistGameField = true;
        isGameOver = false;

        setupField();
        repaint();
    }

    private void setupField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    //////////////////  </SETUP>  //////////////////////////

    //////////////////  <SERVICE>  //////////////////////////

    private String getWinnerName() {
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

    private boolean isCellValid(int x, int y) {
        boolean isCoordinateOutOfField = x < 0 || x >= SIZE || y < 0 || y >= SIZE;
        if (isCoordinateOutOfField) {
            return false;
        }
        boolean isCellEmpty = field[x][y] == EMPTY_CELL;
        return isCellEmpty;
    }

    //////////////////  </SERVICE>  //////////////////////////

    ///////////////////  <WIN CHECKS>  /////////////////////////

    /**
     * Для проверки необходимо пройти по массиву, размером [SIZE x SIZE], избирая на каждом шаге подмассив размером [CELLS_TO_WIN х CELLS_TO_WIN]
     * и делая в нём проверку наличия ряда по вертикали, горизонтали или диагонали из исмволов cellChar(CELL_X или CELL_0) длинной CELLS_TO_WIN.
     */
    private boolean checkPlayersWin(char cellChar) {
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
     * Проходим по подмассиву до тех пор пока один из счётчиков (mainDiagonal, sideDiagonal, verticalCount, horizontalCount) не станет равен CELLS_TO_WIN
     * или пока не дойдём до конца подмассива.
     * <p>
     * k и l координаты подмассива
     * i и j координаты смещения относительно основного массива
     */
    private boolean checkSubArray(int i, int j, char cellChar) {
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

    private boolean isMapFull() {
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
    private void makeAITurn() {
        // проверяем, удалось ли найти выигрышную ячейку
        boolean isTurnDone = makeAIPredictTurn();

        // если нет -- случайный ход
        if (!isTurnDone) {
            makeAIRandomTurn();
        }
    }

    private boolean makeAIPredictTurn() {
        if (makePredict(CELL_0)) return true; // пытаемся победить, как ИИ
        if (makePredict(CELL_X)) return true; // пытаемся заблокировать игрока
        return false;
    }

    private boolean makePredict(char predictChar) {
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

    private void makeAIRandomTurn() {
        int x;
        int y;
        do {
            x = random.nextInt(0, SIZE);
            y = random.nextInt(0, SIZE);
        } while (!isCellValid(x, y));

        field[x][y] = CELL_0;
    }

    ///////////////////  </AI>  /////////////////////////

    //////////////////   <GUI> //////////////////////////

    private void updater(MouseEvent mouse) {

        if (isGameOver) {
            return;
        }

        if (!isExistGameField) {
            return;
        }

        int clickX = mouse.getX() / cellWidth;
        int clickY = mouse.getY() / cellHeight;

        if (!isCellValid(clickY, clickX)) {
            return;
        }

        field[clickY][clickX] = CELL_X;
        gameWindow.addLog("Human turn in [" + (clickX + 1) + ":" + (clickY + 1) + "]");

        winnerName = getWinnerName();
        if (!winnerName.isEmpty()) {
            setGameOver();
            return;
        }

        makeAITurn();
        repaint();

        winnerName = getWinnerName();
        if (!winnerName.isEmpty()) {
            setGameOver();
            return;
        }
    }

    private void setGameOver() {
        repaint();
        isGameOver = true;
        gameWindow.addLog("Ура, игра окончена! И победитель это - " + winnerName + "!");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isExistGameField) {
            return;
        }

        paintGameMap(g);

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {

                if (field[y][x] == EMPTY_CELL) {
                    continue;
                }

                if (field[y][x] == CELL_X) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x * cellWidth + 10, y * cellHeight + 10, cellWidth - 20, cellHeight - 20);
                }

                if (field[y][x] == CELL_0) {
                    g.setColor(Color.RED);
                    g.fillRect(x * cellWidth + 10, y * cellHeight + 10, cellWidth - 20, cellHeight - 20);
                }
            }
        }

        if (isGameOver) {
            return;
        }
    }

    private void paintGameMap(Graphics g) {
        int width = getWidth();
        int height = getHeight();

        cellWidth = width / SIZE;
        cellHeight = height / SIZE;

        g.setColor(new Color(0, 0, 0));

        for (int i = 0; i <= SIZE; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, width, y);
        }

        for (int i = 0; i <= SIZE; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, height);
        }
    }

    //////////////////   </GUI> /////////////////////////

}
