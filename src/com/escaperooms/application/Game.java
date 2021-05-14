package com.escaperooms.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {
   EscapeRoomGame game;
    Traveler traveler;


    public Game() {
    }

    public  Game(Traveler traveler) {
        this.game = traveler.getGame();
        this.traveler = traveler;
    }





//        addKeyListener(new KeyListener() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//                player.keyReleased(e);
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//                player.keyPressed(e);
//            }
//        });
//        setFocusable(true);
        //Sound.BACK.loop();


//    private void move() {
//
//        player.move();
//    }

//    private void addPainting(){
//        pictures.put("NAme1",picture);
//        pictures.put("anme1",picture1);
//    }
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


        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf("This is a random message we can display the whole time"), 10, 30);
    }


//    public void gameOver() {
//        for(Picture p: pictures.values().toArray(new Picture[0])) {
//            if (p.collision()) {
//                JOptionPane.showMessageDialog(this, "you have look at a picture", "picture", JOptionPane.OK_OPTION,p.icon);
//                player.x = player.x + 10;
//                player.y = player.y + 10;
//                player.ya = 0;
//                player.xa = 0;
//            }
//        }
//
//    }


    public void run()throws InterruptedException {
        //traveler.menu();
        JFrame frame = new JFrame("Test");
        Game game = new Game();
        frame.add(game);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
     //       game.move();
            game.repaint();
//            game.gameOver();
            Thread.sleep(10);
        }



    }
}