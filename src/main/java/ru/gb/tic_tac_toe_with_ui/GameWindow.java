package ru.gb.tic_tac_toe_with_ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private int winWidth;
    private int winHeight;
    private int winPosX;
    private int winPosY;

    private GameField gameField;

    private JPanel panelSettings;
    private JPanel panelControls;

    private JButton buttonStartGame;
    private JButton buttonEndGame;
    private JButton buttonClearLog;

    private int minMapSize = 3;
    private int minCellsToWinLength = 3;
    private int maxMapSize = 10;

    private final String MAP_SIZE_LABEL_PREFIX = "Map size: ";
    private JLabel mapSizeLabel;
    private JSlider mapSizeSlider;

    private final String CELLS_TO_WIN_LABEL_PREFIX = "Cells to win: ";
    private JLabel cellsToWinLabel;
    private JSlider cellsToWinSlider;

    private JLabel usernameLabel = new JLabel("Username: ");
    private final String DEFAULT_USERNAME = "User";
    private JTextField usernameField = new JTextField(DEFAULT_USERNAME);

    private JTextArea gameLog = new JTextArea();
    private JScrollPane scrollPanel = new JScrollPane(gameLog);

    public GameWindow() {
        initGameWindow();

        initGameField();
        add(gameField, BorderLayout.CENTER);

        initGameSettingsPanel();
        add(panelSettings, BorderLayout.EAST);
    }

    private void initGameControlPanel() {
        panelControls = new JPanel();
        panelControls.setLayout(new GridLayout(10, 1));

        panelControls.add(usernameLabel);
        panelControls.add(usernameField);
        initSliders();

        panelControls.add(mapSizeLabel);
        panelControls.add(mapSizeSlider);

        panelControls.add(cellsToWinLabel);
        panelControls.add(cellsToWinSlider);

        initButtons();
        panelControls.add(buttonStartGame);
        panelControls.add(buttonClearLog);
        panelControls.add(buttonEndGame);
    }

    private void initSliders() {
        cellsToWinLabel = new JLabel(CELLS_TO_WIN_LABEL_PREFIX + minCellsToWinLength);
        cellsToWinSlider = new JSlider(minCellsToWinLength, minMapSize, minCellsToWinLength);
        cellsToWinSlider.setMinorTickSpacing(1);
        cellsToWinSlider.setPaintTicks(true);
        cellsToWinSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                cellsToWinLabel.setText(CELLS_TO_WIN_LABEL_PREFIX + cellsToWinSlider.getValue());
            }
        });

        mapSizeLabel = new JLabel(MAP_SIZE_LABEL_PREFIX + minMapSize);
        mapSizeSlider = new JSlider(SwingConstants.HORIZONTAL, minMapSize, maxMapSize, minMapSize);
        mapSizeSlider.setMinorTickSpacing(1);
        mapSizeSlider.setPaintTicks(true);
        mapSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentValue = mapSizeSlider.getValue();
                mapSizeLabel.setText(MAP_SIZE_LABEL_PREFIX + currentValue);
                cellsToWinSlider.setMaximum(currentValue);
            }
        });
    }

    private void initButtons() {
        buttonStartGame = new GameButton("Start game");
        buttonStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLog.setText("");
                collectUserSettingsAndLaunch();

            }
        });

        buttonClearLog = new GameButton("Clear log");
        buttonClearLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLog.setText("");
            }
        });

        buttonEndGame = new GameButton("End game");
        buttonEndGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    private void initGameSettingsPanel() {
        panelSettings = new JPanel();
        panelSettings.setLayout(new GridLayout(2, 1));

        initGameControlPanel();
        initScrollPanel();
        panelSettings.add(panelControls);
        panelSettings.add(scrollPanel);
    }

    private void initScrollPanel() {
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
    }

    void addLog(String str) {
        gameLog.append(str + "\n");
    }

    private void initGameWindow() {
        winWidth = 1133;
        winHeight = 744;
        winPosX = 300;
        winPosY = 500;

        setBounds(winPosX, winPosY, winWidth, winHeight);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("TicTacToe");
        setResizable(false);
        setVisible(true);
    }

    private void initGameField() {
        gameField = new GameField(this);

    }

    private void collectUserSettingsAndLaunch() {
        int mapSize = mapSizeSlider.getValue();
        int winLen = cellsToWinSlider.getValue();
        addLog("Start Game");
        addLog("Choose map size " + mapSize + "x" + mapSize);
        addLog("Choose win length " + winLen);
        gameField.setupGame(usernameField.getText(), mapSize, winLen);
    }
}
