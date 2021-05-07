package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ThemeRoom {

    @JsonProperty("name")
    private String name;
    @JsonProperty("puzzles")
    private List<Puzzle> puzzles;
    @JsonProperty("nextTheme")
    private String nextTheme;

    private int currentPuzzle;

    @JsonCreator
    public ThemeRoom(@JsonProperty("name") String name, @JsonProperty("puzzles") List<Puzzle> puzzles, @JsonProperty("nextTheme") String nextTheme){
        this.name = name;
        this.puzzles = puzzles;
        this.nextTheme = nextTheme;
        this.currentPuzzle = 0;
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

    public int getCurrentPuzzle() {
        return currentPuzzle;
    }

    public void setCurrentPuzzle(int currentPuzzle) {
        this.currentPuzzle = currentPuzzle;
    }
}