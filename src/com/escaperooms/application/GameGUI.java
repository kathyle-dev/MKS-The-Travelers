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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameGUI extends JPanel implements ActionListener  {
    private EscapeRoomGame game;
    private Traveler traveler;
    private static final int HEIGHT = 1000;
    private static final int WIDTH = 1000;
    Collection<Item> itemList = new ArrayList<>();
    private Frame frame;
    private Item item;
    ArrayList<String> currentPuzzleHints;
    int counter = 0;
    String hint;
    public GameGUI() {
    }

    public GameGUI(Traveler traveler,Frame frame) {
        this.game = traveler.getGame();
        this.traveler = traveler;
        Timer t = new Timer(5, this);
        t.start();
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        this.frame = frame;
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
    public String hintGui() {
        return this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getAHint();
    }

    public void move() {
        traveler.move();
    }

    public void crash() {
        for (Item i : itemList.toArray(new Item[0])) {

            if (i.getBounds().intersects(traveler.getBounds())) {
                if (i.getItemType().equals("CD")) {
                    setItem(i);
                    traveler.setX(500);
                    traveler.setY(500);
                    traveler.setVelocityY(0);
                    traveler.setVelocityX(0);
                    game.getCurrentAdventure().getCurrentTheme().getMusicPlayer().setSong(i.getName());
                    game.getCurrentAdventure().getCurrentTheme().getMusicPlayer().run();
                    JOptionPane.showMessageDialog(this,i.getDescription(),i.getName(),JOptionPane.INFORMATION_MESSAGE, null);
                    frame.musicPlayer();
//                    String[] options = new String[]{"EXIT", "STOP", "RESTART", "PLAY/PAUSE"};
//                    int response = JOptionPane.showOptionDialog(this, "Listen to the CD", "CD PLAYER",
//                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
//                            null, options, options[0]);
//                    JDialog dialog = optionPane.createDialog("PLAY MUSIC");
//                    dialog.setVisible(true);
//
//                    switch (response) {
//                        case 1:
//                            game.getCurrentAdventure().getCurrentTheme().stopMusic();
//                            break;
//                        case 2:
//                            game.getCurrentAdventure().getCurrentTheme().restartMusic();
//                            break;
//                        case 3:
//                            game.getCurrentAdventure().getCurrentTheme().playMusic(i.getName());
//                            break;
//                        case -1:
//                        case 0:
//                            System.out.println("EXIT");
//                            JOptionPane.getRootFrame().dispose();
//                            game.getCurrentAdventure().getCurrentTheme().exit(); // TODO:  If there is a clip, close it. -> check clip.isActive
//                            traveler.setX(traveler.getX() + 20);
//                            traveler.setY(traveler.getY() + 40);
//                            traveler.setVelocityY(0);
//                            traveler.setVelocityX(0);
//                            break;
//                        default:
//                            System.out.println("NO BUTTON");
//                    }

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
                    traveler.setX(500);
                    traveler.setY(500);
                    traveler.setVelocityY(0);
                    traveler.setVelocityX(0);
                    if(JOptionPane.showConfirmDialog(this, i.getDescription(), i.getName(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, i.icon) == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(this, i.getHasClue(), i.getName(),JOptionPane.INFORMATION_MESSAGE, null);
                    }
                }else if(i.getItemType().equals("Action Figure")){
                    traveler.setX(500);
                    traveler.setY(500);
                    traveler.setVelocityY(0);
                    traveler.setVelocityX(0);
                    if(JOptionPane.showConfirmDialog(this, i.getDescription(), i.getName(), JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, i.icon) == JOptionPane.YES_OPTION){
                        JOptionPane.showMessageDialog(this, i.getHasClue(), i.getName(),JOptionPane.INFORMATION_MESSAGE, null);
                    }

                }

            }
        }
    }

    public void deployItems(Graphics2D g2) {

        if(!game.getCurrentAdventure().getCurrentTheme().isThemeRoomCompleted()) {
            this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(e -> e.values().forEach(i -> i.paint(g2)));
        }
    }

    public void setItemList() {
        if (!game.getCurrentAdventure().getCurrentTheme().isThemeRoomCompleted()) {
            this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getItems().values().forEach(entry -> itemList.addAll(entry.values()));
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

    //    run();
        if(!game.getCurrentAdventure().getCurrentTheme().isThemeRoomCompleted()){
            deployItems(g2d);
            crashDoor();
            deployDoors(g2d);
            traveler.paint(g2d);
            crash();
        }


        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 30));
//        g2d.drawString(String.valueOf(game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getDescription()), 10, 30);
//        JButton  hint= new JButton("Hint");
//        hint.setBounds(500,100,75,50);
//        hint.setFont(new Font("Comic Sans",Font.BOLD,15));
//        hint.addActionListener(this);
//        hint.paint(g2d);
//        frame.getFrame().add(hint);
//        hint.setVisible(true);

    }

    public void run() {
        traveler.jump(traveler.getAvailableGames().get(0), true);
        setItemList();
    }
    public void run(Adventure adventure) {
        traveler.jump(adventure, true);
        setItemList();
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public void deployDoors(Graphics2D g2) {
        if (!game.getCurrentAdventure().getCurrentTheme().isThemeRoomCompleted()) {
            this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getDoor().paint(g2);
        }
    }

    public void crashDoor() {

        Door currentDoor = this.game.getCurrentAdventure().getCurrentTheme().getCurrentPuzzle().getDoor();
        List <String> currentInventory = this.traveler.getInventory();
        if (currentDoor instanceof Door && (currentDoor.getBounds().intersects(traveler.getBounds()))) {
            traveler.setX(500);
            traveler.setY(500);
            traveler.setVelocityY(0);
            traveler.setVelocityX(0);

            String userInput = JOptionPane.showInputDialog(this, "The door seems locked. Find a way to unlock it.\n\nPlease enter the correct keys.", "Door to the next room.", JOptionPane.PLAIN_MESSAGE);
            if(userInput != null || !("".equals(userInput))) {
                String[] words = userInput.split(",");
                for (int i = 0; i < words.length; i++) {
                    words[i] = words[i].trim();
                    System.out.println("xrash door");
                }
                if (game.getCurrentAdventure().getCurrentTheme().checkSolutionGui(words)) {
                    String[] option = new String[]{"OK"};
                    int response = JOptionPane.showOptionDialog(this, "You unlocked the door!", "Door Unlocked", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

                    if (response == 0) {
                        System.out.println(game.getCurrentAdventure().getCurrentTheme().isThemeRoomCompleted());
                        if (game.getCurrentAdventure().getCurrentTheme().isThemeRoomCompleted()) {
                            System.out.println("made it to endgamelogic");
                            game.getCurrentAdventure().getCurrentTheme().setCompleted(true);
                            game.getCurrentAdventure().getNextThemeRoom();
                           if(game.isCurrentAdventureCompleted()) {
                               frame.endGamePanel();
                               game.getCurrentAdventure().getCurrentTheme().resetPuzzleComplete();
                           }
                        } else {
                            setItemList();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "The keys you entered was not able to open the door.", "Door Locked", JOptionPane.PLAIN_MESSAGE, null);
                }
            }
//            JOptionPane.showMessageDialog(this, checkSolution, currentDoor.getDestination(), JOptionPane.OK_OPTION,currentDoor.icon);
            traveler.setX(traveler.getX() - 30);
            traveler.setY(traveler.getY() - 10);
            traveler.setVelocityY(0);
            traveler.setVelocityX(0);
            System.out.println(userInput);
            
//            if(checkSolution.equals(currentInventory)){
//
//            }

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
        move();
    }

    public Frame getFrame() {
        return frame;
    }

    public void setFrame(Frame frame) {
        this.frame = frame;
    }

    public EscapeRoomGame getGame() {
        return game;
    }

    public void setGame(EscapeRoomGame game) {
        this.game = game;
    }

    public Traveler getTraveler() {
        return traveler;
    }

    public void setTraveler(Traveler traveler) {
        this.traveler = traveler;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


}