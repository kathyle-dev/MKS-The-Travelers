package com.escaperooms.application;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Traveler {
    private User user;
    private EscapeRoomGame game;
    private List<ThemeRoom> availableRooms;
    private List<String> inventory = new ArrayList<>();
    private Image image;
    private int x;
    private int y;
    private int velocityX = 0;
    private int velocityY = 0;
    private static final int WITH = 15;
    private static final int HEIGHT = 15;

    public Traveler(User user, EscapeRoomGame game) {
        this.user = user;
        this.game = game;
        setAvailableRooms(game);
    }

    public void jump(ThemeRoom room, boolean isGUI) {
        game.run(this, room, isGUI);
    }

    public User getUser() {
        return this.user;
    }

    private boolean isGameCompleted() {
        boolean result = true;
        for (ThemeRoom room : availableRooms) {
            if (!room.isCompleted()) {
                result = false;
                break;
            }
        }
        return result;
    }

    private void wonSequence() {
        user.setTravelersID(Math.random() * 1000);
        System.out.println(ansi().fg(GREEN).a("All rooms completed! You won!\n" +
                "You have been granted a Traveler's ID.\n" +
                "Your Traveler's ID is " + ansi().fg(RED).a(user.getTravelersID())).reset() + ". DO NOT LOSE IT!");
        user.getFinishTime();
    }

    public void menu() {
        if (!isGameCompleted()) {
            for (int i = 0; i < availableRooms.size(); i++) {
                ThemeRoom currentRoom = availableRooms.get(i);
                if (!currentRoom.isCompleted()) {
                    System.out.println(ansi().fg(GREEN).a(i + ": " + currentRoom.getName()).reset());
                } else {
                    System.out.println(ansi().fg(RED).a(i + ": " + currentRoom.getName() + "(played)").reset());
                }
            }
            String selection = EscapeRoomGame.prompt("Select a room.\n->", "[0-" + (availableRooms.size() - 1) + "]", "Invalid choice.");
            int choice = Integer.parseInt(selection);
            ThemeRoom room = availableRooms.get(choice);
            jump(room, false);
        } else {
            wonSequence();
            System.exit(0);
        }
    }

    public void move() {
        if (getX() + getVelocityX() > 0 && getX() + getVelocityX() < GameGUI.getWIDTH()- getWITH()) {
            setX(getX()+ getVelocityX());
        }
        if (getY() +  getVelocityY() > 0 && getY() + getVelocityY() < GameGUI.getHEIGHT() - (getHEIGHT() * 3)){
            setY(getY()+getVelocityY());
        }
    }


    public List<String> getInventory() {
        return inventory;
    }

    public void showInventory() {
        if (inventory.size() == 0) {
            System.out.println("\nThere is nothing in your inventory");
        } else {
            System.out.println("\nYou have " + inventory + " in your inventory");
        }
    }

    public void addItem(String item) {
        System.out.println("\n Obtained " + item);
        this.inventory.add(item);
    }

    public void removeItem(String item) {
        this.inventory.remove(item);
    }

    public boolean isItemInInventory(String itemName) {
        return this.inventory.contains(itemName);
    }

    public boolean doesItemHaveClue(Item itemName) {
        if (itemName.getHasClue().equalsIgnoreCase("false")) {
            return false;
        }
        return true;
    }

    public List<ThemeRoom> getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(EscapeRoomGame game) {
        List<ThemeRoom> gameList = game.getGameMap().values().stream().collect(Collectors.toList());
        gameList.removeIf(room -> !room.isStartingTheme());
        this.availableRooms = gameList;
    }
    public void keyReleased(KeyEvent e) {
        this.setVelocityX(0);
        this.setVelocityY(0);
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) {
            this.setVelocityY(-2);
        }
        if (code == KeyEvent.VK_DOWN) {
            this.setVelocityY(2);
        }
        if (code == KeyEvent.VK_LEFT) {
            this.setVelocityX(-2);
        }
        if (code == KeyEvent.VK_RIGHT) {
            this.setVelocityX(2);
        }
    }



    public void clearInventory() {
        this.inventory.clear();
    }

    public EscapeRoomGame getGame() {
        return game;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(int velocityY) {
        this.velocityY = velocityY;
    }

    public void paint(Graphics2D g2) {
        g2.drawOval(x,y,15,15);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 15, 15);
    }

    public static int getWITH() {
        return WITH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}
