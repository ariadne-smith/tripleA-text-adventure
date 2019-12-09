import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextAdventure {
    private List<String> commands;
    private String title;
    private String story;
    private Character user;
    private List<Room> rooms;
    private Room startingRoom;
    private Room currentRoom;
    public List<Entity> allGameItems = new ArrayList<>();
    //public static Scanner scanner;

    public TextAdventure(String title, ArrayList<String> commands, ArrayList<Room> rooms) {
        this.title = title;
        this.commands = commands;
        this.rooms = rooms;
        startingRoom = rooms.get(0);
        currentRoom = startingRoom;
        user = new Character("Wolf", "A big bad wolf", null);
        //scanner = new Scanner();

        for(Room r : rooms){
            allGameItems.addAll(r.getItemList());
        }
    }

    public TextAdventure(String title, ArrayList<String> commands, ArrayList<Room> rooms, Room startingRoom) {
        this.title = title;
        this.commands = commands;
        this.rooms = rooms;
        this.startingRoom = startingRoom;
        currentRoom = startingRoom;
        user = new Character("Wolf", "A big bad wolf", null);
        //scanner = new Scanner(System.in);

        for(Room r : rooms) {
            allGameItems.addAll(r.getItemList());
        }

        for(Entity i : allGameItems){
            Item definedItem = (Item) i;
            definedItem.populateInteractions(allGameItems);
        }

    }

    public void startGame() {
        startingRoom.addCharacter(user);
    }

    public void runGame(String command) {
        while (true) {
            // = scanner.nextLine();
            if (command.contains("quit game")) {
                System.out.println("Quitting game");
                break;
            }
            if (doCommand(command)) {
            } else {
                System.out.println("You can't do that.");
            }
        }

    }

    public boolean doCommand(String command) {
        command = command.toLowerCase().trim();
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

    public Character findCharacterInRoomByName(Room room, String characterName) {
        Entity foundCharacter = null;

        for (Entity character : room.getCharacterList()) {
            if (character.getName().equalsIgnoreCase(characterName)) {
                foundCharacter = character;
            }
        }

        return (Character) foundCharacter;
    }

    public String getCommandList() {
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

    public String getTitle(){
        return this.title;
    }

    public String getStory(){
        return this.story;
    }

    public void setStory(String story){
        this.story = story;
    }

    public Room getStartingRoom(){
        return this.startingRoom;
    }

}
