package com.escaperooms.application;

import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;

public class AdventureParser {

    public ThemeRoom parse(File data){

        final ObjectMapper objectMapper = new ObjectMapper();
        ThemeRoom game = null;

        try {
            game = objectMapper.readValue(data, ThemeRoom.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return game;
    }


}