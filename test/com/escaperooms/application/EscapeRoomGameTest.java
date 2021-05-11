package com.escaperooms.application;


import org.junit.Before;
import org.junit.Test;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

import static org.junit.Assert.*;

public class EscapeRoomGameTest {
    EscapeRoomGame escapeRoomGame;
    ThemeRoom themeRoom;
    User player;
    Traveler traveler;

    Map<String, Puzzle> puzzleList;
    Map<String, Item> puzzleItems;

    @Before
    public void setUp(){
        try {
            player = new User();
            player = new User();
            escapeRoomGame = new EscapeRoomGame();
            player.newName("testUser");
            traveler = new Traveler(player, escapeRoomGame);
            player.newName("testUser");
            traveler = new Traveler(player, escapeRoomGame);
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
        puzzleItems = puzzleList.get("puzzle1").getItems();

        assertEquals(1, puzzleList.size());
        assertEquals("puzzle1", puzzleList.get("puzzle1").getName());
        assertEquals(10, puzzleItems.size());
    }

    @Test
    public void testItemsOfAPuzzleWithDefaultCSV(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");

        Item puzzleItem = puzzle.getItems().get("skyline");
        String description = puzzleItem.getDescription();
        String itemType =puzzleItem.getItemType();
        String hasClue = puzzleItem.getHasClue();
        assertEquals("skyline", puzzleItem.getName());
        assertEquals("Skyline is a song by FKJ, that will send you chill vibes", description);
        assertEquals("CD", itemType);
        assertEquals("false", hasClue);
    }

    @Test
    public void testIsPuzzlesCompleted(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
        puzzle.setCompleted(true);
        boolean result = themeRoom.isThemeRoomCompleted();
        assertEquals(true , result);
    }

    @Test
    public void userInputSplitTwoWords(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        themeRoom.setCurrentPuzzle(themeRoom.getPuzzles().get("puzzle1"));
        String verb = "view";
        String noun = "cd";
        themeRoom.setUserInput("view cd");
        themeRoom.splitUserInput();

        assertEquals(verb,themeRoom.getVerb());
        assertEquals(noun,themeRoom.getNoun());
    }
    @Test
    public void userInputSplitThreeWords(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        themeRoom.setCurrentPuzzle(themeRoom.getPuzzles().get("puzzle1"));
        String verb = "look at";
        String noun = "cd";
        themeRoom.setUserInput("look at cd");
        themeRoom.splitUserInput();

        assertEquals(verb,themeRoom.getVerb());
        assertEquals(noun,themeRoom.getNoun());
    }


    @Test
    public void testShowInventory() {
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
        Item puzzleItem = puzzle.getItems().get("skyline");
        String itemName1 = puzzleItem.getName();
        String itemName2 = puzzle.getItems().get("soar").getName();
        player.addItem(itemName1);
        player.addItem(itemName2);
        player.showInventory();
        String[] testArray = {"skyline", "soar"};
        assertArrayEquals(testArray, player.getInventory().toArray(new String[0]));
//        traveler.addItem(itemName);
//        traveler.showInventory();
    }

    @Test
    public void testAddItem() {
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
        Item puzzleItem = puzzle.getItems().get("skyline");
        String itemName1 = puzzleItem.getName();
        String itemName2 = puzzle.getItems().get("soar").getName();
        player.addItem(itemName1);
        player.addItem(itemName2);
        String playerItem1 = player.getInventory().get(0);
        System.out.println(playerItem1);
        assertEquals("skyline", playerItem1);
    }

    @Test
    public void testRemoveItem() {
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
        Item puzzleItem = puzzle.getItems().get("skyline");
        String itemName1 = puzzleItem.getName();
        String itemName2 = puzzle.getItems().get("soar").getName();
        player.addItem(itemName1);
        player.addItem(itemName2);
        String playerItem1 = player.getInventory().get(0);
        String playerItem2 = player.getInventory().get(1);
        System.out.println(playerItem1 + ", " + playerItem2);
        player.removeItem(playerItem1);
        player.removeItem(playerItem2);
        int inventorySize = player.getInventory().size();
        assertEquals(0, inventorySize);
    }

    @Test
    public void testIsItemInInventory() {
    }

    @Test
    public void testHashMapPuzzleInThemeRoom(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        puzzleList = themeRoom.getPuzzles();
        String expectedDescription = "This is kind of like the SAW movies, You must figure out how to unlock the door.\n There are twelve pictures and boom box that appears to have 3 CDs inside. Good luck";
        assertEquals(1, puzzleList.size());
        assertEquals("puzzle1", puzzleList.get("puzzle1").getName());
        assertEquals(expectedDescription,  puzzleList.get("puzzle1").getDescription());

    }
}
