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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameGUI extends JPanel {
    private JFrame frame;
    private EscapeRoomGame game;
    private Traveler traveler;
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    Collection<Item> itemList = new ArrayList<>();


    public GameGUI() {
    }

    public GameGUI(Traveler traveler) {
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

    public void move() {
        traveler.move();
    }

    public void crash() {
        for (Item i : itemList.toArray(new Item[0])) {

            if (i.getBounds().intersects(traveler.getBounds())) {
                if (i.getItemType().equals("CD")) {

//                    String[] options = new String[] {"EXIT", "STOP", "RESTART", "PLAY/PAUSE"};
//                    int response = JOptionPane.showOptionDialog(this, "Listen to the CD", "CD PLAYER",
//                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
//                            null, options, options[0]);
//                    JDialog dialog = optionPane.createDialog("PLAY MUSIC");
//                    dialog.setVisible(true);

                    String[] options = new String[]{"EXIT", "STOP", "RESTART", "PLAY/PAUSE"};
                    JOptionPane pane = new JOptionPane("Seems like you can listen to the CD..",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                            null, options, options[3]);

                    JDialog dialog = new JDialog(frame, "CD PLAYER", true);
                    dialog.setContentPane(pane);
                    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

//                    Object response = pane.getValue();
//                    System.out.println("RESPONSE " + response.toString());

// ** NEED TO RESET DIALOG WINDOW SO IT CAN BE SEEN WITHOUT DRAGGING IT OPEN
                    //**JUST NEED TO TARGET THE PROPERTY CORRECTLY AND WE SHOULD BE ABLE TO USE THE MUSIC PLAYER WITHOUT CLOSING!
                    pane.addPropertyChangeListener(new PropertyChangeListener() {
                        @Override
                        public void propertyChange(PropertyChangeEvent evt) {
                            System.out.println("EVT " + evt.getPropertyName());
                            switch (evt.getPropertyName()) {
                                case "STOP":
                                    System.out.println("STOP");
                                    game.getCurrentAdventure().getCurrentTheme().stopMusic();
                                    break;
                                case "RESTART":
                                    System.out.println("RESTART");
                                    game.getCurrentAdventure().getCurrentTheme().restartMusic();
                                    break;
                                case "PLAY/PAUSE":
                                    System.out.println("PLAY");
                                    game.getCurrentAdventure().getCurrentTheme().playMusic(i.getName());
                                    break;
                                case "EXIT":
                                    System.out.println("EXIT");
                                    dialog.setVisible(false);
                                    game.getCurrentAdventure().getCurrentTheme().exit(); // TODO:  If there is a clip, close it. -> check clip.isActive
                                    traveler.setX(traveler.getX() + 20);
                                    traveler.setY(traveler.getY() + 40);
                                    traveler.setVelocityY(0);
                                    traveler.setVelocityX(0);
                                    break;
                                default:
                                    System.out.println("NO BUTTON");
                            }

                        }
                    });

                    dialog.pack();
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);

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


                } else if (i.getItemType().equals("Picture")) {
                    JOptionPane.showMessageDialog(this, i.getDescription(), i.getName(), JOptionPane.OK_OPTION, i.icon);
                    traveler.setX(traveler.getX() + 10);
                    traveler.setY(traveler.getY() + 10);
                    traveler.setVelocityY(0);
                    traveler.setVelocityX(0);
                }

            }
        }
    }

    public void deployItems(Graphics2D g2) {
        this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(e -> e.values().forEach(i -> i.paint(g2)));
    }

    public void setItemList() {
        this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(entry -> itemList.addAll(entry.values()));
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

    public void run() {
        traveler.jump(traveler.getAvailableGames().get(0), true);
        setItemList();
    }

    public void createFrame(){
        frame = new JFrame("Test");
        frame.add(this);
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        if (currentDoor instanceof Door && (currentDoor.getBounds().intersects(traveler.getBounds()))) {
            JOptionPane.showMessageDialog(this, checkSolution, currentDoor.getDestination(), JOptionPane.OK_OPTION, currentDoor.icon);
            traveler.setX(traveler.getX() - 10);
            traveler.setY(traveler.getY() - 10);
            traveler.setVelocityY(0);
            traveler.setVelocityX(0);
        }
    }
}