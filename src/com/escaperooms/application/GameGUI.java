package com.escaperooms.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameGUI extends JPanel{
    private EscapeRoomGame game;
    private Traveler traveler;
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    List<Item> itemList;
    public GameGUI(){}

    public GameGUI(Traveler traveler){
        this.game = traveler.getGame();
        this.traveler = traveler;

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                traveler.keyReleased(e);

            }

            @Override
            public void keyPressed(KeyEvent e) {
                traveler.keyPressed(e);
            }
        });
        setFocusable(true);


    }

    public void move(){
        traveler.move();
    }

    public void collision() {

     //  this.game.getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(e -> itemList.add(e.values())) ;
//        for(Picture p: pictures.values().toArray(new Picture[0])) {
//            if (p.collision()) {
//                JOptionPane.showMessageDialog(this, "you have look at a picture", "picture", JOptionPane.OK_OPTION,p.icon);
//                player.x = player.x + 10;
//                player.y = player.y + 10;
//                player.ya = 0;
//                player.xa = 0;
//            }
//        }

    }

    public void deployItems(Graphics2D g2) {
        this.game.getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(e -> e.values().forEach(i -> i.paint(g2)));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        deployItems(g2d);
        traveler.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf("This is a random message we can display the whole time"), 10, 30);
    }

    public void run(){
        traveler.jump(traveler.getAvailableRooms().get(0), true);
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }


}