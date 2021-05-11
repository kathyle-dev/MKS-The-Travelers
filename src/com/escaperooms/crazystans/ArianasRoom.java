package com.escaperooms.crazystans;

import com.escaperooms.application.EscapeRoomGame;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

class ArianasRoom {
    private Hinter hinter;

    String start() {
        generateHints();
        System.out.println(welcomeMessage());
        askQuestion();
        return "lickedDonut";
    }

    private void askQuestion() {
        String hint = EscapeRoomGame.prompt("What kind of donut did Ariana Grande lick? ",
                challengeAnswer() + "|hint", "That is not the correct answer.");
        if (hint.equals("hint")) {
            hintChecker();
        }
    }

    private void generateHints() {
        hinter = new Hinter("Circular in shape. Powder on top.", "They inject something inside this type of donut.",
                "It is FILLED with something.");
    }

    private void hintChecker() {
        if (hinter.isEmpty()) {
            System.out.println(ansi().fg(RED).a("NO MORE HINTS! SOLVE IT! cream fi...").reset());
        } else {
            System.out.println("Hint: " + hinter.getHint());
        }
        askQuestion();
    }

    private String welcomeMessage() {
        return "Welcome to the Crazy Stans' Ariana Grande room.";
    }

    private String challengeAnswer() {
        return "cream filled";
    }
}