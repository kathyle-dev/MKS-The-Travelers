package com.escaperooms.application;


import org.junit.Before;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EscapeRoomGameTest {
    EscapeRoomGame escapeRoomGame;
    ThemeRoom themeRoom;
    User player;
    Traveler traveler;
    List<Puzzle> puzzleList;
    List<Item> puzzleItems;

    @Before
    public void setUp(){
        try {
            player = new User();
            escapeRoomGame = new EscapeRoomGame();
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

    @Test
    public void testIsPuzzlesCompleted(){
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get(0);
        puzzle.setCompleted(true);
        boolean result = themeRoom.isPuzzleCompleted();
        assertEquals(true , result);
    }

    @Test
    public void testShowInventory() {
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get(0);
        Item puzzleItem = puzzle.getItems().get(0);
        String itemName1 = puzzleItem.getName();
        String itemName2 = puzzle.getItems().get(1).getName();
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
        Puzzle puzzle = themeRoom.getPuzzles().get(0);
        Item puzzleItem = puzzle.getItems().get(0);
        String itemName1 = puzzleItem.getName();
        String itemName2 = puzzle.getItems().get(1).getName();
        player.addItem(itemName1);
        player.addItem(itemName2);
        String playerItem1 = player.getInventory().get(0);
        System.out.println(playerItem1);
        assertEquals("skyline", playerItem1);
    }

    @Test
    public void testRemoveItem() {
        themeRoom = escapeRoomGame.getGameList().get(0);
        Puzzle puzzle = themeRoom.getPuzzles().get(0);
        Item puzzleItem = puzzle.getItems().get(0);
        String itemName1 = puzzleItem.getName();
        String itemName2 = puzzle.getItems().get(1).getName();
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
}