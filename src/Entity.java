import java.util.List;

/**
 * Abstract class that is extended by the classes Item and Character, both used in the TextAdventure class.
 */

public abstract class Entity {
    String name;
    String description;
    List<Entity> inventory;
    boolean isEatable;
    boolean isOpenable;

    public abstract void addItemToInventory(Entity item);

    public abstract void removeItemFromInventory(Entity item);

    public abstract String getInventoryList();

    public abstract String getDescription();

    public abstract String getName();

    public abstract Entity containsItemOfName(String itemName);

    public abstract boolean getIsPickUpAble();

    public abstract boolean getIsEatable();

    public abstract void setIsEatable(boolean set);

    public abstract boolean isOpenable();

    public abstract boolean isOpen();

    @Override
    public abstract String toString();

}
