package com.escaperooms.client;

import com.escaperooms.application.EscapeRoomGame;
import com.escaperooms.application.Traveler;
import com.escaperooms.application.User;

class Client {

//    public static void main(String[] args) throws IOException {
//        AnsiConsole.systemInstall();
//        System.out.println(" _____                   _           \n" +
//                "|_   _|                 | |          \n" +
//                "  | |_ __ __ ___   _____| | ___ _ __ \n" +
//                "  | | '__/ _` \\ \\ / / _ | |/ _ | '__|\n" +
//                "  | | | | (_| |\\ V |  __| |  __| |   \n" +
//                "  \\_|_|  \\__,_| \\_/ \\___|_|\\___|_|   \n");
//        User user = new User();
//        EscapeRoom escapeRoom = new EscapeRoom();
//        escapeRoom.generateEscapeRooms(escapeRoom);
//        String name = EscapeRoom.prompt("Please enter your name: ", "[ a-zA-z]*",
//                "\nThat is not a valid name!\n");
//        user.newName(name);
//        Traveler traveler = new Traveler(user, escapeRoom);
//        traveler.menu();
//    }

    public static void main(String[] args) {
        try {
            User user = new User();
            EscapeRoomGame escapeRoomGame = new EscapeRoomGame();
            String name = EscapeRoomGame.prompt("Please enter your name: ", "[ a-zA-z]*",
                    "\nThat is not a valid name!\n");
            user.newName(name);
            Traveler traveler = new Traveler(user, escapeRoomGame);
            traveler.menu();
//            Game game = new Game(traveler);
//            game.run();

        } catch (Exception e) {
            System.out.println("The main is not working");
        }
    }

}
