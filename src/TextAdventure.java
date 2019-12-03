
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

    public TextAdventure (String story, ArrayList<String> commands, ArrayList<Room> rooms){
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
        rooms.get(0).addCharacter(user);
        //handle commands
        if (!doCommand(result)) {
            System.out.println("Invalid command");
        }
    }

    public boolean doCommand (String command){
        command = command.toLowerCase().trim();
        if (commands.contains(command)){
            if(command.equals("show inventory")){
                System.out.println(user.getInventoryList());
            }
            if (command.indexOf("go") > 0){
                handleGo(command);
            }
            if(command.indexOf("open") > 0){
                handleOpen(command);
            }
            return true;
        }
        else return false;
    }

    public void moveUserTo (Room room){
        currentRoom.removeCharacter(user);
        currentRoom = room;
        currentRoom.addCharacter(user);
        if(currentRoom.getPlayerFirstArrives()){
            System.out.println(currentRoom.getStory());
        }
        System.out.println((currentRoom.getDescription()));
    }

    public void addCommand(String command){
        commands.add(command);
    }

    private void handleGo(String command){
        String direction = command.substring(command.indexOf("go" + 2));
        direction = direction.toLowerCase().trim();
        if (direction.equals("north") && currentRoom.getConnections()[0] != null){
            moveUserTo(currentRoom.getConnections()[0]);
        }
        if (direction.equals("south") && currentRoom.getConnections()[1] != null){
            moveUserTo(currentRoom.getConnections()[1]);
        }
        if (direction.equals("east") && currentRoom.getConnections()[2] != null){
            moveUserTo(currentRoom.getConnections()[2]);
        }
        if (direction.equals("west") && currentRoom.getConnections()[3] != null){
            moveUserTo(currentRoom.getConnections()[3]);
        }
        if (direction.equals("in") && currentRoom.getConnections()[4] != null){
            moveUserTo(currentRoom.getConnections()[4]);
        }
        if (direction.equals("out") && currentRoom.getConnections()[5] != null){
            moveUserTo(currentRoom.getConnections()[5]);
        }
        else{
            System.out.println("Cannot do that. Try again.");
        }
    }

    private void handleOpen (String command){
        String itemName = command.substring(command.indexOf("open" + 4));
        itemName = itemName.trim().toLowerCase();
        Item itemFound = (Item) currentRoom.containsItemOfName(itemName);
        if(itemFound != null){
            if(itemFound.isOpenable){
                //handle open
                if(itemFound.isOpen){
                    //already open
                    System.out.println("It's already open!");
                } else {
                    //it's not open, so open it
                    itemFound.setOpen(true);
                    itemFound.describeContents();
                }
            } else {
                //is not openable
                System.out.println("You can't open this.");
            }
        } else {
            //no such item found
            System.out.println("That doesn't exist here.");
        }
    }

    private void handlePickUp (String command){
        String itemName = command.substring(command.indexOf("pick up" + 7));
        itemName = itemName.toLowerCase().trim();
        if(user.containsItemOfName(itemName) != null){
            System.out.println("You already picked that up.");
        }
        else if(currentRoom.containsItemOfName(itemName) != null){
            Entity item = currentRoom.containsItemOfName(itemName);
            user.addItemToInventory(item);
            currentRoom.removeItemFromRoom((Item) item);
            System.out.println("You picked up the " + itemName);
        } else{
            System.out.println("You cannot pick that item up or it does not exist.");
        }
    }

    private void handleDrop (String command){
        String itemName = command.substring(command.indexOf("drop" + 4));
        itemName = itemName.toLowerCase().trim();
        if(user.containsItemOfName(itemName) != null){
            Entity item = user.containsItemOfName(itemName);
            currentRoom.addItemToRoom((Item) item);
            user.removeItemFromInventory(item);
            System.out.println("You dropped the "+ itemName);
        } else {
            System.out.println("You don't have that on you.");
        }
    }

}
