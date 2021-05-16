package com.escaperooms.application;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameGUI extends JPanel{
    private EscapeRoomGame game;
    private Traveler traveler;
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    Collection<Item> itemList = new ArrayList<>();



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

        ActionListener listener;

    }

    public void move(){
        traveler.move();
    }

    public void crash() {
        for(Item i: itemList.toArray(new Item[0])) {

            if (i.getBounds().intersects(traveler.getBounds())) {
                if(i.getItemType().equals("CD")){
                    JButton pauseButton = new JButton("Pause");
                    JButton playButton = new JButton("Play");
                    JButton stopButton = new JButton("Stop");
                    JButton restartButton = new JButton("restart");
                    playButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.getCurrentAdventure().getCurrentTheme().playMusic();
                        }
                    });

                    pauseButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.getCurrentAdventure().getCurrentTheme().playMusic();
                        }
                    });

                    stopButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.getCurrentAdventure().getCurrentTheme().playMusic();
                        }
                    });

                    restartButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            game.getCurrentAdventure().getCurrentTheme().restartMusic();
                        }
                    });

                    JOptionPane jOptionPane;

                }
                else if(i.getItemType().equals("Picture")){
                    JOptionPane.showMessageDialog(this, i.getDescription(), i.getName(), JOptionPane.OK_OPTION,i.icon);
                    traveler.setX(traveler.getX()+10);
                    traveler.setY(traveler.getY()+ 10);
                    traveler.setVelocityY(0);
                    traveler.setVelocityX(0);
                }

            }
        }
    }

    public void deployItems(Graphics2D g2) {
        this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(e -> e.values().forEach(i -> i.paint(g2)));
    }

    public void setItemList(){
        this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(entry-> itemList.addAll(entry.values()));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        deployItems(g2d);
        deployDoors(g2d);
        traveler.paint(g2d);

        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
        g2d.drawString(String.valueOf(game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getDescription()), 10, 30);


    }

    public void run(){
        traveler.jump(traveler.getAvailableGames().get(0), true);
        setItemList();
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public void deployDoors(Graphics2D g2) {
        this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getDoor().paint(g2);
    }

    public void crashDoor() {
        Door currentDoor = this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getDoor();
        String checkSolution = this.game.getCurrentAdventure().getCurrentTheme().checkSolution(traveler.getInventory());

        if(currentDoor instanceof Door && (currentDoor.getBounds().intersects(traveler.getBounds()))){
            JOptionPane.showMessageDialog(this, checkSolution, currentDoor.getDestination(), JOptionPane.OK_OPTION,currentDoor.icon);
            traveler.setX(traveler.getX() - 10);
            traveler.setY(traveler.getY() - 10);
            traveler.setVelocityY(0);
            traveler.setVelocityX(0);
        }
    }
}