package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ThemeRoom {

    @JsonProperty("name")
    private String name;
    @JsonProperty("puzzles")
    private List<Puzzle> puzzles;
    @JsonProperty("nextTheme")
    private String nextTheme;

    private Puzzle currentPuzzle;
    private boolean isCompleted = false;

    @JsonCreator
    public ThemeRoom(@JsonProperty("name") String name, @JsonProperty("puzzles") List<Puzzle> puzzles, @JsonProperty("nextTheme") String nextTheme){
        this.name = name;
        this.puzzles = puzzles;
        this.nextTheme = nextTheme;
        this.currentPuzzle = puzzles.get(0);
    }

    public void run(Traveler traveler, ThemeRoom room) {
        System.out.println("You are in "+ getName());
        System.out.println("Here's your first puzzle:");
        System.out.println(currentPuzzle.getName());
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

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
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
}