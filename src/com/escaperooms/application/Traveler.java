package com.escaperooms.application;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Traveler {
    private User user;
    private EscapeRoomGame game;
    private List<Adventure> availableGames;
    private List<String> inventory = new ArrayList<>();

    public Traveler(User user, EscapeRoomGame game) {
        this.user = user;
        this.game = game;
        this.availableGames = game.getGameMap().values().stream().collect(Collectors.toList());
    }

    public void jump(Adventure adventure, boolean isGUI) {
        game.run(this, adventure, isGUI);
    }

    public User getUser() {
        return this.user;
    }

    private boolean isGameCompleted() {
        for (Adventure adventure : availableGames) {
            if (!adventure.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    private void wonSequence() {
        user.setTravelersID(Math.random() * 1000);
        System.out.println(ansi().fg(GREEN).a("All rooms completed! You won!\n" +
                "You have been granted a Traveler's ID.\n" +
                "Your Traveler's ID is " + ansi().fg(RED).a(user.getTravelersID())).reset() + ". DO NOT LOSE IT!");
        user.getFinishTime();
    }

    public void menu() {
        System.out.println("IN MENU");
        while (!isGameCompleted()) {
            for (int i = 0; i < availableGames.size(); i++) {
                Adventure currentAdventure = availableGames.get(i);
                if (!currentAdventure.isCompleted()) {
                    System.out.println(ansi().fg(GREEN).a(i + ": " + currentAdventure.getName()).reset());
                } else {
                    System.out.println(ansi().fg(RED).a(i + ": " + currentAdventure.getName() + "(played)").reset());
                }
            }
            String selection = EscapeRoomGame.prompt("Select a room.\n->", "[0-" + (availableGames.size() - 1) + "]", "Invalid choice.");
            int choice = Integer.parseInt(selection);
            Adventure adventure = availableGames.get(choice);
            jump(adventure, false);
        }
            wonSequence();
            System.exit(0);
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

    public List<Adventure> getAvailableGames() {
        return availableGames;
    }

    public void clearInventory() {
        this.inventory.clear();
    }

    public EscapeRoomGame getGame() {
        return game;
    }
}
