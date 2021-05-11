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
    private boolean isCompleted;
    @JsonProperty("isStartingTheme")
    private boolean isStartingTheme;
    private String verb;
    private String noun;
    private String[] splitting;
    private String userInput;
    private String itemSelection;
    private Item currentItem;
    private String itemType;
    Scanner scanner = new Scanner(System.in);
    private Traveler traveler;

    @JsonCreator
    public ThemeRoom(@JsonProperty("name") String name, @JsonProperty("startingPuzzle") String startingPuzzle, @JsonProperty("puzzles") Map<String, Puzzle> puzzles, @JsonProperty("isStartingTheme") boolean isStartingTheme, @JsonProperty("nextTheme") String nextTheme) {
        this.name = name;
        this.puzzles = puzzles;
        this.isStartingTheme = isStartingTheme;
        this.nextTheme = nextTheme;
        this.currentPuzzle = puzzles.get(startingPuzzle);
    }

    public void run(Traveler traveler) {
        this.traveler = traveler;
        while (!isCompleted) {
            System.out.println(printPuzzleMessage());
            input();
            getNextPuzzle();
            traveler.clearInventory();
        }
    }

    public String printPuzzleMessage() {
        String msg = "";
        msg += ("You've entered: " + getName());
        msg += "\nHere's your puzzle: \n" + currentPuzzle.getDescription();
        return msg;
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
        return this.isCompleted;
    }

    public void setCompleted(Boolean completed) {
        this.isCompleted = completed;
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

    public boolean isStartingTheme() {
        return isStartingTheme;
    }

    public String getNextTheme() {
        return nextTheme;
    }

    //Check if user has completed all puzzles
    //Create is completed var in puzzle class

    public boolean isThemeRoomCompleted() {
        boolean result = true;
        Collection<Puzzle> puzzleList = puzzles.values();
        for (Puzzle currentPuzzle : puzzleList) {
            if (!currentPuzzle.isCompleted()) {
                result = false;
            }
        }
        return result;
    }

    //if the current puzzles is completed, get the next puzzle for the user to play
    public void getNextPuzzle() {
        String puzzleName = currentPuzzle.getDoor().getDestination();
        if (puzzleName.equalsIgnoreCase("none")) {
            this.isCompleted = true;
        } else {
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
        while (!isCompleted) {
            System.out.println("What would you like to do");
            setUserInput(scanner.nextLine().toLowerCase(Locale.ROOT));
            if (getUserInput().equals("quit")) {
                System.exit(0);
            } else if (getUserInput().equals("inventory")) {
                traveler.showInventory();
            }
            splitUserInput();
        }
        ;
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

    void checkItemType() {
        if (currentPuzzle.getItems().containsKey(getNoun())) {
            itemSelection();
        } else if (getNoun().equals("door")) {
            if(traveler.getInventory().size() > 0){
                traveler.showInventory();
                List<String> solution = currentPuzzle.getDoor().getSolution();
                checkSolution(solution);
            }else{
                System.out.println("You can't open this door without items. Nice try.");
            }

        } else {
            System.out.println("invalid item type");
            input();
        }

    }

    void checkSolution(List<String> solution){
        Boolean enteredSolution = false;
        int index = 0;
        while (!enteredSolution){
            if (index == solution.size()) {
                System.out.println("YOU GOT OUT");
                setCompleted(true);
                enteredSolution = true;
            } else {
                System.out.println("Enter an item to unlock this door.");
                String clue = scanner.nextLine();
                if (solution.get(index).equalsIgnoreCase(clue) && traveler.isItemInInventory(clue)) {
                    index++;
                } else if (!traveler.isItemInInventory(clue)) {
                    System.out.println("You don't have that.");
                    break;
                }else{
                    System.out.println("WRONG!");
                    index = 0;
                    break;
                }
            }

        }
    }

    void itemSelection() {
        System.out.println("Which " + getNoun() + " would you like to perform the previous action on");
        Map<String, Item> itemMap = currentPuzzle.getItems().get(getNoun());

        for (Map.Entry<String, Item> entry : itemMap.entrySet()) {
            System.out.println(entry.getKey());
        }

        itemSelection = scanner.nextLine();
        if (itemMap.containsKey(itemSelection)) {
            currentItem = itemMap.get(itemSelection);
            currentItem.setNoun(getNoun());
            currentItem.setVerb(getVerb());
            use();

        } else {
            itemSelection();
        }

        System.out.println(currentItem.getName() + " " + currentItem.getDescription());
    }

    public void use() {
        switch (getNoun()) {
            case "cd":
                useCD();
                break;
            case "picture":
            case "actionfigure":
                useGenericItem();
                break;
            default:
                System.out.println("invalid input try again");
                // input();
        }

    }


    public void useCD() {
        switch (getVerb()) {
            case "look at":
            case "examine":
            case "view":
            case "describe":
                currentItem.getDescription();
                break;
            case "play":
            case "listen to":
                // musicPlayer.run();
                break;
            case "stop":
                //musicPlayer.stopMusic();
                break;
            default:
                System.out.println("You can not do that action with " + getNoun());
        }
    }

    public void useGenericItem() {
        switch (getVerb()) {
            case "look at":
            case "examine":
            case "view":
            case "describe":
                currentItem.getDescription();
                break;
            case "move":
            case "pick up":
            case "lift":
                if (!currentItem.getHasClue().equals("false")) {
                    System.out.println("you have added one " + currentItem.getHasClue());
                    traveler.addItem(currentItem.getHasClue());

                } else {
                    System.out.println("When you " + getVerb() + getNoun() + " Nothing was there");
                }
                break;
            default:
                System.out.println("You can not do that action with " + getNoun());

        }


    }
}

