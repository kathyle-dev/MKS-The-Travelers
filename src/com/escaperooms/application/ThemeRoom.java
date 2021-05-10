package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ThemeRoom {

    @JsonProperty("name")
    private String name;
    @JsonProperty("puzzles")
    private HashMap<String, Puzzle> puzzles;
    @JsonProperty("nextTheme")
    private String nextTheme;
    private Puzzle currentPuzzle;
    private boolean isCompleted = false;

    @JsonCreator
    public ThemeRoom(@JsonProperty("name") String name, @JsonProperty("puzzles") HashMap<String, Puzzle> puzzles, @JsonProperty("nextTheme") String nextTheme){
        this.name = name;
        this.puzzles = puzzles;
        this.nextTheme = nextTheme;
        this.currentPuzzle = puzzles.get(0);
    }

    public void run(Traveler traveler, ThemeRoom room) {
        System.out.println("You are in "+ getName());
        System.out.println("Here's your first puzzle:");
        System.out.println(currentPuzzle.getDescription());
        System.out.println("size: "+ puzzles.size());
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

    public HashMap<String, Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(HashMap<String, Puzzle> puzzles) {
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
}