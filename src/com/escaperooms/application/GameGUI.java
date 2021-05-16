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

                    String[] options = new String[] {"EXIT", "STOP", "RESTART", "PLAY/PAUSE"};
                    int response = JOptionPane.showOptionDialog(this, "Listen to the CD", "CD PLAYER",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options, options[0]);
//                    JDialog dialog = optionPane.createDialog("PLAY MUSIC");
//                    dialog.setVisible(true);
//
                    switch(response){
                        case 1:
                             game.getCurrentAdventure().getCurrentTheme().stopMusic();
                             break;
                        case 2:
                            game.getCurrentAdventure().getCurrentTheme().restartMusic();
                            break;
                        case 3:
                            game.getCurrentAdventure().getCurrentTheme().playMusic(i.getName());
                            break;
                        case -1:
                        case 0:
                            System.out.println("EXIT");
                            JOptionPane.getRootFrame().dispose();
                            game.getCurrentAdventure().getCurrentTheme().exit();
                            traveler.setX(traveler.getX()+20);
                            traveler.setY(traveler.getY()+ 40);
                            traveler.setVelocityY(0);
                            traveler.setVelocityX(0);
                            break;
                        default:
                            System.out.println("NO BUTTON");
                         }

//                        if(response == 1){
//                            game.getCurrentAdventure().getCurrentTheme().stopMusic();
//                            crash();
//                        }else if(response == 2){
//                            game.getCurrentAdventure().getCurrentTheme().restartMusic();
//                            crash();
//                        }else if(response == 3){
//                            game.getCurrentAdventure().getCurrentTheme().playMusic(i.getName());
//                            crash();
//                        }else if(response == -1 || response == 0){
//                            System.out.println("EXIT");
//                            JOptionPane.getRootFrame().dispose();
//                            game.getCurrentAdventure().getCurrentTheme().exit();
//                            traveler.setX(traveler.getX()+20);
//                            traveler.setY(traveler.getY()+ 40);
//                            traveler.setVelocityY(0);
//                            traveler.setVelocityX(0);
//                        }else{
//                            System.out.println("ERRORRRRR");
//                        }


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


}