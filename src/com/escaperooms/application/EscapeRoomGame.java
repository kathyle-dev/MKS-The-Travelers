package com.escaperooms.application;

import com.escaperooms.crazystans.CrazyStans;
import com.escaperooms.joninexams.JoninExams;
import com.escaperooms.spaceodyssey.SpaceOdyssey;
import com.escaperooms.spaceodyssey.Trivia;

import java.io.*;

import java.util.*;

public class EscapeRoomGame implements EscapeRoomInterface {
    private static final EscapeRoomPrompter escapeRoomPrompter = new EscapeRoomPrompter();
    private Map<String, ThemeRoom> gameMap;
    private AdventureParser parser = new AdventureParser();
    public Map<String, Trivia> trivia;
    private ThemeRoom currentTheme;
    private boolean isCompleted = false;

    // default path to csv file
    public EscapeRoomGame() throws IOException {
        this.gameMap = this.load("/resources/data/RoomData.csv");// has paths to JSON files
//        this.trivia = this.loadTrivia();
    }

    public EscapeRoomGame(String path) throws IOException {
        this.gameMap = this.load(path);
    }

    public Map<String, ThemeRoom> load(String path) {
        Map<String, ThemeRoom> allThemes = new HashMap<>();
        try {
            InputStream room = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(room));
            reader.lines().forEach(roomData -> {
                File data = new File(roomData);
                try {
                    ThemeRoom themeRoom = parser.parse(data);
                    allThemes.put(themeRoom.getName(), themeRoom);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("In Escape Roam loader\n");
        }
        return allThemes;
    }

    public void run(Traveler traveler, ThemeRoom room, boolean isGUI) {
        this.currentTheme = room;
        if(isGUI){
            currentTheme.run(traveler, isGUI);
        }else {
            while (!isCompleted) {
                traveler.clearInventory();
                currentTheme.run(traveler, isGUI);
                getNextThemeRoom();
            }
        }
    }

    public void getNextThemeRoom(){
        String roomName = currentTheme.getNextTheme();
        if(roomName.equalsIgnoreCase("none")){
            isCompleted = true;
        }else{
            this.currentTheme = gameMap.get(roomName);
        }
    }

    public Map<String, ThemeRoom> getGameMap() {
        return gameMap;
    }

    public void setGameMap(Map<String, ThemeRoom> gameMap) {
        this.gameMap = gameMap;
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

    public ThemeRoom getCurrentTheme() {
        return currentTheme;
    }
}
