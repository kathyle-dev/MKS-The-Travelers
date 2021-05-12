package com.escaperooms.application;

import com.escaperooms.music.MusicPlayer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;
import java.util.Scanner;

public class Item {
    private String name;
    private String description;
    private String itemType;
    private String hasClue;
    private String userInput;
    private MusicPlayer musicPlayer;
    Scanner scanner = new Scanner(System.in);


    private String verb;
    private String noun;
    private String[] splitting;

    public Item() {

    }

    @JsonCreator
    public Item(@JsonProperty("name") String name,
                @JsonProperty("description") String description,
                @JsonProperty("itemType") String itemType,
                @JsonProperty("hasClue") String hasClue) {

        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.hasClue = hasClue;
    }


    public void use() {
        switch (getNoun()) {
            case "cd":
                useCD();
                break;
            case "picture":
            case "actionfigure":
                useGenericItem();
                break;
            default:
                System.out.println("invalid input try again");
               // input();
        }

    }


    public void useCD() {
        switch (getVerb()) {
            case "look at":
            case "examine":
            case "view":
            case "describe":
                this.getDescription();
                break;
            case "play":
            case "listen to":
                // musicPlayer.run();
                break;
            case "stop":
                musicPlayer.stopMusic();
            break;
            default:
                System.out.println("You can not do that action with "+ getNoun());
        }
    }

    public void useGenericItem() {
        switch (getVerb()) {
            case "look at":
            case "examine":
            case "view":
            case "describe":
                this.getDescription();
                break;
            case "move":
            case "pick up":
            case "lift":
                if(!getHasClue().equals("false")) {
                    System.out.println("you have added one " + this.getHasClue());

                }else{
                    System.out.println("When you "+ getVerb() + getNoun()+ " Nothing was there");
                }
                break;
            default:
                System.out.println("You can not do that action with "+ getNoun());

        }

    }


//    public void input() {
//        System.out.println("What would you like to do");
//        setUserInput(scanner.nextLine().toLowerCase(Locale.ROOT));
//        splitUserInput();
//    }
//
//
//    public void splitUserInput() {
//        setSplitting(getUserInput().split("\\s"));
//        if (getSplitting().length == 2) {
//            setVerb(getSplitting()[0]);
//            setNoun(getSplitting()[1]);
//            use();
//        } else if (getSplitting().length == 3) {
//            setVerb(getSplitting()[0] + " " + getSplitting()[1]);
//            setNoun(getSplitting()[2]);
//            use();
//        } else {
//            input();
//        }
//
//
//    }




   //Getters and Setters
    public String getDescription() {
        return this.description;
    }

    public String getItemType() {
        return itemType;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
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

    public String[] getSplitting() {
        return splitting;
    }

    public void setSplitting(String[] splitting) {
        this.splitting = splitting;
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