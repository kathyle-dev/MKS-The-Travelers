package com.escaperooms.application;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EscapeRoomGameTest {
    EscapeRoomGame escapeRoomGame;
    ThemeRoom themeRoom;
    List<Puzzle> puzzleList;
    List<Item> puzzleItems;

    @Before
    public void setUp(){
        try {
            escapeRoomGame = new EscapeRoomGame();
        }catch (Exception e){
            System.out.println("Could not create an Escape Room");
        }
    }

    @Test
    public void testLoadWithDefaultCSV() {
        int gameListSize = escapeRoomGame.getGameList().size();
        assertEquals(1, gameListSize);
    }

    @Test
    public void testGetThemeInGameListWithDefaultCSV() {
        String result = escapeRoomGame.getGameList().get(0).getName();
        assertEquals("Trap Room", result);
    }

    @Test
    public void testGetPuzzlesofAThemeRoomWithDefaultCSV(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        puzzleList = themeRoom.getPuzzles();
        puzzleItems = puzzleList.get(0).getItems();

        assertEquals(1, puzzleList.size());
        assertEquals("puzzle1", puzzleList.get(0).getName());
        assertEquals(10, puzzleItems.size());
    }

    @Test
    public void testItemsOfAPuzzleWithDefaultCSV(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get(0);
        Item puzzleItem = puzzle.getItems().get(0);
        String name = puzzleItem.getName();
        String description = puzzleItem.getDescription();
        String itemType = puzzleItem.getItemType();
        String hasClue = puzzleItem.getHasClue();
        assertEquals("skyline", name);
        assertEquals("Skyline is a song by FKJ, that will send you chill vibes", description);
        assertEquals("CD", itemType);
        assertEquals("false", hasClue);
    }

}