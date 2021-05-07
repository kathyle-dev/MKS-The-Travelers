package com.escaperooms.client;

import com.escaperooms.application.EscapeRoom;
import com.escaperooms.application.ThemeRoom;
import com.escaperooms.application.Traveler;
import com.escaperooms.application.User;
import org.fusesource.jansi.AnsiConsole;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.*;

import java.io.IOException;
import java.util.List;

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
