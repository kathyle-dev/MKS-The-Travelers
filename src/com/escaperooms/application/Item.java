package com.escaperooms.application;

import com.escaperooms.music.MusicPlayer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Scanner;

public class Item {
    private String name;
    private String description;
    private String itemType;
    private String hasClue;
    private String userInput;
    private MusicPlayer musicPlayer;
    Scanner scanner = new Scanner(System.in);

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


    }


    public void useCD() {
        switch (getUserInput()) {
            case "look at":
            case "examine":
            case "view":
                ;
                break;
            case "play":
            case "listen":
                musicPlayer.run();
                break;
            case "stop":
                musicPlayer.stopMusic();
            default:
                System.out.println("You have entered an invalid Command");
        }
    }

        public String getDescription () {
            return this.description;
        }

        public String getItemType () {
            return itemType;
        }

        public String getName () {
            return name;
        }

        public void setDescription (String description){
            this.description = description;
        }

        public void setItemType (String itemType){
            this.itemType = itemType;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getHasClue () {
            return hasClue;
        }

        public void setHasClue (String hasClue){
            this.hasClue = hasClue;
        }

        //}
//
//    public String getUserInput() {
//        return userInput;
//    }


    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}