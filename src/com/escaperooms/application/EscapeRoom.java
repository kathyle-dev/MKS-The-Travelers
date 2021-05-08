package com.escaperooms.application;

import com.escaperooms.crazystans.CrazyStans;
import com.escaperooms.joninexams.JoninExams;
import com.escaperooms.spaceodyssey.SpaceOdyssey;
import com.escaperooms.spaceodyssey.Trivia;

import java.io.*;

import java.util.*;

public class EscapeRoom implements EscapeRoomInterface {
    private static final EscapeRoomPrompter escapeRoomPrompter = new EscapeRoomPrompter();
    private List<ThemeRoom> gameList;
    private AdventureParser parser = new AdventureParser();
    public Map<String, Trivia> trivia;
    private static EscapeRoom me;

    // default path to csv file
    public EscapeRoom() throws IOException {
        this.gameList = this.load("/resources/data/RoomData.csv");// has paths to JSON files
//        this.trivia = this.loadTrivia();
    }

    public EscapeRoom(String path) throws IOException {
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

}
