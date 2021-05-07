package com.escaperooms.puzzles;

import com.escaperooms.application.Item;

public class Picture extends Item {

    private String name;
    private String description;
    private String hasClue;

    Picture(){
        this.name = name;
        this.description = description
    }


    @Override
    public void use(String command) {
        switch (command){
            case "look at":
            case "examine":
            case "view":
                getDescription();
                break;
        }
    }
}
