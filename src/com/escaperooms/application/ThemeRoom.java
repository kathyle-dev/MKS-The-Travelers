package com.escaperooms.application;

import com.escaperooms.music.MusicPlayer;
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
    private final MusicPlayer musicPlayer = new MusicPlayer();
    private Traveler traveler;
    private List<String> validPrepositions =new ArrayList<String>(){{ add("AT"); add("TO"); add("UP");}};

    @JsonCreator
    public ThemeRoom(@JsonProperty("name") String name, @JsonProperty("startingPuzzle") String startingPuzzle, @JsonProperty("puzzles") Map<String, Puzzle> puzzles, @JsonProperty("isStartingTheme") boolean isStartingTheme, @JsonProperty("nextTheme") String nextTheme) {
        this.name = name;
        this.puzzles = puzzles;
        this.isStartingTheme = isStartingTheme;
        this.nextTheme = nextTheme;
        this.currentPuzzle = puzzles.get(startingPuzzle);
    }

    public void run(Traveler traveler, boolean isGUI) {
        this.traveler = traveler;
        if(isGUI){
            System.out.println(printPuzzleMessage());
        } else{
            while (!isThemeRoomCompleted()) {
                System.out.println(printPuzzleMessage());
                input();
                getNextPuzzle();
                traveler.clearInventory();
            }
        }
    }

    public String printPuzzleMessage() {
        String msg = "";
        msg += ("\nYou've entered: " + getName() + "\n");
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

    public boolean isStartingTheme() {
        return isStartingTheme;
    }

    public String getNextTheme() {
        return nextTheme;
    }

    //Check if user has completed all puzzles
    //Create is completed var in puzzle class

    public boolean isThemeRoomCompleted() {
        Collection<Puzzle> puzzleList = puzzles.values();
        for (Puzzle currentPuzzle : puzzleList) {
            if (!currentPuzzle.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    //if the current puzzles is completed, get the next puzzle for the user to play
    public void getNextPuzzle() {
        String puzzleName = currentPuzzle.getDoor().getDestination();
        setCurrentPuzzle(puzzles.get(puzzleName));
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
        while (!currentPuzzle.isCompleted()) {
            System.out.println("What would you like to do");
            setUserInput(scanner.nextLine().toUpperCase().trim());
            if (getUserInput().equalsIgnoreCase("quit")) {
                System.exit(0);
            } else if (getUserInput().equalsIgnoreCase("inventory")) {
                traveler.showInventory();
            }
            splitUserInput();
        }
    }


    public void splitUserInput() {
        try{
            setSplitting(getUserInput().split("\\s", 3));
            validateInput();
            checkItemType();

        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error: you didn't enter your move correctly!");
        }
    }

    // Checks item type
    void checkItemType() {
        if (currentPuzzle.getItems().containsKey(getNoun())) {
            itemSelection();
        } else if (getNoun().equalsIgnoreCase("door")) {
            if(traveler.getInventory().size() > 0){
                traveler.showInventory();
                List<String> solution = currentPuzzle.getDoor().getSolution();
                checkSolution(solution);
            }else{
                System.out.println("\nYou can't open this door without items. Nice try.\n");
            }

        } else {
            System.out.println("\ninvalid item type\n");
            input();
        }
    }

    void checkSolution(List<String> solution){
        Boolean enteredSolution = false;
        int index = 0;
        while (!enteredSolution){
            if (index == solution.size()) {
                System.out.println("\nYOU GOT OUT");
                currentPuzzle.setCompleted(true);
                enteredSolution = true;
            } else {
                System.out.println("\nEnter an item to unlock this door.");
                String clue = scanner.nextLine().trim();
                if (solution.get(index).equalsIgnoreCase(clue)) {
                    index++;
                }else{
                    System.out.println("\nWRONG!");
                    index = 0;
                    break;
                }
            }

        }
    }

    void itemSelection() {
        System.out.print("\nWhich " + getNoun() + " would you like to perform the previous action on?\n");
        Map<String, Item> itemMap = currentPuzzle.getItems().get(getNoun());

        for (Map.Entry<String, Item> entry : itemMap.entrySet()) {
            System.out.println(entry.getKey());
        }
        itemSelection = scanner.nextLine().toUpperCase().trim();
        if (itemMap.containsKey(itemSelection)) {
            currentItem = itemMap.get(itemSelection);
            currentItem.setNoun(getNoun());
            currentItem.setVerb(getVerb());
            use();
        } else {
            itemSelection();
        }

//        System.out.println(currentItem.getName() + " " + currentItem.getDescription());
    }

    public void use() {
        switch (getNoun()) {
            case "CD":
                useCD();
                break;
            case "PICTURE":
            case "ACTION FIGURE":
                useGenericItem();
                break;
            default:
                System.out.println("\ninvalid input try again");
                // input();
        }

    }


    public void useCD() {
        switch (getVerb()) {
            case "LOOK AT":
            case "EXAMINE":
            case "VIEW":
            case "DESCRIBE":
                System.out.println("\n" + currentItem.getDescription());
                break;
            case "PLAY":
            case "LISTEN TO":
                musicPlayer.setSong(currentItem.getName());
                musicPlayer.run();
                musicPlayer.musicMenu();

                break;
            case "STOP":
                musicPlayer.stopMusic();
                break;
            default:
                System.out.println("\nYou can not do that action with " + getNoun());
        }
    }

    public void useGenericItem() {
        switch (getVerb()) {
            case "LOOK AT":
            case "EXAMINE":
            case "VIEW":
            case "DESCRIBE":
                System.out.println(currentItem.getDescription());
                break;
            case "MOVE":
            case "PICK UP":
            case "LIFT":
                if (!currentItem.getHasClue().equals("false")) {
                    System.out.println("\nyou have added one " + currentItem.getHasClue());
                    traveler.addItem(currentItem.getHasClue());
                } else {
                    System.out.println("\nWhen you " + getVerb() + getNoun() + " Nothing was there");
                }
                break;
            default:
                System.out.println("\nYou can not do that action with " + getNoun());
        }
    }
    
    public void validateInput(){
        if (getSplitting().length == 2) {
            setVerb(getSplitting()[0]);
            setNoun(getSplitting()[1]);
        }else if(validPrepositions.contains(getSplitting()[1])){
            setVerb(getSplitting()[0] + " " + getSplitting()[1]);
            setNoun(getSplitting()[2]);
        }else{
            setVerb(getSplitting()[0]);
            setNoun(getSplitting()[1] + " " + getSplitting()[2]);
        }
    }
}
