import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents separate locations within a TextAdventure game. Stores whether or not the character has already
 * entered the room, whether or not the Room is accessible, an array of other Rooms and which directions they connect
 * at, along with lists of characters and items contained within the Room.
 */

public class Room {

    private String name;
    private String description;
    private String story;
    private Boolean playerFirstArrives;
    private Boolean accessible;
    private Room[] connections = new Room[6]; //n s e w in out
    private List<Entity> characterList;
    private List<Entity> itemList;

    public Room(String name) {
        this.name = name;
        this.description = null;
        this.story = null;
        this.playerFirstArrives = true;
        this.accessible = true;
        itemList = new ArrayList<>();
        characterList = new ArrayList<>();
    }

    public Room(String name, Room north, Room south, Room east, Room west, Room in, Room out){
        this(name);
        connections[0] = north;
        connections[1] = south;
        connections[2] = east;
        connections[3] = west;
        connections[4] = in;
        connections[5] = out;
    }

    void addItemToRoom(Item item) {
        itemList.add(item);
    }

    void removeItemFromRoom(Entity item) {
        itemList.remove(item);
    }

    void addCharacter(Character c) {
        characterList.add(c);
    }

    void removeCharacter(Character c) {
        characterList.remove(c);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getStory() {
        return story;
    }

    void setStory(String story) {
        this.story = story;
    }

    void setAccessible(boolean access){
        accessible = access;
    }

    boolean getAccessible(){
        return accessible;
    }

    Boolean getPlayerFirstArrives() {
        return playerFirstArrives;
    }

    void setPlayerFirstArrives(Boolean playerFirstArrives) {
        this.playerFirstArrives = playerFirstArrives;
    }

    Room[] getConnections() {
        //return connections;
        return connections.clone();
    }

    void setConnections(Room[] connections) {
        this.connections = connections;
    }

    List<Entity> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Entity> characterList) {
        this.characterList = characterList;
    }

    List<Entity> getItemList() {
        return itemList;
    }

    /**
     * Returns a list of Items contained in the Room and their descriptions to display in the TextAdventure game. If
     * there are no items, returns "There is nothing to see here."
     */

    String getItemListDescription(){
        if(itemList == null || itemList.isEmpty()){
            return "There is nothing else to see here.";
        } else{
            StringBuilder result = new StringBuilder("Here, you can see: \n");
            for (Entity item : itemList){
                result.append("a(n) ").append(item.getName());
                if(item.getDescription()!= null){
                    result.append(", ").append(item.getDescription()).append("\n");
                }
            }
            return result.toString();
        }
    }

    /**
     * Returns a list of Characters contained in the Room and their descriptions to display in the TextAdventure game.
     * If there are no Characters, returns "There's no one here."
     */

    String getCharacterListDescription(){
        if(characterList == null || characterList.isEmpty()){
            return "There's no one here.";
        } else {
            StringBuilder result = new StringBuilder("Present, there is: \n");
            for (Entity character : characterList){
                result.append(character.getName());
                if(character.getDescription() != null){
                    result.append(": ").append(character.getDescription()).append("\n");
                }
            }
            return result.toString();
        }
    }

    public void setItemList(List<Entity> itemList) {
        this.itemList = itemList;
    }

    /**
     * Takes the String name of an Item and returns the Item if it exists in this Room. Otherwise, returns
     * null.
     */

    Entity containsItemOfName(String itemName){
        for(Entity i: itemList){
            if(i.getName().equalsIgnoreCase(itemName)){
                return i;
            }
            if (i.containsItemOfName(itemName) != null){
                return i.containsItemOfName(itemName);
            }
        }
        return null;
    }

    /**
     * Takes the String name of an Character and returns the Character if it exists in this Room. Otherwise, returns
     * null.
     */

    Entity containsCharacterOfName(String charName){
        for(Entity i: characterList){
            if(i.getName().equalsIgnoreCase(charName)){
                return i;
            }
        }
        return null;
    }

    /**
     * Returns the direction that corresponds with each index of the connections array stored within the Room class.
     */

    private String indexToDirection(int index){
        if(index == 0){
            return "north";
        }
        if(index == 1){
            return "south";
        }
        if(index == 2){
            return "east";
        }
        if(index == 3){
            return "west";
        }
        if(index == 4){
            return "in";
        }
        if(index == 5){
            return "out";
        }
        else return null;
    }

    /**
     * Returns a description of the possible directions to go from this Room.
     */

    String getConnectionsDescription(){
        StringBuilder result = new StringBuilder("You may go ");
        if (connections == null){
            result = new StringBuilder("You may not move.");
            return result.toString();
        }
        for(int i = 0; i < connections.length; i ++){
            if (connections[i] != null && connections[i].getAccessible()){
                if(connections.length > 2){
                    result.append(indexToDirection(i)).append(", ");
                }
                else if (connections.length == 2){
                    result.append(indexToDirection(i)).append("and ").append(indexToDirection(i + 1)).append(".");
                    break;
                }
                else{
                    result.append(indexToDirection(i));
                }
            }
        }
        if (result.toString().endsWith(", ")){
            result = new StringBuilder(result.substring(0, result.length() - 2) + ".");
        }
        return result.toString();
    }

}
