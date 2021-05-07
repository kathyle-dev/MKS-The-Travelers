package com.escaperooms.application;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.File;
import java.io.IOException;

public class AdventureParser {

    public ThemeRoom parse(File data) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        ThemeRoom game = null;

        try {
            game = objectMapper.readValue(data, ThemeRoom.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return game;
    }

    public void happy() {
        ObjectMapper mapper = new ObjectMapper();
    }

}