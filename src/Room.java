import java.lang.reflect.Array;
import java.util.Collections;
import java.util.List;

public class Room {

String name;
String description;
String story;
Boolean playerFirstArrives;
Room[] connections = new Room[6]; //n s e w in out
List<Entity> characterList;
List<Entity> itemList;

public Room(String name){
    this.name = name;
    this.description = null;
    this.story = null;
    this.playerFirstArrives = true;
}

void addItemToRoom(){}

void removeItemFromRoom(){}

void addCharacter(){}

void removeCharacter(){}

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
