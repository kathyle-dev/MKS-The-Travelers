package com.escaperooms.music;

import java.net.URL;
import java.util.Scanner;
import javax.sound.sampled.*;


public class MusicPlayer extends Thread{
    String song;
    Clip clip;
    Scanner scanner = new Scanner(System.in);
    String userInput;
    public MusicPlayer() {
    }

    public MusicPlayer(String song) {
        this.song = song;
    }
    public void run() {
        try {
            System.out.println("SONG " + song);
            URL url = MusicPlayer.class.getResource("/resources/" + song + ".wav");
            AudioInputStream audioStream;
            audioStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            System.out.println("Could not create the music clip");
        }
    }

    public void stopMusic() {
        clip.stop();
        this.interrupt();
    }

    public void waitToDie() {
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void musicMenu(){
        while (clip.isOpen()) {
            System.out.println("What would you like to do with this cd");
            userInput = scanner.nextLine();
            if(userInput.equals("start")||userInput.equals("stop") || userInput.equals("pause")){
                if (clip.isRunning()){
                    clip.stop();
                } else if(!clip.isRunning()){
                    clip.start();
                }
            } else if (userInput.equals("restart")){
                clip.setMicrosecondPosition(0);
            } else if(userInput.equals("exit")){
                clip.close();
            } else {
                System.out.println("Invalid command");
                musicMenu();
            }
        }
    }

    public void playPauseStop(){
        if (clip.isRunning()){
            clip.stop();
        } else if(!clip.isRunning()){
            clip.start();
        }
    }

    public void restart(){
        clip.setMicrosecondPosition(0);
    }

    public void exit(){
        if(clip != null){
            clip.close();
        }
    }


    public Clip getClip() {
        return this.clip;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void musicControls(){
        clip.start();
        musicMenu();
    }





}