package com.escaperooms.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class MusicPlayerPanel extends JPanel{

    private final int BORDER_PADDING = 10;
    Frame frame;
    String name;

    /**
     * ActionListener allows the EndGamePanel to communicate with the Game class
     * Note: EndGamePanel is extending JPanel. The Border Padding equal spacing from all insets
     * The GridBagConstraints is responsible for holding the UI centered
     */
    MusicPlayerPanel(ActionListener listener,Frame frame) {
        setBorder(new EmptyBorder(BORDER_PADDING, BORDER_PADDING, BORDER_PADDING, BORDER_PADDING));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        this.frame= frame;
        JButton play = new JButton("Play");
        JButton restartMusic = new JButton("Restart CD");
        JButton stop = new JButton("Stop");
        JButton exitMusicPlayer = new JButton("Exit Music Player");

        add(new JLabel("<html><h1><strong><i>What would you like to do with the CD</i></strong></h1><hr></html>"), gbc);
        //add(new JLabel("Song Name"+ frame.getGameGUI().getItem().getName()),gbc);
        play.addActionListener(listener);
        restartMusic.addActionListener(listener);
        stop.addActionListener(listener);
        exitMusicPlayer.addActionListener(listener);



        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());

        buttons.add(play);
        buttons.add(stop);
        buttons.add(restartMusic);
        buttons.add(exitMusicPlayer);

        add(buttons, gbc);

    }
}