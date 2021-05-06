package com.escaperooms.puzzles;

import com.escaperooms.application.Item;

public class Picture extends Item {



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
