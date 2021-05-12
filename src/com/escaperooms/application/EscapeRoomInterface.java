package com.escaperooms.application;

import java.io.IOException;

public interface EscapeRoomInterface {
    default Playable playable() throws IOException {
        return null;
    }

    default void run(Traveler traveler, EscapeRoomGame escapeRoomGame) {

    }
    default void terminate() {

    }

    String getName();
}