package com.escaperooms.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.JOptionPane.OK_OPTION;

public class Frame extends Component implements ActionListener {
    private final int DEFAULT_SCREEN_SIZE = 1000;
    private JFrame frame;
    private GameGUI gameGUI;
    private Container container;
    private CardLayout card;
    private EndGamePanel endGame;
    User user;
    EscapeRoomGame escapeRoomGame;
    Traveler traveler;
   MusicPlayerPanel musicPlayerPanel;
    private JTextArea printText;



    public Frame(String name, Traveler traveler) throws IOException {

        card = new CardLayout();
        frame = new JFrame(name);
        endGame = new EndGamePanel(this);
        container = frame.getContentPane();
        this.traveler = traveler;
        this.gameGUI = new GameGUI(traveler,this);
        gameGUI.run();
        musicPlayerPanel = new MusicPlayerPanel(this,this);


    }


    public void musicPlayer(){
        card.show(container,"MusicPlayerPanel");
        gameGUI.requestFocus();
    }

    public void endGamePanel(){
        card.show(container,"endGame");
        gameGUI.requestFocus();
    }


    public void showGame() {
        instantiate();
        frame.pack();
        frame.setVisible(true);
        frame.setSize(1000, 1000);
        frame.setResizable(false);
    }


    public void instantiate() {
        container.setLayout(card);
        addComponentsToPane();
        frame.setSize(1000, 1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void addComponentsToPane() {
        JButton button = new JButton("Button");
        button.setActionCommand("Switch");
        button.addActionListener(this);
        JButton button2 = new JButton("Welcome, Click to get started!");
        button2.setSize(50, 50);
        button2.setActionCommand("Switch");
        button2.addActionListener(this);
        WelcomeScreen welcomeScreen = new WelcomeScreen(this);
        JButton  hint= new JButton("Hint");
        hint.setBounds(500,100,75,50);
        hint.setFont(new Font("Comic Sans",Font.BOLD,15));
        hint.addActionListener(this);
        gameGUI.add(hint);
        container.add("welcomeScreen",welcomeScreen);
        container.add("gameGui",gameGUI);
        container.add("endGame",endGame);
      container.add("MusicPlayerPanel",musicPlayerPanel);



    }



    public void next() {
        card.next(container);
        endGame.requestFocus();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("Switch")) {

            card.next(container);
            gameGUI.requestFocus();
        }
        if (e.getActionCommand().equals("Start")) {

//            JOptionPane.showMessageDialog(this,
//                    "Test",
//                    "Test",
//                    OK_OPTION);
            card.show(container, "gameGui");
            gameGUI.requestFocus();
        }
        if (e.getActionCommand().equals("Restart")) {
            card.show(container, "gameGui");
            gameGUI.requestFocus();
        }
        if (e.getActionCommand().equals("Exit")) {
            System.out.println("Exit");
        }
        if (e.getActionCommand().equals("Play")) {
            gameGUI.getGame().getCurrentAdventure().getCurrentTheme().getMusicPlayer().playPauseStop();
        }
        if (e.getActionCommand().equals("Stop")) {
            gameGUI.getGame().getCurrentAdventure().getCurrentTheme().getMusicPlayer().stopMusic();
        }
        if (e.getActionCommand().equals("Restart CD")) {
            gameGUI.getGame().getCurrentAdventure().getCurrentTheme().getMusicPlayer().restart();
        }
        if (e.getActionCommand().equals("Exit Music Player")) {
            gameGUI.getGame().getCurrentAdventure().getCurrentTheme().getMusicPlayer().exit();
            card.show(container, "gameGui");
            gameGUI.requestFocus();
        }
        if(e.getActionCommand().equals("Hint")){
            gameGUI.hintGui();
            JOptionPane.showMessageDialog(this,gameGUI.hintGui(),"hint",JOptionPane.INFORMATION_MESSAGE);
            gameGUI.requestFocus();
        }

    }

    public static class WelcomeScreen extends JPanel {
        public WelcomeScreen(ActionListener listener) {
            setBorder(new EmptyBorder(10, 10, 10, 10));
            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.anchor = GridBagConstraints.NORTH;
            JButton start = new JButton("Start");
            JButton retreat = new JButton("Exit");
            add(new JLabel("<html><h1><strong><i>The Traveler</i></strong></h1><hr></html>"), gbc);
            start.addActionListener(listener);
            retreat.addActionListener(listener);
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JPanel buttons = new JPanel(new GridBagLayout());
            buttons.add(start);
            buttons.add(retreat);
            add(buttons, gbc);
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_SCREEN_SIZE, DEFAULT_SCREEN_SIZE);
    }

    public GameGUI getGameGUI() {
        return gameGUI;
    }

    public void setGameGUI(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }
}


