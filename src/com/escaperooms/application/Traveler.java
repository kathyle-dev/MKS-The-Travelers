package com.escaperooms.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
public class Traveler {
   private User user;
    EscapeRoomGame game;
    List<ThemeRoom> availableRooms;
    List<String> inventory = new ArrayList<>();

    public Traveler(User user, EscapeRoomGame game) {
        this.user = user;
        this.game = game;
        setAvailableRooms(game);
    }

    private void jump(ThemeRoom room) {
        game.run(this, room);
    }

    public User getUser() {
        return this.user;
    }

    private boolean isGameCompleted() {
        boolean result = true;
        for(ThemeRoom room : availableRooms) {
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
        if(!isGameCompleted()) {
            for(int i = 0; i < availableRooms.size(); i++) {
                ThemeRoom currentRoom = availableRooms.get(i);
                if(!currentRoom.isCompleted()) {
                    System.out.println(ansi().fg(GREEN).a(i + ": " + currentRoom.getName()).reset());
                } else {
                    System.out.println(ansi().fg(RED).a(i + ": " + currentRoom.getName() + "(played)").reset());
                }
            }
            String selection = EscapeRoomGame.prompt("Select a room. ", "[0-" + (availableRooms.size()-1) + "]", "Invalid choice.");
            int choice = Integer.parseInt(selection);
            ThemeRoom room = availableRooms.get(choice);
            jump(room);
        } else {
            wonSequence();
            System.exit(0);
        }
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void showInventory() {
        if(inventory.size()==0){
            System.out.println("There is nothing in your inventory");
        }else{
            System.out.println("You have " + inventory + " in your inventory");
        }
    }

    public void addItem(String item) {
        System.out.println(" Obtained "+item);
        this.inventory.add(item);
    }

    public void removeItem(String item) {
        this.inventory.remove(item);
    }

    public boolean isItemInInventory(String itemName){
        return this.inventory.contains(itemName);
    }

    public boolean doesItemHaveClue(Item itemName){
        if(itemName.getHasClue().equalsIgnoreCase("false")){
            return false;
        }
        return true;
    }
    public void setAvailableRooms(EscapeRoomGame game) {
        List<ThemeRoom> gameList = game.getGameMap().values().stream().collect(Collectors.toList());
        gameList.removeIf(room -> !room.isStartingTheme());
        this.availableRooms = gameList;
    }

    public void clearInventory(){
        this.inventory.clear();
    }
}
