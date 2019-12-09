import java.util.List;

public abstract class Entity {
    String name;
    String description;
    List<Entity> inventory;
    boolean isEatable;

    public abstract void addItemToInventory(Entity item);

    public abstract void removeItemFromInventory(Entity item);

    public abstract String getInventoryList();

    public abstract String getDescription();

    public abstract String getName();

    public abstract Entity containsItemOfName(String itemName);

    public abstract boolean getIsPickUpAble();

    public abstract boolean getIsEatable();

    public abstract void setIsEatable(boolean set);

    @Override
    public abstract String toString();

}
