package com.escaperooms.crazystans;

import com.escaperooms.application.EscapeRoomGame;
import com.escaperooms.music.MusicPlayer;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.ansi;

import java.util.Map;

class ChristinaAguilerasRoom {

    private final Map<String, String> answers = Map.of("challengeOneSoar", "soar", "challengeTwoIWillBe", "i will be");

    String start() {
        System.out.println(welcomeMessage());
        soarChallengeStart();
        iWillBeChallengeStart();
        return "aguilerasMic";
    }

    private void startPrompt() {
        EscapeRoomGame.prompt("Type 'start' to begin the challenge. ", "start", "Invalid command");
    }

    private String commandsMessage() {
        return "Type 'commands' to see what you can do: ";
    }

    private String completeMessage() {
        return "Challenge completed!";
    }

    private void soarChallengeStart() {
        startPrompt();
        MusicPlayer musicPlayer = startSong("soar.wav");
        challengeOneSoar(musicPlayer);
        System.out.println(completeMessage());
    }

    private void challengeOneSoar(MusicPlayer musicPlayer) {
        String input = CrazyStansPrompter.prompt("What word is the highest note hit in this sequence? " + commandsMessage(),
                answers.get("challengeOneSoar"), musicPlayer);
        if(input.equals("rerun")) {
            challengeOneSoar(musicPlayer);
        } else {
            musicPlayer.stopMusic();
        }
    }

    private void iWillBeChallengeStart() {
        startPrompt();
        MusicPlayer musicPlayer = startSong("iwillbe.wav");
        challengeTwoIWillBe(musicPlayer);
        System.out.println(completeMessage());
    }

    private void challengeTwoIWillBe(MusicPlayer musicPlayer) {
        String input = CrazyStansPrompter.prompt("Unreleased as apart of the Stripped album, the greatest album of all time, " +
                        "what is the name of this song? " + commandsMessage(),
                answers.get("challengeTwoIWillBe"), musicPlayer);
        if(input.equals("rerun")) {
            challengeTwoIWillBe(musicPlayer);
        } else {
            musicPlayer.stopMusic();
        }
    }

    private String welcomeMessage() {
        return "Welcome to Crazy Stans' Christina Aguilera Room.\nYou have been allowed entry into the GOAT's room " +
                "because you have acquired the items from those that aspire to be the GOAT but can never be " +
                "and a legend from the past.\nHere you will be faced with three challenges, the most difficult you have " +
                "faced.\n" + ansi().fg(RED).a("There are no hints in this room.").reset() + "\nSolve both challenges, " +
                "and you will be granted with the mic of the great one and be allowed to escape.";
    }


    private MusicPlayer startSong(String song) {
        MusicPlayer musicPlayer = new MusicPlayer(song);
        musicPlayer.start();
        return  musicPlayer;
    }
}