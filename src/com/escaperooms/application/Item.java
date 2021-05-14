package com.escaperooms.application;

import com.escaperooms.music.MusicPlayer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.*;

public class Item {
    private static final int DIAMETER = 20;
    private String name;
    private String description;
    private String itemType;
    private String hasClue;
    private String userInput;
    private int x;
    private int y;
    private MusicPlayer musicPlayer;


    private String verb;
    private String noun;

    public Item() {}

    @JsonCreator
    public Item(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("itemType") String itemType,
                @JsonProperty("hasClue") String hasClue,
                @JsonProperty("x") int x,
                @JsonProperty("y") int y) {

        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.hasClue = hasClue;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g) {
        g.fillOval(x, y, DIAMETER, DIAMETER);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, DIAMETER, DIAMETER);
    }



   //Getters and Setters
    public String getDescription() {
        return this.description;
    }


    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getHasClue() {
        return hasClue;
    }

    public void setHasClue(String hasClue) {
        this.hasClue = hasClue;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hasClue='" + hasClue + '\'' +
                ", verb='" + verb + '\'' +
                ", noun='" + noun + '\'' +
                '}';
    }
}