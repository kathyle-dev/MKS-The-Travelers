package com.escaperooms.application;

import java.io.*;

import java.util.*;

public class EscapeRoomGame implements EscapeRoomInterface {
    private static final EscapeRoomPrompter escapeRoomPrompter = new EscapeRoomPrompter();
    private Map<String, Adventure> gameMap;
    private AdventureParser parser = new AdventureParser();
//    public Map<String, Trivia> trivia; from previous source code
    private Adventure currentAdventure;

    // default path to csv file
    public EscapeRoomGame() throws IOException {
        this.gameMap = this.load("/resources/data/GameList.csv");// has paths to JSON files
//        this.trivia = this.loadTrivia();
    }

    public EscapeRoomGame(String path) throws IOException {
        this.gameMap = this.load(path);
    }

    public Map<String, Adventure> load(String path) {
        Map<String, Adventure> allThemes = new HashMap<>();
        try {
            InputStream room = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(room));
            reader.lines().forEach(roomData -> {
                String[] gameDataCells = roomData.split("~");
                String gameName = gameDataCells[0];
                String roomJSON = gameDataCells[1];
                File data = new File(roomJSON);
                try {
                    ThemeRoom themeRoom = parser.parse(data);
                    Map<String, ThemeRoom> singleThemeRoom = new HashMap<>();
                    singleThemeRoom.put(themeRoom.getName(), themeRoom);
                    Adventure adventure = new Adventure(gameName, singleThemeRoom);
                    if(!allThemes.containsKey(gameName)){
                        allThemes.put(gameName, adventure);
                        System.out.println("after all themes");
                    }else{
                        allThemes.get(gameName).addToAdventureMap(themeRoom.getName(), themeRoom);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("Error In Escape Room Loader\n");
        }
        return allThemes;
    }

    public void run(Traveler traveler, Adventure adventure, boolean isGUI) {
        this.currentAdventure = adventure;
        if(isGUI){
            currentAdventure.run(traveler, isGUI);
        }else {
            while (!isCurrentAdventureCompleted()) {
                traveler.clearInventory();
                currentAdventure.run(traveler, isGUI);
                currentAdventure.setCompleted(true);
            }
        }
    }

    public Boolean isCurrentAdventureCompleted(){
        Collection<ThemeRoom> currentThemesList = getCurrentAdventure().getAdventureMap().values();
        for (ThemeRoom currentTheme : currentThemesList) {
            if (!currentTheme.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    public static String prompt(String message, String regex, String errorMessage) {
        String customRegex = "|quit|" + regex;
        String input = escapeRoomPrompter.getPrompt(message, customRegex, errorMessage);
        // check for global commands
        switch (input) {
            case "quit":
                quitGame();
        }
        return input;
    }

    private static void quitGame() {
        System.out.println("\nQuitting game...");
        System.exit(0);
    }

    @Override
    public String getName() {
        return null;
    }

    public Adventure getCurrentAdventure() {
        return currentAdventure;
    }

    public void setCurrentAdventure(Adventure currentAdventure) {
        this.currentAdventure = currentAdventure;
    }

    public Map<String, Adventure> getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map<String, Adventure> gameMap) {
        this.gameMap = gameMap;
    }
}
