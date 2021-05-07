package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle {
    @JsonProperty("name")
    private String name;
    @JsonProperty("items")
    private List<Item> items;
    @JsonProperty("door")
    private Door door;


    /*
     * GETTER'S AND SETTERS
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Door getDoor() {
        return this.door;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}