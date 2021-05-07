package com.escaperooms.application;

import com.escaperooms.crazystans.CrazyStans;
import com.escaperooms.joninexams.JoninExams;
import com.escaperooms.spaceodyssey.SpaceOdyssey;
import com.escaperooms.spaceodyssey.Trivia;

import java.io.*;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;

public class EscapeRoom implements EscapeRoomInterface {
    private static final EscapeRoomPrompter escapeRoomPrompter = new EscapeRoomPrompter();
    private List<ThemeRoom> gameList = new ArrayList<>();
    private AdventureParser parser = new AdventureParser();
    public Map<String, Trivia> trivia;
    private static EscapeRoom me;

    public EscapeRoom() throws IOException {
        this.gameList = this.load();
//        this.trivia = this.loadTrivia();
    }

    public List<ThemeRoom> load() {
        List<ThemeRoom> allThemes = new ArrayList<>();
        try {
            InputStream room = getClass().getResourceAsStream("/resources/data/RoomData.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(room));
            reader.lines().forEach(roomData -> {
                File data = new File(roomData);
                try {
                    allThemes.add(parser.parse(data));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("In Escape Roam loader");
        }
        return allThemes;
    }

//    public Map<String, Trivia> loadTrivia(){
//
//        Map<String, Trivia> allTrivia = new HashMap<>();
//        try {
//            InputStream in = getClass().getResourceAsStream("/resources/data/Trivia.csv");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            reader.lines().forEach(triviaQuestion -> {
//                String[] questionParam = triviaQuestion.split(" : ");
//                String questionName = questionParam[0];
//                String question = questionParam[1];
//                String correctAnswer = questionParam[2];
//                String answerA = questionParam[3];
//                String answerB = questionParam[4];
//                String answerC = questionParam[5];
//                String answerD = questionParam[6];
//                Trivia triva = new Trivia(questionName,question,correctAnswer,answerA,answerB,answerC,answerD);
//                allTrivia.put(questionName,triva);
//            });
//        } catch(Exception e) {
//            System.out.println(e);
//        }
//        return allTrivia;
//    }

//    public Puzzle generateRoom(JSONObject game, String puzzleName){
//        JSONObject puzzleObj = (JSONObject) game.get(puzzleName);
//        Puzzle puzzle = new Puzzle(puzzleObj, puzzleName);
//        return puzzle;
//    }


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
                me.quitGame();
        }
        return input;
    }

    private void quitGame() {
        System.out.println("Quitting game...");
        System.exit(0);
    }

    @Override
    public String getName() {
        return null;
    }

    public static void main(String[] args){
        try {
            EscapeRoom escapeRoom = new EscapeRoom();
            List<ThemeRoom> rooms = escapeRoom.getGameList();
            rooms.forEach(room -> System.out.println(room.getName()));
        }catch (Exception e){
            System.out.println("The main is not working");

        }
    }
}
