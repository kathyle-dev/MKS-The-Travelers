package com.escaperooms.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.simple.JSONObject;
import com.escaperooms.puzzles.*;

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

    /*
     * Inner class - only used by Puzzle
     */

    public class Door{
        private String destination;
        private List<String> solution;

        public Door(String destination, String solutionObj){
            this.destination = destination;
            this.solution = Arrays.stream(solutionObj.split(" : ")).collect(Collectors.toList());
        }

        public String getDestination() {
            return destination;
        }

        public List<String> getSolution() {
            return solution;
        }


    }


}