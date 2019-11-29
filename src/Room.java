import java.lang.reflect.Array;
import java.util.Collections;
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
}
