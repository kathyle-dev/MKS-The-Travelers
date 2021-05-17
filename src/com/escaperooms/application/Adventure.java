package com.escaperooms.application;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Adventure {
    private String name;
    private Map<String, ThemeRoom> adventureMap;
    private ThemeRoom startingTheme;
    private ThemeRoom currentTheme;
    private boolean isCompleted = false;

    public Adventure() {
    }


    public Adventure(String name, Map<String, ThemeRoom> adventureMap) {
        this.name = name;
        this.adventureMap = adventureMap;
        setStartingTheme();
    }

    // Will start the escape room game.
    public void run(Traveler traveler, boolean isGUI) {
        this.currentTheme = startingTheme;
        if (isGUI) {
            currentTheme.run(traveler, isGUI);
        } else {
            while (!isAdventureCompleted()) {
                traveler.clearInventory();
                currentTheme.run(traveler, isGUI);
                currentTheme.setCompleted(true);
                getNextThemeRoom();
            }
        }
    }

    // grabs the next them room
    public void getNextThemeRoom() {
        String themeName = currentTheme.getNextTheme();
        this.currentTheme = adventureMap.get(themeName);
    }

    public boolean isAdventureCompleted() {
        Collection<ThemeRoom> themeList = adventureMap.values();
        for (ThemeRoom theme : themeList) {
            if (!theme.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    public void addToAdventureMap(String name, ThemeRoom room) {
        adventureMap.put(name, room);
    }

    public String getName() {
        return name;
    }

    public Map<String, ThemeRoom> getAdventureMap() {
        return adventureMap;
    }

    public void setAdventureMap(Map<String, ThemeRoom> adventureMap) {
        this.adventureMap = adventureMap;
    }

    public ThemeRoom getCurrentTheme() {
        return currentTheme;
    }

    public boolean isCompleted() {
        return isCompleted;
    }


    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public void setStartingTheme() {
        List<ThemeRoom> themeList = adventureMap.values().stream().collect(Collectors.toList());
        for (ThemeRoom room : themeList) {
            if (room.isStartingTheme()) {
                this.startingTheme = room;
            }
        }
    }

    @Override
    public String toString() {
        return "Adventure{" +
                "name='" + name + '\'' +
                ", adventureMap=" + adventureMap +
                ", startingTheme=" + startingTheme +
                ", currentTheme=" + currentTheme +
                ", isCompleted=" + isCompleted +
                '}';
    }
}