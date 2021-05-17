package com.escaperooms.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * EndGamePanel is the final window that allows the user to restart or exit the application.
 */
public class EndGamePanel extends JPanel {


    private final int BORDER_PADDING = 10;

    /**
     * ActionListener allows the EndGamePanel to communicate with the Game class
     * Note: EndGamePanel is extending JPanel. The Border Padding equal spacing from all insets
     * The GridBagConstraints is responsible for holding the UI centered
     */
    EndGamePanel(ActionListener listener) {
        setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        JButton endGame = new JButton("Exit");
        JButton restart = new JButton("Restart");

        add(new JLabel("<html><h1><strong><i>Game Over</i></strong></h1><hr></html>"), gbc);

        endGame.addActionListener(listener);
        restart.addActionListener(listener);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());

        buttons.add(endGame);
        buttons.add(restart);

        add(buttons, gbc);

    }
}