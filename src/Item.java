import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Extends entity class and represents items in the TextAdventure class that the user can interact with.
 */
public class Item extends Entity {

    boolean isOpenable;
    boolean isOpen;
    private boolean isPickupable;
    private boolean isEatable;
    private Map<Entity, Supplier<String>> interactionsByItem = new HashMap<>();

    public Item (String name, String description, List<Entity> inventory){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        isOpenable = false;
        isOpen = false;
        isPickupable = true;
        isEatable = false;
    }

    public Item (String name, String description, List<Entity> inventory, boolean canBeOpened, boolean currentlyOpen){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        isOpenable = canBeOpened;
        isOpen = currentlyOpen;
    }

    /**
     * Populates the HashMap of interactions with every item in the game with the default response "That does nothing."
     */

    void populateInteractions(List<Entity> itemList){
        for(Entity i : itemList){
            if(!i.getName().equals(this.getName())){
                interactionsByItem.put(i, () -> "That does nothing.");
            }
        }
    }

    void addInteraction(Item item, Supplier<String> interaction){
        interactionsByItem.put(item, interaction);
    }

    Supplier<String> getInteraction(Item item){
        return interactionsByItem.get(item);
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

    @Override
    public String getInventoryList() {
        StringBuilder result = new StringBuilder();
        for (Entity item : inventory){
            result.append(" ").append(item.getDescription());
        }
        return result.toString();
    }

    /**
     * Returns a list of what is contained in the inventory of an Item formatted to be displayed in TextAdventure.
     */

    String describeContents() {
        StringBuilder desc = new StringBuilder("Inside here there is ");
        int ct = 0;
        for (Entity ignored : inventory) {
            ct++;
        }
        for (Entity i : inventory) {
            {
                desc.append("a(n) ").append(i.getName());
                if (ct == 1) {
                    desc.append(".");
                    return desc.toString();
                } else {
                    ct--;
                }
            }
        }
        return desc.toString();
    }

    @Override
    public String getDescription() {
        return description;
    }

    public boolean isOpenable() {
        return isOpenable;
    }

    public void setOpenable(boolean openable) {
        isOpenable = openable;
    }

    public boolean isOpen() {
        return isOpen;
    }

    void setOpen(boolean open) {
        isOpen = open;
    }

    void setPickUpAble(boolean set){
        isPickupable = set;
    }

    @Override
    public boolean getIsPickUpAble(){
        return isPickupable;
    }

    public boolean getIsEatable(){
        return isEatable;
    }

    @Override
    public void setIsEatable(boolean set) {
        isEatable = set;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Entity> getInventory() {
        return inventory;
    }

    public void setInventory(List<Entity> inventory) {
        this.inventory = inventory;
    }

    /**
     * Takes the String name of an Item and returns the Item if it exists in this Item's inventory. Otherwise, returns
     * null.
     */

    public Entity containsItemOfName(String itemName){
        if (inventory == null){
            return null;
        } else {
            for (Entity item : inventory) {
                if (itemName.equals(item.getName().toLowerCase()) && this.isOpen) {
                    return item;
                }
            }
            return null;
        }
    }

    @Override
    public String toString() {
        return "This is " + name + ". " + description + " " + isOpen + " " + isOpenable;
    }
}
