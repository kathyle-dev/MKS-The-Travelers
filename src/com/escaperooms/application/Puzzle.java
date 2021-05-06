package com.escaperooms.application;

import org.json.simple.JSONObject;
import com.escaperooms.puzzles.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Puzzle {
    String name;
    Map<String, String> directions = new HashMap<>();
    List<JSONObject> items = new ArrayList<>();
    List<String> usefulItems = new ArrayList<>();
    List<String> actors = new ArrayList<>();
    List<JSONObject> doors = new ArrayList<>();

    ITem has itemType
    MAP == itemTYpe+count  : object


    public Puzzle(String name) {
        setName(name);
    }

    public Puzzle(String name, List<JSONObject> items) {
        this(name);
        setItems(items);
    }

    public Puzzle(String name, List<JSONObject> items, List<JSONObject> doors) {
        this(name, items);
        setDoors(doors);
    }

//    public Room(String name, List<String> items, List<String> usefulItems) {
//        this(name, items);
//        setUsefulItems(usefulItems);
//    }
//
//    public Room(String name, List<String> items, List<String> usefulItems, List<String> actors) {
//        this(name, items, usefulItems);
//        setActors(actors);
//    }
//
//    public Room(String name, List<String> items, List<String> usefulItems, List<String> actors, List<String> doors) {
//        this(name, items, usefulItems,actors);
//        setDoors(doors);
//    }

    public void setDoors(List<JSONObject> doors) {
        this.doors = doors;
    }

    public List<JSONObject> getDoors() {
        return this.doors;
    }

    public void addDoor(JSONObject door) {
        this.doors.add(door);
    }

    public void removeDoor(JSONObject door) {
        this.doors.remove(door);
    }

    public boolean hasDoor(String door) {
        return this.doors.contains(door);
    }

    public List<JSONObject> getItems() {
        return items;
    }

    public void setItems(List<JSONObject> items) {
        this.items = items;
    }

    public void removeItem(JSONObject item) {
        items.remove(item);
    }

    public List<String> getUsefulItems() {
        return usefulItems;
    }

    public void setUsefulItems(List<String> usefulItems) {
        this.usefulItems = usefulItems;
    }

    public void removeUsefulItem(String item) {
        usefulItems.remove(item);
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void removeActors(String actor) {
        actors.remove(actor);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasItem(String itemName) {
        return getItems().contains(itemName);
    }

    public void addItem(JSONObject item) {
        items.add(item);
    }

    public boolean hasUsefulItem(String itemName) {
        return getUsefulItems().contains(itemName);
    }

    public boolean hasActor(String actorName) {
        return getActors().contains(actorName);
    }

    public void description(){
        CD cd = new CD();
        cd.setDescription("This CD is about  Trap Music");
    }

}