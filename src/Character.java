import java.util.List;

public class Character extends Entity{

    public Character(String name, String description, List<Entity> inventory) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }
    
    @Override
    public void addItemToInventory(Entity item) {
        inventory.add(item);
    }

    @Override
    public void removeItemFromInventory(Entity item) {
        inventory.remove(item);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    @Override
    public String toString() {
        return "This is " + name + ". " + description;
    }

    public String getInventoryList() {
        String result = "";
        for (Entity item : inventory){
            result = result + " " + item.getDescription();
        }
        return result;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

}
