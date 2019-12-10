
import comp127graphics.FontStyle;
import comp127graphics.GraphicsText;
import comp127graphics.ui.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextAdventure {
    //private CanvasWindow canvas = new CanvasWindow("Text Adventure", 800, 800);
    private GraphicsText title;
    private GraphicsText history;
    private TextField input;

    private List<String> commands;
    private String story;
    private Character user;
    private List<Room> rooms;
    private Room currentRoom;
    List<Entity> allGameItems = new ArrayList<>();

    public static Scanner scanner;

    public TextAdventure(String story, ArrayList<String> commands, ArrayList<Room> rooms) {
        this.story = story;
        this.commands = commands;
        this.rooms = rooms;
        currentRoom = rooms.get(0);
        user = new Character("Wolf", "A big bad wolf", null);
        scanner = new Scanner(System.in);

        for(Room r : rooms){
            allGameItems.addAll(r.getItemList());
        }

        title = new GraphicsText();
        title.setText("");
        title.setFont(FontStyle.BOLD, 30);
        title.setPosition(25, 25);
        //canvas.add(title);

        history = new GraphicsText();
        history.setText(story);
        history.setFont(FontStyle.PLAIN, 15);


        input = new TextField();
        input.setCenter(400, 700);
        //canvas.add(input);

//        canvas.draw();
    }

    public TextAdventure(String story, ArrayList<String> commands, ArrayList<Room> rooms, Room startingRoom) {
        this.story = story;
        this.commands = commands;
        this.rooms = rooms;
        this.currentRoom = startingRoom;
        user = new Character("Wolf", "A big bad wolf", null);
        scanner = new Scanner(System.in);

        for(Room r : rooms) {
            allGameItems.addAll(r.getItemList());
        }

        for(Entity i : allGameItems){
            Item definedItem = (Item) i;
            definedItem.populateInteractions(allGameItems);
        }

        title = new GraphicsText();
        title.setText("");
        title.setFont(FontStyle.BOLD, 30);
        title.setPosition(25, 25);
        //canvas.add(title);

        history = new GraphicsText();
        history.setText(story);
        history.setFont(FontStyle.PLAIN, 15);


        input = new TextField();
        input.setCenter(400, 700);
        //canvas.add(input);

//        canvas.draw();
    }

    /*
    public static void main(String[] args) {
        Room room1 = new Room("Room 1");
        ArrayList<Room> rooms = new ArrayList<>();
        Room room2 = new Room("Room 2");
        rooms.add(room1);
        rooms.add(room2);

        ArrayList<String> commands = new ArrayList<>();
        commands.add("go");
        commands.add("show inventory");

        TextAdventure adventure = new TextAdventure("Once upon a time", commands, rooms);

        String userInput = scanner.nextLine().trim().toLowerCase();
        while (true) {
            adventure.runGame();
        }
    }*/

    public void startGame() {
        System.out.println("Available commands: " + getCommandList());
        System.out.println("Enter 'quit game' to quit.");
        System.out.println(story);
        System.out.println(rooms.get(0).getStory());
        System.out.println(rooms.get(0).getDescription());
        System.out.println(rooms.get(0).getConnectionsDescription());
        if (currentRoom.getCharacterListDescription() != null) {
            System.out.println(currentRoom.getCharacterListDescription());
        }
        if (currentRoom.getItemListDescription() != null) {
            System.out.println(currentRoom.getItemListDescription());
        }
        rooms.get(0).addCharacter(user);
        System.out.println("> ");
    }

    public void runGame() {
        while (true) {
            String command = scanner.nextLine();
            if (command.contains("quit game")) {
                System.out.println("Quitting game");
                break;
            }
            if (doCommand(command)) {
                System.out.println(">");
            } else {
                System.out.println("You can't do that.");
                System.out.println(">");
            }
        }
    }

    public boolean doCommand(String command) {
        command = command.toLowerCase().trim();
        if(command.contains("use")){
            handleUse(command);
            return true;
        }
        if (command.contains("show inventory")) {
            System.out.println(user.getInventoryList());
            return true;
        }
        if (command.contains("go ")) {
            if (handleGo(command)) {
                return true;
            } else {
                return false;
            }
        }
        if (command.contains("open ")) {
            handleOpen(command);
            return true;
        }
        if (command.contains("pick up ")) {
            handlePickUp(command);
            return true;
        }
        if (command.contains("drop ")) {
            handleDrop(command);
            return true;
        }
        if (command.contains("talk ")) {
            handleTalk(command);
            return true;
        }
        if (command.contains("eat")){
            handleEat(command);
            return true;
        } else return false;
    }

    public void moveUserTo(Room room) {
        currentRoom.removeCharacter(user);
        currentRoom = room;
        currentRoom.addCharacter(user);
        if (currentRoom.getPlayerFirstArrives()) {
            System.out.println(currentRoom.getStory());
            currentRoom.setPlayerFirstArrives(false);
        }
        System.out.println((currentRoom.getDescription()));
        System.out.println(currentRoom.getConnectionsDescription());
        if (currentRoom.getItemListDescription() != null) {
            System.out.println(currentRoom.getCharacterListDescription());
            System.out.println(currentRoom.getItemListDescription());
        }
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public void addInteraction(Item item1, Item item2, Runnable interaction){
        item1.addInteraction(item2, interaction);
        item2.addInteraction(item1, interaction);
    }

    public void populateInteractions(){
        for (Entity item : allGameItems) {
            ((Item)item).populateInteractions(allGameItems);
        }
    }

    private boolean handleGo(String command) {
        String direction = command.substring(command.indexOf("go") + 2);
        direction = direction.toLowerCase().trim();
        if (direction.contains("north") && currentRoom.getConnections()[0] != null) {
            moveUserTo(currentRoom.getConnections()[0]);
            return true;
        }
        if (direction.contains("south") && currentRoom.getConnections()[1] != null) {
            moveUserTo(currentRoom.getConnections()[1]);
            return true;
        }
        if (direction.contains("east") && currentRoom.getConnections()[2] != null) {
            moveUserTo(currentRoom.getConnections()[2]);
            return true;
        }
        if (direction.contains("west") && currentRoom.getConnections()[3] != null) {
            moveUserTo(currentRoom.getConnections()[3]);
            return true;
        }
        if (direction.contains("in") && currentRoom.getConnections()[4] != null) {
            moveUserTo(currentRoom.getConnections()[4]);
            return true;
        }
        if (direction.contains("out") && currentRoom.getConnections()[5] != null) {
            moveUserTo(currentRoom.getConnections()[5]);
            return true;
        } else {
//            System.out.println("Cannot do that. Try again.");
            return false;
        }
    }

    private void handleOpen(String command) {
        String itemName = command.substring(command.indexOf("open") + 5);
        itemName = itemName.trim().toLowerCase();
        Item itemFound = (Item) currentRoom.containsItemOfName(itemName);
        if (itemFound != null) {
            if (itemFound.isOpenable) {
                //handle open
                if (itemFound.isOpen) {
                    //already open
                    System.out.println("It's already open!");
                } else {
                    //it's not open, so open it
                    itemFound.setOpen(true);
                    System.out.println((itemFound.describeContents()));
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

    private void handlePickUp(String command) {
        String itemName = command.substring(command.indexOf("pick up") + 7);
        itemName = itemName.toLowerCase().trim();

        if (user.containsItemOfName(itemName) != null) {
            //if it is already in user's inventory
            System.out.println("You already picked that up.");
        } else if (currentRoom.containsItemOfName(itemName) != null) {
            //if the room contains the item, pick it up
            Entity item = currentRoom.containsItemOfName(itemName);
            user.addItemToInventory(item);
            currentRoom.removeItemFromRoom((Item) item);
            System.out.println("You picked up the " + itemName + ".");
        } else {
            System.out.println("You can't do that.");
        }
    }

    private void handleDrop(String command) {
        String itemName = command.substring(command.indexOf("drop") + 4);
        itemName = itemName.toLowerCase().trim();
        if (user.containsItemOfName(itemName) != null) {
            Entity item = user.containsItemOfName(itemName);
            currentRoom.addItemToRoom((Item) item);
            user.removeItemFromInventory(item);
            System.out.println("You dropped the " + itemName + ".");
        } else {
            System.out.println("You don't have that on you.");
        }
    }

    private void handleTalk(String command) { //need to refactor
        String targetCharacterName, commandWord;
        String chosenTopic = "";
        if (command.contains("talk to")) {
            commandWord = "talk to";
            targetCharacterName = command.substring(command.indexOf("talk to") + 7).trim();
        } else {
            //the command was just "talk"
            commandWord = "talk";
            targetCharacterName = command.substring(command.indexOf("talk") + 4).trim();
        }

        if(targetCharacterName.contains(" about ")){
            chosenTopic = targetCharacterName.substring(targetCharacterName.indexOf("about") + 6);
            targetCharacterName = targetCharacterName.substring(0, targetCharacterName.indexOf(" about "));
        }

        Character targetCharacter = findCharacterInRoomByName(currentRoom, targetCharacterName);

        if (targetCharacter != null) {
            //then the character is in the room
            //so talk to it
            if (!command.contains(" about ")) {

                if (targetCharacter.getIsPlayersFirstTimeSpeakingTo()) {
                    //then it's the user's first time speaking with this character
                    System.out.println(targetCharacter.getFirstDialogue());
                    System.out.println(" You can talk with " + targetCharacter.getName() + " about: " +
                            targetCharacter.getDialogueByTopics().keySet());
                    targetCharacter.setIsPlayersFirstTimeSpeakingTo(false);
                } else {
                    //it's not the first time
                    System.out.println(targetCharacter.getGeneralGreeting());
                }
            } else {
                chosenTopic = command.substring(
                        command.indexOf("about") + 5
                ).trim();
                targetCharacter.beSpokenToAbout(chosenTopic);
            }
        }

    }

    private void handleEat(String command){
        String itemName = command.substring(command.indexOf("eat") + 3);
        itemName = itemName.toLowerCase().trim();
        if (currentRoom.containsItemOfName(itemName) != null && currentRoom.containsItemOfName(itemName).getIsEatable()) {
            Entity item = currentRoom.containsItemOfName(itemName);
            currentRoom.removeItemFromRoom((Item) item);
            System.out.println("You ate the" + itemName + "!");
        } else if(currentRoom.containsCharacterOfName(itemName) != null && currentRoom.containsCharacterOfName(itemName).getIsEatable()){
            Entity character = currentRoom.containsCharacterOfName(itemName);
            currentRoom.removeCharacter((Character) character);
            System.out.println(currentRoom.getCharacterList());
            System.out.println("You ate " + itemName + "!");
        } else{
            System.out.println("You can't eat that or you already ate it.");
        }
    }

    private void handleUse(String command){
        command = command.substring(command.indexOf("use") + 3);
        //if command contains "with" and there is a valid word to process after "with"
        if (!command.contains("with") || command.substring(command.indexOf("with")).length() < 5 ){
            System.out.println("That doesn't make sense.");
        } else {
            String itemName = command.substring(0, command.indexOf("with")).trim().toLowerCase();
            String itemName2 = command.substring(command.indexOf("with") + 4).trim().toLowerCase();
            Item item1 = getItemForUse(itemName);
            Item item2 = getItemForUse(itemName2);
            //if both of these items are valid items in the room or in the user's inventory
            if(item1 == null || item2 == null){
                System.out.println("You can't do that.");
            } else{
                Runnable action = item1.getInteraction(item2);
                action.run();
            }
        }
    }

    public Character findCharacterInRoomByName(Room room, String characterName) {
        Entity foundCharacter = null;

        for (Entity character : room.getCharacterList()) {
            if (character.getName().equalsIgnoreCase(characterName)) {
                foundCharacter = character;
            }
        }

        return (Character) foundCharacter;
    }

    private String getCommandList() {
        String result = "";
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).equals("go")) {
                result = result + commands.get(i) + " (direction)";
            } else {
                result = result + commands.get(i);
            }
            if (i < commands.size() - 1) {
                result = result + ", ";
            } else {
                result = result + ".";
            }
        }
        return result;
    }

    //item interaction methods -- must be static
    static void doNothing(){
        System.out.println("You can't do that.");
    }

    private Item getItemForUse(String name){
        Item thing;
        if(currentRoom.containsItemOfName(name) != null) {
            thing = (Item) currentRoom.containsItemOfName(name);
            return thing;
        }
        else if(user.containsItemOfName(name) != null) {
            thing = (Item) user.containsItemOfName(name);
            return thing;
        } else return null;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }

}
