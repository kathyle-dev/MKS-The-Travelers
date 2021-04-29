package com.escaperooms.crazystans;


import com.escaperooms.application.EscapeRoom;
import com.escaperooms.music.MusicPlayer;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

class Lobby {
    MusicPlayer musicPlayer;
    Hinter hinter;

    public Lobby() {
    }

    String start() {
        generateHints();
        System.out.println(welcomeMessage());
        playLobbyChallenge();
        return "songKey";
    }

    private void playLobbyChallenge() {
        musicPlayer = new MusicPlayer("feelitstill.wav");
        playSong();
        askQuestion();
        musicPlayer.stopMusic();
    }

    private void askQuestion() {
        String hint = EscapeRoom.prompt("The answer is in the song. When something's unexpected, it is said to be what? Listen carefully. ",
                challengeAnswer() + "|hint", "That is not the correct answer.");
        if (hint.equals("hint")) {
            hintChecker();
        }
    }

    private void generateHints() {
        hinter = new Hinter("Think baseball terminology", "There's the right field, center field, then...",
                "Odd, strange, surprise. LISTEN TO THE LYRICS!");
    }

    private void hintChecker() {
        if (hinter.isEmpty()) {
            System.out.println(ansi().fg(RED).a("NO MORE HINTS! SOLVE IT! Coming...").reset());
        } else {
            System.out.println("Hint: " + hinter.getHint());
        }
        askQuestion();
    }

    String welcomeMessage() {
        return "Welcome to Crazy Stans Lobby";
    }

    void playSong() {
        musicPlayer.start();
    }

    String challengeAnswer() {
        return "out of left field";
    }
}
