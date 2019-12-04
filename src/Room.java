import java.util.ArrayList;
import java.util.List;

public class Room {

    private String name;
    private String description;
    private String story;
    private Boolean playerFirstArrives;
    private Room[] connections = new Room[6]; //n s e w in out
    private List<Entity> characterList;
    private List<Entity> itemList;

    public Room(String name) {
        this.name = name;
        this.description = null;
        this.story = null;
        this.playerFirstArrives = true;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Boolean getPlayerFirstArrives() {
        return playerFirstArrives;
    }

    public void setPlayerFirstArrives(Boolean playerFirstArrives) {
        this.playerFirstArrives = playerFirstArrives;
    }

    public Room[] getConnections() {
        //return connections;
        return connections.clone();
    }

    public void setConnections(Room[] connections) {
        this.connections = connections;
    }

    public List<Entity> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Entity> characterList) {
        this.characterList = characterList;
    }

    public List<Entity> getItemList() {
        return itemList;
    }

    public String getItemListDescription(){
        if(itemList == null || itemList.isEmpty()){
            return "There is nothing to see in this room.";
        } else{
            String result = "In this room, you can see: \n";
            for (Entity item : itemList){
                result = result + "a(n) " + item.getName();
                if(item.getDescription()!= null){
                    result = result + ", " + item.getDescription() + "\n";
                }
            }
            return result;
        }
    }

    public void setItemList(List<Entity> itemList) {
        this.itemList = itemList;
    }

    public Entity containsItemOfName(String itemName){
        for(Entity i: itemList){
            if(i.getName().equals(itemName)){
                return i;
            }
            if (i.containsItemOfName(itemName) != null){
                return i.containsItemOfName(itemName);
            }
        }
        return null;
    }

    public String indexToDirection(int index){
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
     * Returns a description of the possible directions to go from a room.
     * @return
     */

    public String getConnectionsDescription (){
        String result = "You may go ";
        if (connections == null){
            result = "You may not move.";
            return result;
        }
        for(int i = 0; i < connections.length; i ++){
            if (connections[i] != null){
                if(connections.length > 2){
                    result = result + indexToDirection(i) +  ", ";
                }
                else if (connections.length == 2){
                    result = result + indexToDirection(i)+  "and " + indexToDirection(i + 1) + ".";
                    break;
                }
                else{
                    result = result + indexToDirection(i);
                }
            }
        }
        if (result.endsWith(", ")){
            result = result.substring(0, result.length()-2) + ".";
        }
        return result;
    }

}
