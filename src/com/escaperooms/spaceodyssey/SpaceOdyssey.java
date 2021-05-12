//package com.escaperooms.spaceodyssey;
//
//import com.escaperooms.application.*;
//
//import java.util.Random;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//import com.escaperooms.application.Room;
//import com.escaperooms.music.MusicPlayer;
//
//import java.util.regex.*;
//
//public class SpaceOdyssey extends EscapeRoom {
//    boolean running = true;
//    Map<String, Room> innerRooms = new HashMap<>();
//    Traveler traveler;
//
//
//    public SpaceOdyssey () throws IOException {
//        super();
//    }
//
//    public Playable playable()  throws IOException {
//        return new Playable(getName(), "Welcome to Space Odyssey", new SpaceOdyssey());
//    }
//
//    @Override
//    public void run(Traveler traveler, EscapeRoom escapeRoom) {
//        this.running = true;
//        this.traveler = traveler;
//        User user = traveler.getUser();
//        user.move("COCK PIT");
//        System.out.println("The phasing between timelines is the worst part. Nausea, Confusion, but sometimes new powers!\nFor some reason I can hear howls in my head, is it coming from that [Dog]?");
//        Prompter prompter = new Prompter(new Scanner(System.in));
//        //Start Scene//
//        while (true){
//            if(!running){
//                break;
//            }
//            describeRoom(user.getCurrentRoom(), escapeRoom);
//            Room userCurrentRoom = escapeRoom.innerRooms.get("Space Odyssey : "+user.getCurrentRoom());
//
//            String inputAction = prompter.prompt("What do you want to do?\n\uD83D\uDE80").toUpperCase();
//            String[] choice = inputAction.split(" ");
//            switch(choice[0]){
//                case "INVENTORY" : traveler.getUser().showInventory(); prompter.prompt("Press Enter to Continue"); break;
//                case "HELP":
//                    System.out.println("Commands start with a <verb> and end with a [noun]\nYou can :\n<go> to [PLACES]\n<talk> to [People]\n<use> [items]\netc...\n'quit' to exit the game"); prompter.prompt("Press Enter to Continue\n"); break;
//                case "TAKE":
//                case "GRAB":
//                case "STEAL":
//                case "GET": if(userCurrentRoom.hasItem(choice[1])){
//                    user.addItem(choice[1]);
//                    userCurrentRoom.removeItem(choice[1]);
//                }else{
//                    System.out.println("Can't get "+choice[1]);
//                } prompter.prompt("Press Enter to Continue\n"); break;
//                case "CLIMB":
//                case "SWIM":
//                case "WALK":
//                case "RUN":
//                case "CRAWL":
//                case "JUMP":
//                case "SLITHER":
//                case "FLY":
//                case "HOP":
//                case "SKIP":
//                case "GO":
//                    if(choice.length>=2){
//                        String room;
//                        if (choice[1].equals("TO")){
//                            room = inputAction.substring(choice[0].length() + 4);
//                        }else{
//                            room = inputAction.substring(choice[0].length() + 1);
//                        }
//                        if(userCurrentRoom.hasDoor(room)){
//                            user.move(room);
//                        }else{
//                            System.out.println("Can't "+inputAction);
//                        }
//                    } prompter.prompt("Press Enter to Continue\n"); break;
//                case "THROW":
//                case "TRY":
//                case "PUSH":
//                case "PULL":
//                case "PUT":
//                case "KICK":
//                case "FIX":
//                case "CLEAN":
//                case "USE":
//                    if(choice.length>1 && userCurrentRoom.hasUsefulItem(choice[1]) && user.isItemInInventory(choice[1])){
//                        tryNarrate(choice[1],1);
//                        userCurrentRoom.removeUsefulItem(choice[1]);
//                        user.addAnswer(choice[1]);
//                    }else if(user.isCurrentRoom("POD") && choice[choice.length-1].equals("KEYPAD")){
//                        startKeypad();
//                    }else{
//                        System.out.println("Can't "+inputAction);
//                    } prompter.prompt("Press Enter to Continue\n"); break;
//                case "PRAISE":
//                case "FEED":
//                case "CALL":
//                case "RIDE":
//                case "TOUCH":
//                case "CHASE":
//                case "PLAY":
//                case "PET":
//                case "TALK":
//                    if(choice.length>1 && userCurrentRoom.hasActor(choice[choice.length-1])) {
//                        int randomInt = new Random().nextInt(5);
//                        boolean guess = escapeRoom.trivia.get(choice[choice.length - 1] + randomInt).ask();
//                        if(guess){
//                            System.out.println("Your minds are in sync!");
//                            if (user.isCurrentRoom("STOCK ROOM") && user.hasAnswer("MATCH")) {
//                                tryNarrate(choice[choice.length - 1], 2);
//                                user.addItem("Golden Spatula");
//                                userCurrentRoom.removeActors("DUCK");
//                                user.addAnswer("DUCK");
//                            }
//                            if (user.isCurrentRoom("STOCK ROOM") && !user.hasAnswer("MATCH")) {
//                                tryNarrate(choice[choice.length - 1], 1);
////                                tryPlayConvo(choice[choice.length - 1]);
//                            }
//                            if (user.isCurrentRoom("KITCHEN")) {
//                                tryNarrate(choice[choice.length - 1], 1);
//                                user.addItem("FishBowl");
//                                userCurrentRoom.removeActors("DUCKLING");
//                            }
//                            if (user.isCurrentRoom("COCK PIT") && user.hasAnswer("DUCK") && user.hasAnswer("FISH") && user.hasAnswer("CAT") && user.hasAnswer("HORSE")) {
//                                tryNarrate(choice[choice.length - 1], 2);
//                            }
//                            if (user.isCurrentRoom("COCK PIT") && (!user.hasAnswer("DUCK") || !user.hasAnswer("FISH") || !user.hasAnswer("CAT") || !user.hasAnswer("HORSE"))) {
//                                tryNarrate(choice[choice.length - 1], 1);
//                            }
//                            if (user.isCurrentRoom("ENGINE ROOM") && !user.isItemInInventory("WRENCH")) {
//                                tryNarrate(choice[choice.length - 1], 1);
//                            }
//                            if (user.isCurrentRoom("ENGINE ROOM") && user.isItemInInventory("WRENCH")) {
//                                tryNarrate(choice[choice.length - 1], 2);
//                                user.removeItem("WRENCH");
//                                userCurrentRoom.removeActors("HORSE");
//                                user.addAnswer("HORSE");
//                            }
//                            if (user.isCurrentRoom("POD") && !user.getInventory().contains("LASER")) {
//                                if(!escapeRoom.innerRooms.get("Space Odyssey : DORMS").hasItem("LASER")){
//                                    escapeRoom.innerRooms.get("Space Odyssey : DORMS").addItem("LASER");
//                                }
//                                tryNarrate(choice[choice.length - 1], 1);
//                            }
//                            if (user.isCurrentRoom("POD") && user.isItemInInventory("LASER")) {
//                                tryNarrate(choice[choice.length - 1], 2);
//                                user.removeItem("LASER");
//                                user.addAnswer("CAT");
//                                userCurrentRoom.removeActors("CAT");
//                            }
//                            if (user.isCurrentRoom("ESCAPE HATCH") && !user.isItemInInventory("FishBowl")) {
//                                tryNarrate(choice[choice.length - 1], 1);
//                            }
//                            if (user.isCurrentRoom("ESCAPE HATCH") && user.isItemInInventory("FishBowl")) {
//                                tryNarrate(choice[choice.length - 1], 2);
//                                user.removeItem("FishBowl");
//                                userCurrentRoom.removeActors("FISH");
//                                user.addAnswer("FISH");
//                            }
//                        }else{
//                            System.out.println("Your minds aren't in sync. Try again!");
//                            tryNarrate(choice[choice.length - 1], 3);
//                        }
//                    }else{
//                        System.out.println("Can't "+inputAction);
//                    }
//                    prompter.prompt("Press Enter to Continue\n"); break;
//                case "QUIT" :running = false; traveler.menu(); break;
//                default:
//                    System.out.println("I don't understand that command :"+inputAction); prompter.prompt("Press Enter to Continue\n"); break;
//            }
//
//        }
//    }
//
//    @Override
//    public void terminate() {
//
//    }
//
//    void tryPlayConvo(String convo){
//        try{
//            new MusicPlayer(convo+"0.wav").start();
//        }catch(Exception e){
//            System.out.println("I don't understand "+convo+" or maybe I can't find my dictionary");
//        }
//    }
//
//    void tryPlayConvo(String convo,int index){
//        try{
//            new MusicPlayer(convo+index+".wav").start();
//        }catch(Exception e){
//            System.out.println("I don't understand "+convo+" or maybe I can't find my dictionary");
//        }
//    }
//
//    void tryNarrate(String item){
//        try{
//            new Narrator().spaceTalk(item);
//        }catch(IOException io){
//            System.out.println("I don't understand "+item+" or maybe I can't find my dictionary");
//        }
//    }
//
//    void tryNarrate(String item, int convo){
//        try{
//            new Narrator().spaceTalk(item,convo);
//        }catch(IOException io){
//            System.out.println("I don't understand "+item+" or maybe I can't find my dictionary");
//        }
//    }
//
//    public void describeRoom (String currentRoom, EscapeRoom escapeRoom) {
//        tryNarrate(currentRoom);
//        Room userCurrentRoom = escapeRoom.innerRooms.get("Space Odyssey : "+currentRoom);
//        for(String item : userCurrentRoom.getItems()){
//            if(item.length()>1){
//                tryNarrate(item,0);
//            }
//        }
//        for(String actor : userCurrentRoom.getActors()){
//            if(actor.length()>1){
//                tryNarrate(actor,0);
//            }
//        }
//        for(String item : userCurrentRoom.getUsefulItems()){
//            if(item.length()>1){
//                tryNarrate(item,2);
//            }
//        }
//        if(userCurrentRoom.getDoors().size()==1){
//            System.out.println("You can go to "+userCurrentRoom.getDoors()+ ".");
//        }
//        if(userCurrentRoom.getDoors().size()>1){
//            System.out.println("You can go to "+userCurrentRoom.getDoors().subList(0,userCurrentRoom.getDoors().size()-1)+ " or ["+userCurrentRoom.getDoors().get(userCurrentRoom.getDoors().size()-1)+"].");
//        }
//
//    }
//
//    public void startKeypad(){
//        Gui keypad = new Gui();
//        System.out.println("You tried to enter the code.");
//        new Prompter(new Scanner(System.in)).prompt("Press Enter to continue!\n");
//        if(keypad.getCode().equals("4982")){
//            System.out.println("You got the right code and the pod launches you into a different timeline.");
//            traveler.menu();
//            running = false;
//        }else{
//            System.out.println("You put in the wrong code: "+keypad.getCode());
//        }
//    }
//
//    @Override
//    public String getName() {
//        return "Space Odyssey";
//    }
//
//
//}
