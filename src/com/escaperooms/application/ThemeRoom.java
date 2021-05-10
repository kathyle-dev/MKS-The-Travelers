package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ThemeRoom {

    @JsonProperty("name")
    private String name;
    @JsonProperty("puzzles")
    private Map<String, Puzzle> puzzles;
    @JsonProperty("nextTheme")
    private String nextTheme;
    private Puzzle currentPuzzle;
    private boolean isCompleted = false;

    @JsonCreator
    public ThemeRoom(@JsonProperty("name") String name, @JsonProperty("puzzles") Map<String, Puzzle> puzzles, @JsonProperty("nextTheme") String nextTheme){
        this.name = name;
        this.puzzles = puzzles;
        this.nextTheme = nextTheme;
        this.currentPuzzle = puzzles.get("puzzle1");
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
}