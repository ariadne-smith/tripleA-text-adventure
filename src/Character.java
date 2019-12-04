import java.util.ArrayList;
import java.util.List;

public class Character extends Entity{

    private List<Entity> inventory;

    public Character(String name, String description, List<Entity> inventory) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
    }

    @Override
    public void addItemToInventory(Entity item) {
        if(inventory == null){
            inventory = new ArrayList<>(List.of(item));
        } else {
            inventory.add(item);
        }
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
            result = result + " " + item.getName();
            if(item.getDescription()!= null){
                result = result + "\n" + item.getDescription() + "\n";
            }
        }
        return result;
    }

    public Entity containsItemOfName(String itemName){
        if (inventory == null){
            return null;
        }
        for(Entity item : inventory){
            if(itemName.equals(item.getName().toLowerCase())){
                return item;
            }
            if(item.containsItemOfName(itemName) != null){

            }
        }
        return null;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

}
