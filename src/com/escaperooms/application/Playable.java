package com.escaperooms.application;

public class Playable {

    private String name;
    private String message;
    private ThemeRoom themeRoom;
    private boolean isCompleted = false;

    public Playable(String name, String message, ThemeRoom themeRoom) {
        setEscapeRoom(themeRoom);
        setMessage(message);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ThemeRoom getEscapeRoom() {
        return themeRoom;
    }

    public void setEscapeRoom(ThemeRoom themeRoom) {
        this.themeRoom = themeRoom;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }
}