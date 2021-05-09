package com.escaperooms.crazystans;

import com.escaperooms.application.EscapeRoomGame;
import com.escaperooms.music.MusicPlayer;

import java.util.List;

class CrazyStansPrompter {
    private static final List<String> musicControls = List.of("p", "s", "r");

    static String prompt(String message, String regex, MusicPlayer musicPlayer) {
        final StringBuffer controls = new StringBuffer();
        musicControls.forEach(control -> controls.append("|").append(control));
        regex = regex + controls.toString() + "|commands";
        String input = EscapeRoomGame.prompt(message, regex, "That is not the correct answer.");
        String result = "rerun";
        if(musicPlayer.getClip() != null && musicControls.contains(input)) {
            switch (input) {
                case "p":
                    musicPlayer.getClip().start();
                    break;
                case "s":
                    musicPlayer.getClip().stop();
                    break;
                case "r":
                    musicPlayer.getClip().setMicrosecondPosition(0);
                    break;
            }
        } else if(input.equals("commands")) {
            System.out.println(CrazyStans.commandsList());
        } else {
            result = input;
        }
        return result;
    }
}