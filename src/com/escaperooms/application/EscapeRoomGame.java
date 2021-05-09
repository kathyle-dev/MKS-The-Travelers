package com.escaperooms.application;

import com.escaperooms.crazystans.CrazyStans;
import com.escaperooms.joninexams.JoninExams;
import com.escaperooms.spaceodyssey.SpaceOdyssey;
import com.escaperooms.spaceodyssey.Trivia;

import java.io.*;

import java.util.*;

public class EscapeRoomGame implements EscapeRoomInterface {
    private static final EscapeRoomPrompter escapeRoomPrompter = new EscapeRoomPrompter();
    private List<ThemeRoom> gameList;
    private AdventureParser parser = new AdventureParser();
    public Map<String, Trivia> trivia;

    // default path to csv file
    public EscapeRoomGame() throws IOException {
        this.gameList = this.load("/resources/data/RoomData.csv");// has paths to JSON files
//        this.trivia = this.loadTrivia();
    }

    public EscapeRoomGame(String path) throws IOException {
        this.gameList = this.load(path);
    }

    public List<ThemeRoom> load(String path) {
        List<ThemeRoom> allThemes = new ArrayList<>();
        try {
            InputStream room = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(room));
            reader.lines().forEach(roomData -> {
                File data = new File(roomData);
                try {
                    ThemeRoom themeRoom = parser.parse(data);
                    allThemes.add(themeRoom);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("In Escape Roam loader");
        }
        return allThemes;
    }


//
//    public void generateEscapeRooms(EscapeRoom room) throws IOException {
//        me = room;
//        SpaceOdyssey spaceOdyssey = new SpaceOdyssey();
//        CrazyStans crazyStans = new CrazyStans();
//        JoninExams joninExams = new JoninExams();
//        escapeRooms.put(spaceOdyssey.getName(), spaceOdyssey.playable());
//        escapeRooms.put(crazyStans.getName(), crazyStans.playable());
//        escapeRooms.put(joninExams.getName(), joninExams.playable());
//    }
//
//    public Map<String, Playable> getEscapeRooms() {
//        return escapeRooms;
//    }

//
//    public Playable getEscapeRoomPlayable(String room) {
//        Map<String, Playable> playables = getEscapeRooms();
//        return playables.get(room);
//    }
//
//    public void removeEscapeRoom(String room) {
//        escapeRooms.remove(room);
//    }

    public List<ThemeRoom> getGameList() {
        return gameList;
    }

    public void setGameList(List<ThemeRoom> gameList) {
        this.gameList = gameList;
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
        System.out.println("Quitting game...");
        System.exit(0);
    }

    @Override
    public String getName() {
        return null;
    }

}
