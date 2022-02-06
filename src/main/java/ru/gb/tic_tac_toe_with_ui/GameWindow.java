package ru.gb.tic_tac_toe_with_ui;

import javax.swing.*;
import java.awt.*;

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

    public GameWindow() {
        initGameWindow();

        initGameField();
        add(gameField, BorderLayout.CENTER);

        initButtons();
        initGameControlPanel();

        initGameSettingsPanel();
        add(panelSettings, BorderLayout.EAST);
    }

    private void initGameControlPanel() {
        panelControls = new JPanel();
        panelControls.setLayout(new GridLayout(8, 1));

        panelControls.add(buttonStartGame);
        panelControls.add(buttonEndGame);
    }

    private void initButtons() {
        buttonStartGame = new GameButton("Start game");
        buttonEndGame = new GameButton("End game");
    }

    private void initGameSettingsPanel() {
        panelSettings = new JPanel();
        panelSettings.setLayout(new GridLayout(2,1));

        panelSettings.add(panelControls, BorderLayout.NORTH);

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
        gameField = new GameField();
    }

}
