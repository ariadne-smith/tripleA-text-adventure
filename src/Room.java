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

    void removeItemFromRoom(Item item) {
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

    public void setItemList(List<Entity> itemList) {
        this.itemList = itemList;
    }

    public Entity containsItemOfName(String itemName){
        for(Entity i: itemList){
            if(i.getName().equals(itemName)){
                return i;
            }
        }
        return null;
    }

}
