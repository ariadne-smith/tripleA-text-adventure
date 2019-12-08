import java.util.List;

public abstract class Entity {
    String name;
    String description;
    List<Entity> inventory;

    public abstract void addItemToInventory(Entity item);

    public abstract void removeItemFromInventory(Entity item);

    public abstract String getInventoryList();

    public abstract String getDescription();

    public abstract String getName();

    public abstract Entity containsItemOfName(String itemName);

    public abstract boolean getIsPickUpAble();

    @Override
    public abstract String toString();

}
