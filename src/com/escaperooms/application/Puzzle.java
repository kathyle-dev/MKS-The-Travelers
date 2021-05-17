package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle {
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("items")
    private Map<String, Map<String, Item>> items;
    @JsonProperty("door")
    private Door door;
    private boolean isCompleted;
    @JsonProperty("hints")
    private ArrayList<String> hints;
    private String currentHint;
    private int currentHintIndex = 0;

    /*
     * GETTER'S AND SETTERS
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Door getDoor() {
        return this.door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Map<String, Map<String, Item>> getItems() {
        return items;
    }

    public void setItems(Map<String, Map<String, Item>> items) {
        this.items = items;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCurrentHint() {
        return hints.get(currentHintIndex);
    }

    public void setCurrentHint(String currentHint) {
        this.currentHint = currentHint;
    }

    public ArrayList<String> getHints() {
        return hints;
    }

    public int getCurrentHintIndex() {
        return currentHintIndex;
    }

    public void setCurrentHintIndex(int currentHintIndex) {
        this.currentHintIndex = currentHintIndex;
    }

    public String getAHint() {
        if(currentHintIndex == (hints.size()-1)) {
            setCurrentHintIndex(0);
        }

        return getCurrentHint();
    }
}