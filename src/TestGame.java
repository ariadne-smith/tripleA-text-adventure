import comp127graphics.ui.TextField;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class TestGame {
    public static void main(String[] args) {
        Room room1, room2, room3, room4, room5, room6;
        room1 = new Room("Room 1");
        room1.setDescription("This is the first room.");
        room1.setStory("Once upon a time . . .");

        room2 = new Room("Room 2");
        room2.setDescription("This is the second room.");
        room1.setStory("And then!");

        room3 = new Room("Room 3", null, null, null, room2, null, null);
        room3.setDescription("This is the third room.");
        room1.setStory("And then!");

        room4 = new Room("Room 4", null, room2, null, null, null, null);
        room4.setDescription("This is the fourth room.");
        room1.setStory("And then!");

        room5 = new Room("Room 5", room2, null, null, null, null, null);
        room5.setDescription("This is the fifth room.");
        room1.setStory("And then!");

        room6 = new Room("Room 6", null, null, null, room2, null, null);
        room6.setDescription("This is the sixth room, inside the second room.");
        room1.setStory("THE END");

        room2.setConnections(new Room[]{room4, room5, room3, room1, room6, null});
        room1.setConnections(new Room[]{null, null, room2, null, null, null});


        Item pen, knife, box;
        pen = new Item("pen", "a ballpoint pen", null);
        knife = new Item("knife", "a sharp knife", null);
        box = new Item("box", "a cardboard box", List.of(pen), true, false);

        room4.addItemToRoom(box);
        room3.addItemToRoom(knife);

        ArrayList<String> commands = new ArrayList<String>(List.of("go", "show inventory", "pick up", "drop", "open"));
        ArrayList<Room> rooms = new ArrayList<Room>(List.of(room1, room2, room3, room4, room5, room6));

        TextAdventure testGame = new TextAdventure("This is a test game! Have fun.", commands,rooms);
        testGame.startGame();



    }
}
