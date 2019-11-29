
import comp127graphics.CanvasWindow;
import comp127graphics.FontStyle;
import comp127graphics.GraphicsText;
import comp127graphics.ui.Button;
import comp127graphics.ui.TextField;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextAdventure {
    private CanvasWindow canvas = new CanvasWindow("Text Adventure", 800, 800);
    private GraphicsText title;
    private GraphicsText history;
    private TextField input;

    private List<String> commands;
    private String story;
    private Character user;
    private List<Room> rooms;
    private Room currentRoom;

    public TextAdventure (String story, ArrayList<String> commands, ArrayList rooms){
        this.story = story;
        this.commands = commands;
        this.rooms = rooms;
        currentRoom = (Room) rooms.get(0);
        user = new Character("Wolf", "A big bad wolf",null);

        title = new GraphicsText();
        title.setText("The Three Little Pigs");
        title.setFont(FontStyle.BOLD, 30);
        title.setPosition(25, 25);
        canvas.add(title);

        history = new GraphicsText();
        history.setText(story);
        history.setFont(FontStyle.PLAIN, 15);
        

        input = new TextField();
        input.setCenter(400, 700);
        canvas.add(input);

        canvas.draw();
    }

    public static void main(String[] args) {
        Room room1 = new Room ("Room 1");
        ArrayList<Room> rooms = new ArrayList<>();
        Room room2 = new Room ("Room 2");
        rooms.add(room1);
        rooms.add(room2);

        ArrayList<String> commands = new ArrayList<>();
        commands.add("go");
        commands.add("show inventory");

        TextAdventure adventure = new TextAdventure("Once upon a time", commands, rooms);

        while (5 + 5 == 10) {
            adventure.startGame();
        }
    }

    public void startGame(){
        Scanner in = new Scanner(System.in);
        String result = in.nextLine().trim().toLowerCase();
        rooms.get(0).addCharacter(); //add user to first room
        //handle commands
        if (!doCommand(result)) {
            System.out.println("Invalid command");
        }
    }

    public boolean doCommand (String command){
        command = command.toLowerCase().trim();
        if (commands.contains(command)){
            if(commands.equals("show inventory")){
                System.out.println(user.getInventoryList());
            }
            if (commands.equals("go") && rooms.indexOf(currentRoom) < rooms.size() -1){
                currentRoom = rooms.get(rooms.indexOf(currentRoom) + 1);
                //TODO Remove the user from the previous room's inventory and add it to the current room's inventory, and
                // print new room description
            }
            return true;
        }
        else return false;
    }

    public void addCommand(String command){
        commands.add(command);
    }

}
