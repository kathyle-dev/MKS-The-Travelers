package com.escaperooms.application;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    String name;
    String currentRoom;
    long startTime = System.currentTimeMillis();
    int points = 0;
    HashMap<String, String[]> inventory = new HashMap<>();
    List<String> answers = new ArrayList<>();
    boolean winOrLose;
    private double travelersID;


    public double getEndTime() {
        long secs = (System.currentTimeMillis() - startTime)/1000;
        double mins = secs/60;
        return mins;
    }
    public void getFinishTime() {
        long secs = ((System.currentTimeMillis() - startTime)/1000)%60;
        int mins = (int) Math.floor(((System.currentTimeMillis() - startTime)/1000)/60);
        System.out.println(
                "Time : "+mins+" minutes and "+secs+" seconds"
        );
        System.out.println("Points : "+getPoints());
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void losePoints(int points) {
        this.points -= points;
    }

//    public Map<String, String[]> getInventory() {
//        return inventory;
//    }
//
//    public void showInventory() {
//        if(inventory.size()==0){
//            System.out.println("You have nothing in your inventory");
//        }else{
//            System.out.println("You have " + inventory + " in your inventory");
//        }
//    }
//
//    public void addItem(String item) {
//        System.out.println(name+ " Obtained "+item);
//        this.inventory.add(item);
//    }
//
//    public void removeItem(String item) {
//        this.inventory.remove(item);
//    }
//public boolean isItemInInventory(String itemName){
//    return this.inventory.contains(itemName);
//}

    public boolean getWinOrLose() {
        return winOrLose;
    }

    public void setWinOrLose(boolean winOrLose) {
        this.winOrLose = winOrLose;
    }

    public void restartTimer() {
        startTime = System.currentTimeMillis();
    }

    public void move(String roomName){
        this.currentRoom = roomName;
    }

    public boolean isCurrentRoom(String roomName){
        return roomName.equals(this.currentRoom);
    }



    public void addAnswer(String answer){
        this.answers.add(answer);
    }

    public boolean hasAnswer(String answer){
        return this.answers.contains(answer);
    }

    public void newName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getCurrentRoom() {
        return currentRoom;
    }

    double getTravelersID() {
        return travelersID;
    }

    void setTravelersID(double travelersID) {
        this.travelersID = travelersID;
    }
}