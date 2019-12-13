import java.util.ArrayList;
import java.util.List;

public class TextAdventure {
    private List<String> commands;
    private String title;
    private String story;
    private Character user;
    private List<Room> rooms;
    private Room startingRoom;
    private Room currentRoom;
    private int score;
    public List<Entity> allGameItems = new ArrayList<>();
    public TextAdventureDisplay tad = new TextAdventureDisplay();
    //public static Scanner scanner;

    public TextAdventure(String title, ArrayList<String> commands, ArrayList<Room> rooms) {
        this.title = title;
        this.commands = commands;
        this.rooms = rooms;
        startingRoom = rooms.get(0);
        currentRoom = startingRoom;
        user = new Character("You, a wolf", "A big bad wolf", null);
        //scanner = new Scanner();

        for(Room r : rooms){
            allGameItems.addAll(r.getItemList());
        }
    }

    public TextAdventure(String title, ArrayList<String> commands, ArrayList<Room> rooms, Room startingRoom) {
        this.title = title;
        this.story = story;
        this.commands = commands;
        this.rooms = rooms;
        this.startingRoom = startingRoom;
        currentRoom = startingRoom;
        user = new Character(null, null, null);
        score = 0;
        //scanner = new Scanner(System.in);

        for(Room r : rooms) {
            allGameItems.addAll(r.getItemList());
        }
        this.populateInteractions();

    }

    public void startGame() {
        startingRoom.addCharacter(user);
        System.out.println("Current score: " + score);
    }

    public String runGame(String command) {
        while (true) {
            // = scanner.nextLine();
            if (command.contains("quit game")) {
                //System.out.println("Quitting game");
                return "Quitting game";
            }
            if (checkCommand(command)) {
                return doCommand(command);
            } else {
                //System.out.println("You can't do that.");
                return "You can't do that.";
            }
        }
    }

    public boolean checkCommand(String command){
        command = command.toLowerCase().trim();
        if(command.contains("use ")){
            return true;
        }
        if (command.contains("show inventory")) {
            return true;
        }
        if (command.contains("go ")) {
            if (checkGo(command)) {
                return true;
            } else {
                return false;
            }
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
        if (command.contains("blow down")){
            return true;
        }
        else return false;
    }

    public String doCommand(String command) {
        command = command.toLowerCase().trim();
        if(command.contains("use ")){
            return handleUse(command);
            //return true;
        }
        if (command.contains("show inventory")) {
            //System.out.println(user.getInventoryList());
            //return true;
            return user.getInventoryList();
        }
        if (command.contains("go ")) {
            if (checkGo(command)) {
                //return true;
                return handleGo(command);
            } /*else {
                return false;
            }*/
        }
        if (command.contains("open ")) {
            return handleOpen(command);
            //return true;
        }
        if (command.contains("pick up ")) {
            return handlePickUp(command);
            //return true;
        }
        if (command.contains("drop ")) {
            return handleDrop(command);
            //return true;
        }
        if (command.contains("talk ")) {
            return handleTalk(command);
            //return true;
        }
        if (command.contains("eat")){
            return handleEat(command);
            //return true;
        }  //else return false;
        if(command.contains("blow down")){
            return handleBlowDown(command);
        }
        else return "You can't do that.";
    }

    public String moveUserTo(Room room) {
        String output = "";
        currentRoom.removeCharacter(user);
        currentRoom = room;
        currentRoom.addCharacter(user);
        if (currentRoom.getPlayerFirstArrives()) {
            System.out.println("Current score: " + score);
            output += currentRoom.getStory();
            currentRoom.setPlayerFirstArrives(false);
        } else{
            score--;
            System.out.println("Current score: " + score);
        }
        output += "\n" + currentRoom.getDescription();
        output += "\n" + currentRoom.getConnectionsDescription();
        if (currentRoom.getItemListDescription() != null) {
            output += "\n" + currentRoom.getCharacterListDescription();
            output += "\n" + currentRoom.getItemListDescription();
        }
        System.out.println("moveuserto" + output);
        return output;
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

    private boolean checkGo(String command){
        String direction = command.substring(command.indexOf("go") + 2);
        direction = direction.toLowerCase().trim();
        if (direction.contains("north") && currentRoom.getConnections()[0] != null) {
            //moveUserTo(currentRoom.getConnections()[0]);
            return true;
        }
        if (direction.contains("south") && currentRoom.getConnections()[1] != null) {
            //moveUserTo(currentRoom.getConnections()[1]);
            return true;
        }
        if (direction.contains("east") && currentRoom.getConnections()[2] != null) {
            //moveUserTo(currentRoom.getConnections()[2]);
            return true;
        }
        if (direction.contains("west") && currentRoom.getConnections()[3] != null) {
            //moveUserTo(currentRoom.getConnections()[3]);
            return true;
        }
        if (direction.contains("in") && currentRoom.getConnections()[4] != null) {
            //moveUserTo(currentRoom.getConnections()[4]);
            return true;
        }
        if (direction.contains("out") && currentRoom.getConnections()[5] != null) {
            //moveUserTo(currentRoom.getConnections()[5]);
            return true;
        } else {
//            System.out.println("Cannot do that. Try again.");
            return false;
        }
    }

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
//            System.out.println("Cannot do that. Try again.");
            return "Cannot do that. Try again.";
        }
    }

    private String handleOpen(String command) {
        String itemName = command.substring(command.indexOf("open") + 5);
        itemName = itemName.trim().toLowerCase();
        Item itemFound = (Item) currentRoom.containsItemOfName(itemName);
        if (itemFound != null) {
            if (itemFound.isOpenable) {
                if (itemFound.isOpen) {
                    //already open
                    //System.out.println("It's already open!");
                    return "It's already open!";
                } else {
                    //it's not open, so open it
                    itemFound.setOpen(true);
                    //System.out.println((itemFound.describeContents()));
                    return itemFound.describeContents();
                }
            } else {
                //is not openable
                //System.out.println("You can't open this.");
                return "You can't open this.";
            }
        } else {
            //no such item found
            //System.out.println("That doesn't exist here.");
            return "That doesn't exist here.";
        }
    }

    private String handlePickUp(String command) {
        String itemName = command.substring(command.indexOf("pick up") + 7);
        itemName = itemName.toLowerCase().trim();

        if (user.containsItemOfName(itemName) != null) {
            //if it is already in user's inventory
            //System.out.println("You already picked that up.");
            return "You already picked that up.";
        } else if (currentRoom.containsItemOfName(itemName) != null) {
            //if the room contains the item, pick it up
            Entity item = currentRoom.containsItemOfName(itemName);
            user.addItemToInventory(item);
            currentRoom.removeItemFromRoom((Item) item);
            //System.out.println("You picked up the " + itemName + ".");
            return "You picked up the " + itemName + ".";
        } else {
            //System.out.println("You can't do that.");
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
            //System.out.println("You dropped the " + itemName + ".");
            return "You dropped the " + itemName + ".";
        } else {
            //System.out.println("You don't have that on you.");
            return "You don't have that on you.";
        }
    }

    private String handleTalk(String command) { //need to refactor
        String talkResponse = "";
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

    private String handleEat(String command){
        String output = "";
        String itemName = command.substring(command.indexOf("eat") + 3);
        itemName = itemName.toLowerCase().trim();
        if (currentRoom.containsItemOfName(itemName) != null && currentRoom.containsItemOfName(itemName).getIsEatable()) {
            Entity item = currentRoom.containsItemOfName(itemName);
            currentRoom.removeItemFromRoom((Item) item);
            output += "You ate the" + item.getName() + "!";
        } else if(currentRoom.containsCharacterOfName(itemName) != null && currentRoom.containsCharacterOfName(itemName).getIsEatable()){
            Entity character = currentRoom.containsCharacterOfName(itemName);
            currentRoom.removeCharacter((Character) character);
            output += "You ate " + character.getName() + "!";
        } else{
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
            System.out.println(itemName);
            System.out.println(itemName2);
            //if both of these items are valid items in the room or in the user's inventory
            if(item1 == null || item2 == null){
                output += "You can't do that.";
            } else{
                Runnable action = item1.getInteraction(item2);
                action.run();
            }
        }
        return output;
    }

    private String handleBlowDown(String command){
        String output = "";
        String blowThis = command.substring((command.indexOf("blow down") + 9));
        blowThis = blowThis.toLowerCase().trim();
        if((blowThis.contains("straw house")) && currentRoom.containsItemOfName("Straw House") != null){
            currentRoom.removeItemFromRoom(currentRoom.containsItemOfName("Straw House"));
            if(currentRoom.containsCharacterOfName("Billy") != null){
                currentRoom.containsCharacterOfName("Billy").setIsEatable(true);
            }
            rooms.get(2).setAccessible(true);
            output = "You blew down the Straw house!" + "\n" +currentRoom.getConnectionsDescription();
            //print out: "You blew the Straw House down!"
        }
        else if(currentRoom.containsItemOfName("Straw House") == null){
            output = "You already blew down the Straw House!";
        }
        else{
            output = "You can't blow this down.";
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

    public Room getCurrentRoom(){
        return currentRoom;
    }

    public void addPoints(int points){
        score += points;
    }

    public void setUser(String name, String description){
        user.setName("You, " + name);
        user.setDescription(description);
    }
}
