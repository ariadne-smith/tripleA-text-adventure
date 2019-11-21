import java.util.List;

public class Character extends Entity{

    public Character(String name, String description, List<Entity> inventory) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }
    
    @Override
    public void addItem(Entity item) {

    }

    @Override
    public void removeItem(Entity item) {

    }

    @Override
    public String toString() {
        return "This is " + name + ". " + description;
    }


}
