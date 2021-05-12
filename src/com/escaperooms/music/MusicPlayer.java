package com.escaperooms.music;

import java.net.URL;
import java.util.Scanner;
import javax.sound.sampled.*;


public class MusicPlayer extends Thread{
    String song;
    Clip clip;

    public MusicPlayer() {
    }

    public MusicPlayer(String song) {
        this.song = song;
    }
    public void run() {
        try {
            URL url = MusicPlayer.class.getResource("/resources/" + song + ".wav");
            AudioInputStream audioStream;
            audioStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            musicControls();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
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

    public Clip getClip() {
        return this.clip;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public void musicControls(){
        clip.start();
    }
}