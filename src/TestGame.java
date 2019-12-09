import java.util.ArrayList;
import java.util.List;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGame {
    /**
     * Test that the user was removed from current room upon a keyboard command of "go (some direction)'
     *
     */
    @Test
    public void testUserPositionUpdate() {
        //
    }
    /**
     * Test that the current room was updated after user commanded a direction change
     *
     */
    @Test
    public void testRoomUpdate() {
        //
    }
    /**
     * Test that the item picked up was dropped
     *
     */
    @Test
    public void testItemDrop() {
        //
    }
    /**
     * Test that when a direction is written incorrectly by the user,
     * the program will respond by saying "You can't do that"
     * Mostly a text for the close cases (ex: wesst vs. west)
     *
     */
    @Test
    public void testInputWord() {
        //
    }

    public static void main(String[] args) {
        Room room1, room2, room3, room4, room5, room6;
        Character user = new Character("Player", "You are the player.", List.of());

        room1 = new Room("Room 1");
        room1.setDescription("You are in the first room.");
        room1.setStory("Once upon a time . . .");

        room2 = new Room("Room 2");
        room2.setDescription("You are in the second room.");
        room2.setStory("And then!");

        room3 = new Room("Room 3", null, null, null, room2, null, null);
        room3.setDescription("You are in the third room.");
        room3.setStory("And then!");

        room4 = new Room("Room 4", null, room2, null, null, null, null);
        room4.setDescription("You are in the fourth room.");
        room4.setStory("And then!");

        room5 = new Room("Room 5", room2, null, null, null, null, null);
        room5.setDescription("You are in the fifth room.");
        room5.setStory("And then!");

        room6 = new Room("Room 6", null, null, null, null, null, room2);
        room6.setDescription("You are in the sixth room, inside the second room.");
        room6.setStory("You have found the secret room! Congratulations!");

        room2.setConnections(new Room[]{room4, room5, room3, room1, room6, null});
        room1.setConnections(new Room[]{null, null, room2, null, null, null});


        Item pen, knife, box;
        pen = new Item("pen", "a ballpoint pen", null);
        knife = new Item("knife", "a sharp knife", null);
        box = new Item("box", "a cardboard box", List.of(pen), true, false);

        room1.addItemToRoom(box);
        room1.addCharacter(user);
        room3.addItemToRoom(knife);

        Character owl = new Character("Owl", "It's an owl. You can talk to it", List.of());
        owl.setFirstDialogue("\"I'm an owl, hoot hoot.\"");
        owl.setGeneralGreeting("\"Hello again, hoot hoot.\"");
        owl.populateDialogueByTopics("forest","\"It's big and green.\"");
        owl.populateDialogueByTopics("sky", "\"It's big and blue.\"");
        room1.addCharacter(owl);

        ArrayList<String> commands = new ArrayList<>(List.of("go", "show inventory", "pick up", "drop", "open"));
        ArrayList<Room> rooms = new ArrayList<>(List.of(room1, room2, room3, room4, room5, room6));

        TextAdventure testGame = new TextAdventure("This is a test game! Have fun.", commands, rooms, room1);


//        String input = TextAdventure.scanner.nextLine().toLowerCase().trim();
//        while(!input.equalsIgnoreCase("quit game")) {
//            testGame.runGame(input)l;
//        }
        testGame.startGame();
        //testGame.runGame();


    }
}
