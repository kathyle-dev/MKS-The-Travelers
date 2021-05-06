package com.escaperooms.application;

import com.escaperooms.crazystans.CrazyStans;
import com.escaperooms.joninexams.JoninExams;
import com.escaperooms.spaceodyssey.SpaceOdyssey;
import com.escaperooms.spaceodyssey.Trivia;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;

public class EscapeRoom implements EscapeRoomInterface {
    private static final EscapeRoomPrompter escapeRoomPrompter = new EscapeRoomPrompter();
    Map<String, Playable> escapeRooms = new HashMap<>();
    public Map<String, Puzzle> innerRooms;
    public Map<String, Trivia> trivia;
    private static EscapeRoom me;

    public EscapeRoom() throws IOException {
        this.innerRooms = this.load();
//        this.trivia = this.loadTrivia();
    }

    public Map<String, Puzzle> load() {

        Map<String, Puzzle> allRooms = new HashMap<>();
        try {
            InputStream in = getClass().getResourceAsStream("/resources/data/test.json");
            InputStreamReader file = new InputStreamReader(in,
                    StandardCharsets.UTF_8);
            JSONObject data = (JSONObject) new JSONParser().parse(file);

            Iterator keys = data.keySet().iterator();

            while(keys.hasNext()){
                String gameName = (String) keys.next();
                JSONObject game = (JSONObject) data.get(gameName);
                List puzzles = (List) game.get("puzzles");
                for(int i = 0; i< puzzles.size(); i++){
                    String roomName = (String) puzzles.get(i);
                    Puzzle currentPuzzle = generateRoom(game, roomName);
                    allRooms.put(gameName + " : " + roomName, currentPuzzle);
                }
            }

//            InputStream in = getClass().getResourceAsStream("/resources/data/RoomData.csv");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            reader.lines().forEach(roomData -> {
//                String[] roomDataCells = roomData.split(" : ");
//                String gameName = roomDataCells[0];
//                String roomName = roomDataCells[1];
//                List<String> roomItems = Arrays.stream(roomDataCells[2].split(" ~ ")).collect(Collectors.toList());
//                List<String> roomUsefulItems = Arrays.stream(roomDataCells[3].split(" ~ ")).collect(Collectors.toList());
//                List<String> actorNames = Arrays.stream(roomDataCells[4].split(" ~ ")).collect(Collectors.toList());
//                List <String> doors = Arrays.stream(roomDataCells[5].split(" ~ ")).collect(Collectors.toList());
//
//                Room currentRoom = new Room(roomName, roomItems, roomUsefulItems, actorNames,doors);
//                allRooms.put(gameName + " : " + roomName, currentRoom);
//            });
        } catch(Exception e) {
            System.out.println(e);
            System.out.println("In load");
        }
        return allRooms;


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

    public Puzzle generateRoom(JSONObject game, String roomName){
        JSONObject roomObj = (JSONObject) game.get(roomName);
        Puzzle puzzle = new Puzzle(roomName, (List<JSONObject>) roomObj.get("items"), (List<JSONObject>) roomObj.get("doors"));
        return puzzle;
    }

    public void testPrintRoomInfo(){
        Collection<Puzzle> puzzles = innerRooms.values();
        Puzzle puzzle2 = (Puzzle) puzzles.toArray()[1];
        Puzzle puzzle1 = (Puzzle) puzzles.toArray()[0];
        System.out.println("ROOM 1" + puzzle1.getDoors());
        System.out.println("ROOM 2" + puzzle2.getDoors());
    }

    public void generateEscapeRooms(EscapeRoom room) throws IOException {
        me = room;
        SpaceOdyssey spaceOdyssey = new SpaceOdyssey();
        CrazyStans crazyStans = new CrazyStans();
        JoninExams joninExams = new JoninExams();
        escapeRooms.put(spaceOdyssey.getName(), spaceOdyssey.playable());
        escapeRooms.put(crazyStans.getName(), crazyStans.playable());
        escapeRooms.put(joninExams.getName(), joninExams.playable());
    }

    public Map<String, Playable> getEscapeRooms() {
        return escapeRooms;
    }


    public Playable getEscapeRoomPlayable(String room) {
        Map<String, Playable> playables = getEscapeRooms();
        return playables.get(room);
    }

    public void removeEscapeRoom(String room) {
        escapeRooms.remove(room);
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
