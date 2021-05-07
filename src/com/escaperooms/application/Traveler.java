//package com.escaperooms.application;
//
//import java.util.ArrayList;
//import java.util.List;
//import static org.fusesource.jansi.Ansi.*;
//import static org.fusesource.jansi.Ansi.Color.*;
//public class Traveler {
//    User user;
//    EscapeRoom escapeRoom;
//
//
//    public Traveler(User user, EscapeRoom escapeRoom) {
//        this.user = user;
//        this.escapeRoom = escapeRoom;
//    }
//
//    private void jump(EscapeRoom room) {
//        Playable preRoom = escapeRoom.getEscapeRoomPlayable(room.getName());
//        preRoom.setCompleted();
//        room.run(this, escapeRoom);
//    }
//
//    public User getUser() {
//        return this.user;
//    }
//
//    public List<Playable> getRooms() {
//        return new ArrayList<>(escapeRoom.getEscapeRooms().values());
//    }
//
//    private boolean isEscapeRoomCompleted() {
//        List<Playable> availableRooms = getRooms();
//        boolean result = true;
//        for(Playable room : availableRooms) {
//            if (!room.isCompleted()) {
//                result = false;
//                break;
//            }
//        }
//        return result;
//    }
//
//    private void wonSequence() {
//        user.setTravelersID(Math.random() * 1000);
//        System.out.println(ansi().fg(GREEN).a("All rooms completed! You won!\n" +
//                "You have been granted a Traveler's ID.\n" +
//                "Your Traveler's ID is " + ansi().fg(RED).a(user.getTravelersID())).reset() + ". DO NOT LOSE IT!");
//        user.getFinishTime();
//    }
//
//    public void menu() {
//        List<Playable> availableRooms = getRooms();
//        if(!isEscapeRoomCompleted()) {
//            for(int i = 0; i < availableRooms.size(); i++) {
//                Playable currentRoom = availableRooms.get(i);
//                if(!currentRoom.isCompleted()) {
//                    System.out.println(ansi().fg(GREEN).a(i + ": " + currentRoom.getName()).reset());
//                } else {
//                    System.out.println(ansi().fg(RED).a(i + ": " + currentRoom.getName() + "(played)").reset());
//                }
//            }
//            String selection = EscapeRoom.prompt("Select a room. ", "[0-" + (availableRooms.size()-1) + "]", "Invalid choice.");
//            int choice = Integer.parseInt(selection);
//            EscapeRoom room = availableRooms.get(choice).getEscapeRoom();
//            jump(room);
//        } else {
//            wonSequence();
//            System.exit(0);
//        }
//
//    }
//}
