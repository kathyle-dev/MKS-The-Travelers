package com.escaperooms.application;

import com.escaperooms.music.MusicPlayer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    private static final int DIAMETER = 50;
    private String name;
    private String description;
    private String itemType;
    private String hasClue;
    private String userInput;
    private int x;
    private int y;
    private MusicPlayer musicPlayer;
    public Icon icon;
    private String filePath;
    private String verb;
    private String noun;


    public Item() {
    }

    @JsonCreator
    public Item(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("itemType") String itemType,
                @JsonProperty("hasClue") String hasClue,
                @JsonProperty("x") int x,
                @JsonProperty("y") int y,
                @JsonProperty("filePath") String filePath) {

        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.hasClue = hasClue;
        this.x = x;
        this.y = y;
        this.filePath = filePath;
        this.icon = (new ImageIcon(filePath));
    }

    public void paint(Graphics2D g) {
        switch (itemType) {
            case "CD":
                g.drawImage((new ImageIcon("src/resources/pictures/cd.jpg").getImage()), x, y, null);
                break;
            case "Picture":
                g.drawImage((new ImageIcon("src/resources/pictures/blankFrame.png").getImage()), x, y, null);
                break;
            case "Action Figure":
                g.drawImage((new ImageIcon("src/resources/pictures/ActionFigureDisplayCase.jpg").getImage()), x, y, null);
                break;
            default:
                g.fillOval(x, y, DIAMETER, DIAMETER);
                break;
        }

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

    public String getFilepath() {
        return filePath;
    }

    public void setFilepath(String filepath) {
        this.filePath = filepath;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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