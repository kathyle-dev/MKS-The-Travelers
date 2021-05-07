package com.escaperooms.application;

import org.json.simple.JSONObject;
import com.escaperooms.puzzles.*;

import java.util.*;
import java.util.stream.Collectors;

public class Puzzle {
    String name;
    JSONObject data;
    HashMap<String, Item> items = new HashMap<>();
    Door door;

    public Puzzle(JSONObject puzzle, String puzzleName){
        this.name = puzzleName;
        this.data = puzzle;
        setItems();
        setDoor();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoor() {
        JSONObject doorObj = (JSONObject) data.get("door");
        this.door = new Door((String) doorObj.get("destination"), (String) doorObj.get("solution"));
    }

    public JSONObject getDoor() {
        return this.door;
    }

    public void addDoor(JSONObject door) {
        this.door.add(door);
    }

    public void removeDoor(JSONObject door) {
        this.door.remove(door);
    }

    public boolean hasDoor(String door) {
        return this.door.contains(door);
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public void setItems() {
        JSONObject itemsObj =(JSONObject) data.get("items");
        int counter =1;

        if(data.get("CD"){
            CD cd = new CD(data.get("description"),data.get("hasClue"));
                    items.put("CD",cd);//FIX MISS PLEASE :)
        }
        // check for matching item types aka itemsObjKeys
        // if they match create a new instance of that item type
        // then put it into items map ( key will be the itemtype + count, value = new item.)

        // create inner counter.

        switch(itemType){
            case "CD":
                int counter = 1
                        String key = "CD"+counter;
                        CD value = new CD();
                        itmes.put(key, value);
        }
    }

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



    public void description(){
        CD cd = new CD();
        cd.setDescription("This CD is about  Trap Music");
    }

}