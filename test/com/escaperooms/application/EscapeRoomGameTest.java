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
    Map<String, Map<String, Item>> puzzleItems;

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
        int gameListSize = escapeRoomGame.getGameMap().size();
        assertEquals(2, gameListSize);
    }

    @Test
    public void testGetThemeInGameListWithDefaultCSV() {
        String result = escapeRoomGame.getGameMap().get("Trap Room").getName();
        assertEquals("Trap Room", result);
    }

    @Test
    public void testGetPuzzlesOfAThemeRoomWithDefaultCSV(){
        themeRoom = escapeRoomGame.getGameMap().get("Trap Room");
        puzzleList = themeRoom.getPuzzles();
        puzzleItems = puzzleList.get("puzzle1").getItems();
        assertEquals(1, puzzleList.size());
        assertEquals("puzzle1", puzzleList.get("puzzle1").getName());
        assertEquals(2, puzzleItems.size());
    }

//    @Test
//    public void testItemsOfAPuzzleWithDefaultCSV(){
//        themeRoom = escapeRoomGame.getGameList().get(0);
//        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
//<<<<<<< Updated upstream
//
//        Item puzzleItem = puzzle.getItems().get("skyline");
//=======
//        Item puzzleItem = puzzle.getItems().get("CD").get("skyline");
//        String name = puzzleItem.getName();
//>>>>>>> Stashed changes
//        String description = puzzleItem.getDescription();
//        String itemType =puzzleItem.getItemType();
//        String hasClue = puzzleItem.getHasClue();
//        assertEquals("skyline", puzzleItem.getName());
//        assertEquals("Skyline is a song by FKJ, that will send you chill vibes", description);
//        assertEquals("CD", itemType);
//        assertEquals("false", hasClue);
//    }

    @Test
    public void testIsPuzzlesCompleted() {
        themeRoom = escapeRoomGame.getGameMap().get("Trap Room");;
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
        puzzle.setCompleted(true);
        boolean result = themeRoom.isThemeRoomCompleted();
        assertEquals(true , result);
    }

    @Test
    public void userInputSplitTwoWords(){
        themeRoom = escapeRoomGame.getGameMap().get("Trap Room");
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
        themeRoom = escapeRoomGame.getGameMap().get("Trap Room");
        themeRoom.setCurrentPuzzle(themeRoom.getPuzzles().get("puzzle1"));
        String verb = "look at";
        String noun = "cd";
        themeRoom.setUserInput("look at cd");
        themeRoom.splitUserInput();

        assertEquals(verb,themeRoom.getVerb());
        assertEquals(noun,themeRoom.getNoun());
    }


//    @Test
//    public void testShowInventory() {
//        themeRoom = escapeRoomGame.getGameList().get(0);
//        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
//        Item puzzleItem = puzzle.getItems().get("cd");
//        String itemName1 = puzzleItem.getName();
//        String itemName2 = puzzle.getItems().get("soar").getName();
//        player.addItem(itemName1);
//        player.addItem(itemName2);
//        player.showInventory();
//        String[] testArray = {"skyline", "soar"};
//        assertArrayEquals(testArray, player.getInventory().toArray(new String[0]));
////        traveler.addItem(itemName);
////        traveler.showInventory();
//    }

    @Test
    public void testAddItem() {
        themeRoom = escapeRoomGame.getGameMap().get("Trap Room");
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
        Item puzzleItem = puzzle.getItems().get("cd").get("skyline");
        String itemName1 = puzzleItem.getName();
        String itemHasClue = puzzleItem.getHasClue();
//        String itemName2 = puzzle.getItems().get("cd").getName();
        traveler.addItem(itemName1);
//        traveler.addItem(itemName2);
        String travelerItem1 = traveler.getInventory().get(0);
        System.out.println(travelerItem1);
        System.out.println("Item: " + travelerItem1 + "\nDoes item have clue? " + itemHasClue);
        assertEquals("skyline", travelerItem1);
    }

    @Test
    public void testRemoveItem() {
        themeRoom = escapeRoomGame.getGameMap().get("Trap Room");
        Puzzle puzzle = themeRoom.getPuzzles().get("puzzle1");
        Item puzzleItem1 = puzzle.getItems().get("cd").get("skyline");
        Item puzzleItem2 = puzzle.getItems().get("picture").get("soaringEagle");
        String itemName1 = puzzleItem1.getName();
        String itemName2 = puzzleItem2.getName();
        traveler.addItem(itemName1);
        traveler.addItem(itemName2);
        int twoItemsAdded = traveler.getInventory().size();
        String playerItem1 = traveler.getInventory().get(0);
        String playerItem2 = traveler.getInventory().get(1);
        System.out.println(playerItem1 + ", " + playerItem2);
        traveler.removeItem(playerItem1);
        traveler.removeItem(playerItem2);
        int inventorySize = traveler.getInventory().size();
        assertEquals(0, inventorySize);
        assertEquals(2,twoItemsAdded);
    }

    @Test
    public void testGetInventory(){
       assertEquals(0, traveler.getInventory().size());
    }

    @Test
    public void testIsItemInInventory() {
    }

    @Test
    public void testHashMapPuzzleInThemeRoom(){
        themeRoom = escapeRoomGame.getGameMap().get("Trap Room");;
        puzzleList = themeRoom.getPuzzles();
        String expectedDescription = "This is kind of like the SAW movies, You must figure out how to unlock the door.\n There are twelve pictures and boom box that appears to have 3 CDs inside. Good luck";
        assertEquals(1, puzzleList.size());
        assertEquals("puzzle1", puzzleList.get("puzzle1").getName());
        assertEquals(expectedDescription,  puzzleList.get("puzzle1").getDescription());

    }

//    @Test
//    public void testDataStructureOfItemsMap() {
//        themeRoom = escapeRoomGame.getGameList().get(0);
//        puzzleList = themeRoom.getPuzzles();
//        Puzzle puzzle = puzzleList.get("puzzle1");
//        puzzleItems = puzzle.getItems();
//        assertEquals(2, puzzleItems.size());
//        assertEquals(3, puzzleItems.get("CD").size());
//        assertEquals(7, puzzleItems.get("picture").size());
//        System.out.println(puzzleItems);
//
//    }
}
