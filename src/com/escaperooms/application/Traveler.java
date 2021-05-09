package com.escaperooms.application;

import java.util.List;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
public class Traveler {
    User user;
    EscapeRoomGame game;
    List<ThemeRoom> availableRooms;


    public Traveler(User user, EscapeRoomGame game) {
        this.user = user;
        this.game = game;
        this.availableRooms = game.getGameList();
    }

    private void jump(ThemeRoom room) {
        room.setCompleted();
        room.run(this, room);
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
}
