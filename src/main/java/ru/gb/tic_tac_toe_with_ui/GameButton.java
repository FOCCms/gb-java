package ru.gb.tic_tac_toe_with_ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class GameButton extends JButton {

    public GameButton(String text) {
        super(text);

        this.setFocusPainted(false);
        this.setContentAreaFilled(false);

        this.setBorder(new RoundedBorder(10));
    }

    // добавляет скругление кнопкам
    // https://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java
    private static class RoundedBorder implements Border {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}


