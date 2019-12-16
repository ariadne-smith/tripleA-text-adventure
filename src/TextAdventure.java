import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * The TextAdventure class contains all of the methods and structure necessary for walking a user through a specific story.
 * It is fed into TextAdventureDisplay, which then displays its attributes on a canvas.
 */

public class TextAdventure {
    private List<String> commands;
    private String title;
    private String story;
    private Character user;
    private List<Room> rooms;
    private Room startingRoom;
    private Room currentRoom;
    private int score;
    private List<Entity> allGameItems = new ArrayList<>();


    TextAdventure(String title, ArrayList<String> commands, ArrayList<Room> rooms, Room startingRoom) {
        this.title = title;
        this.commands = commands;
        this.rooms = rooms;
        this.startingRoom = startingRoom;
        currentRoom = startingRoom;
        user = new Character(null, null, null);
        score = 0;
        for(Room r : rooms) {
            allGameItems.addAll(r.getItemList());
        }
        this.populateInteractions();
    }

    /**
     * 'Places' the user into the first room that has been designated by the story line.
     */

    void startGame() {
        startingRoom.addCharacter(user);
    }

    /**
     * The main function of runGame is to process commands inputted by the user, if not about quitting the game, the method
     * calls on doCommand, if doCommand determines an incorrect input, runGame handles the output that informs the user of this.
     */

    String runGame(String command) {
        while (true) {
            if (command.contains("quit game")) {
                return "Quitting game";
            }
            if (checkCommand(command)) {
                return doCommand(command);
            } else {
                return "You can't do that.";
            }
        }
    }

    /**
     * Method to check if the user's input command is valid.
     */

    private boolean checkCommand(String command){
        command = command.toLowerCase().trim();
        if(command.contains("blow down ")){
            return true;
        }
        if (command.contains("show inventory")) {
            return true;
        }
        if (command.contains("go ")) {
            return checkGo(command);
        }
        if (command.contains("open ")) {
            return true;
        }
        if (command.contains("pick up ")) {
            return true;
        }
        if (command.contains("drop ")) {
            return true;
        }
        if (command.contains("talk ")) {
            return true;
        }
        if (command.contains("eat ")){
            return true;
        }
        return command.contains("use ");
    }

    /**
     * Handles the user's input commands, if valid the method calls on the correct method to implement the command.
     */

    private String doCommand(String command) {
        command = command.toLowerCase().trim();
        if (command.contains("blow down ")) {
            return handleBlowDown(command);
        }
        if (command.contains("show inventory")) {
            return user.getInventoryList();
        }
        if (command.contains("go ")) {
            if (checkGo(command)) {
                return handleGo(command);
            }
        }
        if (command.contains("open ")) {
            return handleOpen(command);
        }
        if (command.contains("pick up ")) {
            return handlePickUp(command);
        }
        if (command.contains("drop ")) {
            return handleDrop(command);
        }
        if (command.contains("talk ")) {
            return handleTalk(command);
        }
        if (command.contains("eat")){
            return handleEat(command);
        }
        if(command.contains("use")){
            return handleUse(command);
        }
        else return "You can't do that.";
    }

    /**
     * Method checks to see if it is the first time the user has arrived at the room - if so, it prints out the necessary
     * descriptions for the room and changes a boolean to record the visit. If the method determines that the user has
     * already been in the room it does not print out anything and simply moves the user to the correct room.
     */

    String moveUserTo(Room room) {
        String output = "";
        currentRoom.removeCharacter(user);
        currentRoom = room;
        currentRoom.addCharacter(user);
        if (currentRoom.getPlayerFirstArrives()) {
            output += currentRoom.getStory();
            currentRoom.setPlayerFirstArrives(false);
        }
        output += "\n" + currentRoom.getDescription();
        output += "\n" + currentRoom.getConnectionsDescription();
        if (currentRoom.getItemListDescription() != null) {
            output += "\n" + currentRoom.getCharacterListDescription();
            output += "\n" + currentRoom.getItemListDescription();
        }
        return output;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    /**
     * Connects an existing item to its 'receiver' item through an interaction (ex: blow, eat).
     */

    void addInteraction(Item item1, Item item2, Supplier<String> interaction){
        item1.addInteraction(item2, interaction);
        item2.addInteraction(item1, interaction);
    }

    /**
     *
     */

    void populateInteractions(){
        for (Entity item : allGameItems) {
            ((Item)item).populateInteractions(allGameItems);
        }
    }

    /**
     * If a user's input command is a 'go' command + a direction, the method checks to see if the given direction is a
     * valid one.
     */

    private boolean checkGo(String command){
        String direction = command.substring(command.indexOf("go") + 2);
        direction = direction.toLowerCase().trim();
        if (direction.contains("north") && currentRoom.getConnections()[0] != null) {
            return true;
        }
        if (direction.contains("south") && currentRoom.getConnections()[1] != null) {
            return true;
        }
        if (direction.contains("east") && currentRoom.getConnections()[2] != null) {
            return true;
        }
        if (direction.contains("west") && currentRoom.getConnections()[3] != null) {
            return true;
        }
        if (direction.contains("in") && currentRoom.getConnections()[4] != null) {
            return true;
        }
        return direction.contains("out") && currentRoom.getConnections()[5] != null;
    }

    /**
     * After previously having checked the given direction for validity, method 'moves' the user to the room in that
     * direction and updates all the necessary storage variables.
     */

    private String handleGo(String command) {
        String direction = command.substring(command.indexOf("go") + 2);
        direction = direction.toLowerCase().trim();
        if (direction.contains("north") && currentRoom.getConnections()[0] != null
                && currentRoom.getConnections()[0].getAccessible()) {
            return moveUserTo(currentRoom.getConnections()[0]);
        }
        if (direction.contains("south") && currentRoom.getConnections()[1] != null
                && currentRoom.getConnections()[1].getAccessible()) {
            return moveUserTo(currentRoom.getConnections()[1]);
        }
        if (direction.contains("east") && currentRoom.getConnections()[2] != null
                && currentRoom.getConnections()[2].getAccessible()) {
            return moveUserTo(currentRoom.getConnections()[2]);
        }
        if (direction.contains("west") && currentRoom.getConnections()[3] != null
                && currentRoom.getConnections()[3].getAccessible()) {
            return moveUserTo(currentRoom.getConnections()[3]);
        }
        if (direction.contains("in") && currentRoom.getConnections()[4] != null
                && currentRoom.getConnections()[4].getAccessible()) {
            return moveUserTo(currentRoom.getConnections()[4]);
        }
        if (direction.contains("out") && currentRoom.getConnections()[5] != null
                && currentRoom.getConnections()[5].getAccessible()) {
            return moveUserTo(currentRoom.getConnections()[5]);
        } else {
            return "Cannot do that. Try again.";
        }
    }

    /**
     * The following handle(Open)(Pickup)(Drop)(Talk)(Eat)(Use) methods take in the user's command word + the item or
     * character they are interacting with.
     * The methods check the item/character's status' and then print out messages or carries out the command accordingly.
     *
     */

    private String handleOpen(String command) {
        String itemName = command.substring(command.indexOf("open") + 5);
        itemName = itemName.trim().toLowerCase();
        Item itemFound = (Item) currentRoom.containsItemOfName(itemName);
        if (itemFound != null) {
            if (itemFound.isOpenable) {
                if (itemFound.isOpen) {
                    //already open
                    return "It's already open!";
                } else {
                    //it's not open, so open it
                    itemFound.setOpen(true);
                    return itemFound.describeContents();
                }
            } else {
                //is not openable
                return "You can't open this.";
            }
        } else {
            //no such item found
            return "That doesn't exist here.";
        }
    }

    private String handlePickUp(String command) {
        String itemName = command.substring(command.indexOf("pick up") + 7);
        itemName = itemName.toLowerCase().trim();

        for(Entity i : currentRoom.getItemList()){
            if(i.getInventoryList() != null){
                if(i.containsItemOfName(itemName) != null &&  !i.isOpen()){
                    return "You can't see a "+ itemName +" here.";
                }            }
        }
        if (user.containsItemOfName(itemName) != null) {
            //if it is already in user's inventory
            return "You already picked that up.";
        } else if (currentRoom.containsItemOfName(itemName) != null &&
                currentRoom.containsItemOfName(itemName).getIsPickUpAble()) {
            //if the room contains the item, pick it up
            Entity item = currentRoom.containsItemOfName(itemName);
            user.addItemToInventory(item);
            currentRoom.removeItemFromRoom(item);
            return "You picked up the " + itemName + ".";
        } else {
            return "You can't do that.";
        }
    }

    private String handleDrop(String command) {
        String itemName = command.substring(command.indexOf("drop") + 4);
        itemName = itemName.toLowerCase().trim();
        if (user.containsItemOfName(itemName) != null) {
            Entity item = user.containsItemOfName(itemName);
            currentRoom.addItemToRoom((Item) item);
            user.removeItemFromInventory(item);
            return "You dropped the " + itemName + ".";
        } else {
            return "You don't have that on you.";
        }
    }

    private String handleTalk(String command) { //need to refactor
        String talkResponse = "";
        String targetCharacterName;
        String chosenTopic;
        if (command.contains("talk to") && command.length() > 7) {
            targetCharacterName = command.substring(command.indexOf("talk to") + 7).trim();
        } else {
            //the command was just "talk"
            targetCharacterName = command.substring(command.indexOf("talk") + 4).trim();
        }
        if(targetCharacterName.contains(" about ")){
            targetCharacterName = targetCharacterName.substring(0, targetCharacterName.indexOf(" about "));
        }

        if (currentRoom.containsCharacterOfName(targetCharacterName) != null && !targetCharacterName.equalsIgnoreCase(user.getName())) {
            //then the character is in the room
            //so talk to it
            Character targetCharacter = (Character) currentRoom.containsCharacterOfName(targetCharacterName);
            if (!command.contains(" about ") && command.length() > 5) {
                if (targetCharacter.getIsPlayersFirstTimeSpeakingTo()) {
                    //then it's the user's first time speaking with this character
                    talkResponse += targetCharacter.getFirstDialogue();
                    talkResponse += "\n You can talk with " + targetCharacter.getName() + " about: " +
                            targetCharacter.getDialogueByTopics().keySet();
                    targetCharacter.setIsPlayersFirstTimeSpeakingTo(false);
                } else {
                    //it's not the first time
                    talkResponse += targetCharacter.getGeneralGreeting();
                }
            } else {
                chosenTopic = command.substring(
                        command.indexOf("about") + 5
                ).trim();
                talkResponse += targetCharacter.beSpokenToAbout(chosenTopic);
            }

        } else{
            talkResponse = "You can't do that or you need to rephrase it.";
        }

        return talkResponse;
    }

    private String handleBlowDown(String command){
        String output = "";
        String blowThis = command.substring((command.indexOf("blow down") + 9));
        blowThis = blowThis.toLowerCase().trim();
        if((blowThis.contains("straw house")) && currentRoom.containsItemOfName("Straw House") != null){
            currentRoom.removeItemFromRoom(currentRoom.containsItemOfName("Straw House"));
            if(currentRoom.containsCharacterOfName("Billy") != null){
                currentRoom.containsCharacterOfName("Billy").setIsEatable(true);
                rooms.get(2).setAccessible(true);
                output = "You blew down the Straw House!" + "\n" + currentRoom.getConnectionsDescription();
            }
            else if(currentRoom.containsItemOfName("Straw House") == null){
                output = "You already blew down the Straw House!";
            }
        }
        else{
            output = "You can't blow this down.";
        }
        return output;
    }

    private String handleEat(String command){
        String output = "";
        String itemName = command.substring(command.indexOf("eat") + 3);
        itemName = itemName.toLowerCase().trim();
        if (currentRoom.containsItemOfName(itemName) != null && currentRoom.containsItemOfName(itemName).getIsEatable()) {
            Entity item = currentRoom.containsItemOfName(itemName);
            currentRoom.removeItemFromRoom(item);
            output += "You ate the" + item.getName() + "!";
            addPoints(10);
        } else if(currentRoom.containsCharacterOfName(itemName) != null && currentRoom.containsCharacterOfName(itemName).getIsEatable()){
            Entity character = currentRoom.containsCharacterOfName(itemName);
            currentRoom.removeCharacter((Character) character);
            output += "You ate " + character.getName() + "!";
            addPoints(10);
        } else if(currentRoom.containsCharacterOfName(itemName) == null){
            output += "You can't eat that or you already ate it.";
        }        else {
            output += "You can't eat that or you already ate it.";
        }
        return output;
    }

    private String handleUse(String command){
        String output = "";
        command = command.substring(command.indexOf("use ") + 4);
        //if command contains "with" and there is a valid word to process after "with"
        if (!command.contains("with") || command.substring(command.indexOf("with")).length() < 5 ){
            output += "That doesn't make sense.";
        } else {
            String itemName = command.substring(0, command.indexOf("with")).trim().toLowerCase();
            String itemName2 = command.substring(command.indexOf("with") + 4).trim().toLowerCase();
            Item item1 = getItemForUse(itemName);
            Item item2 = getItemForUse(itemName2);
            //if both of these items are valid items in the room or in the user's inventory
            if(item1 == null || item2 == null){
                output += "You can't do that.";
            } else{
                Supplier<String> action = item1.getInteraction(item2);
                output = action.get();
            }
        }
        return output;
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

    /**
     * Gets the commands that are possible for the user to use in their current position.
     */

    String getCommandList() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).equals("go")) {
                result.append(commands.get(i)).append(" (direction)");
            } else {
                result.append(commands.get(i));
            }
            if (i < commands.size() - 1) {
                result.append(", ");
            } else {
                result.append(".");
            }
        }
        return result.toString();
    }

    /**
     * Returns the item that the user is requesting to use.
     */

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


    String getTitle(){
        return this.title;
    }

    String getStory(){
        return this.story;
    }

    void setStory(String story){
        this.story = story;
    }

    Room getStartingRoom(){
        return this.startingRoom;
    }

    Room getCurrentRoom(){
        return currentRoom;
    }

    void addPoints(int points){
        score += points;
    }

    public void setUser(String name, String description){
        user.setName("You, " + name);
        user.setDescription(description);
    }

    Character getUser(){
        return user;
    }

    int getScore(){
        return score;
    }
}
