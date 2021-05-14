package com.escaperooms.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameGUI extends JPanel{
    private EscapeRoomGame game;
    private Traveler traveler;

    public GameGUI(){}

    public GameGUI(Traveler traveler){
        this.game = traveler.getGame();
        this.traveler = traveler;
    }

    public void deployItems(Graphics2D g2) {
        this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(e -> e.values().forEach(i -> i.paint(g2)));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        deployItems(g2d);


        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf("This is a random message we can display the whole time"), 10, 30);
    }

    public void run(){
        traveler.jump(traveler.getAvailableGames().get(0), true);
    }
}