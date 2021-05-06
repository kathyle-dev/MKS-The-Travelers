package com.escaperooms.puzzles;

import com.escaperooms.application.Item;
import com.escaperooms.music.MusicPlayer;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;
public class CD extends Item {
    private String song;
    private MusicPlayer musicPlayer;



    public CD(){
        this.song = getName() + ".wav";
        this.musicPlayer = new MusicPlayer(song);
    }

    public CD(String description, String itemType){
        this();
        setDescription(description);
        setItemType(itemType);
    }

    @Override
    public void use(String command) {
        switch (command){
            case "look at":
            case "examine":
            case "view":
                getDescription();
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


}
