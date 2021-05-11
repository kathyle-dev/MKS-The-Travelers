package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class ThemeRoom {

    @JsonProperty("name")
    private String name;
    @JsonProperty("puzzles")
    private Map<String, Puzzle> puzzles;
    @JsonProperty("nextTheme")
    private String nextTheme;
    private Puzzle currentPuzzle;
    private boolean isCompleted = false;
    private String verb;
    private String noun;
    private String[] splitting;
    private String userInput;
    private String itemSelection;
    private Item currentItem;
    private String itemType;
    Scanner scanner = new Scanner(System.in);

    @JsonCreator
    public ThemeRoom(@JsonProperty("name") String name, @JsonProperty("startingPuzzle") String startingPuzzle, @JsonProperty("puzzles") Map<String, Puzzle> puzzles, @JsonProperty("nextTheme") String nextTheme){
        this.name = name;
        this.puzzles = puzzles;
        this.nextTheme = nextTheme;
        this.currentPuzzle = puzzles.get(startingPuzzle);
    }

    public void run(Traveler traveler, ThemeRoom room) {
        System.out.println("You are in "+ getName());
        System.out.println("Here's your first puzzle:");
        System.out.println(currentPuzzle.getDescription());
        System.out.println("size: "+ puzzles.size());
        input();
    }

    /*
     * GETTER'S AND SETTERS
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(Map<String, Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    public Puzzle getCurrentPuzzle() {
        return currentPuzzle;
    }

    public void setCurrentPuzzle(Puzzle currentPuzzle) {
        this.currentPuzzle = currentPuzzle;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String[] getSplitting() {
        return splitting;
    }

    public void setSplitting(String[] splitting) {
        this.splitting = splitting;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemSelection() {
        return itemSelection;
    }

    public void setItemSelection(String itemSelection) {
        this.itemSelection = itemSelection;
    }

//Check if user has completed all puzzles
    //Create is completed var in puzzle class

    public boolean isThemeRoomCompleted(){
        boolean result = true;
        Collection<Puzzle> puzzleList = puzzles.values();
        for (Puzzle currentPuzzle  : puzzleList) {
            if(!currentPuzzle.isCompleted()){
                result = false;
            }
        }
        return result;
    }

    //if the current puzzles is completed, get the next puzzle for the user to play
    public void getNextPuzzle(){
        if(currentPuzzle.isCompleted()){
            String puzzleName = currentPuzzle.getDoor().getDestination();
            setCurrentPuzzle(puzzles.get(puzzleName));
        }
    }

    @Override
    public String toString() {
        return "ThemeRoom{" +
                "name='" + name + '\'' +
                ", puzzles=" + puzzles +
                ", nextTheme='" + nextTheme + '\'' +
                ", currentPuzzle=" + currentPuzzle +
                ", isCompleted=" + isCompleted +
                '}';
    }

    public void input() {

       do {
           System.out.println("What would you like to do");
           setUserInput(scanner.nextLine().toLowerCase(Locale.ROOT));
           if(getUserInput().equals("quit")){
               System.exit(0);
           }
           splitUserInput();
       } while(!getUserInput().equals("quit"));

    }


    public void splitUserInput() {
        setSplitting(getUserInput().split("\\s"));
        if (getSplitting().length == 2) {
            setVerb(getSplitting()[0]);
            setNoun(getSplitting()[1]);

        } else if (getSplitting().length == 3) {
            setVerb(getSplitting()[0] + " " + getSplitting()[1]);
            setNoun(getSplitting()[2]);
        } else {
            input();
        }
        checkItemType();
    }

    void checkItemType(){
        if (currentPuzzle.getItems().containsKey(getNoun())) {
            itemSelection();
        }else {
            System.out.println("invalid item type");
            input();
        }

    }


    void itemSelection(){
        System.out.println("Which "+ getNoun() + " would you like to perform the previous action on");
        Map<String, Item> itemMap = currentPuzzle.getItems().get(getNoun());

        for(Map.Entry<String,Item> entry:itemMap.entrySet()){
            System.out.println(entry.getKey());
        }

        itemSelection = scanner.nextLine();
        if(itemMap.containsKey(itemSelection)){
            currentItem = itemMap.get(itemSelection);
        }else {
            itemSelection();
        }

        System.out.println(currentItem.getName() + " " + currentItem.getDescription());
    }






}