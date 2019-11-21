import java.util.List;

public abstract class Entity {
    String name;
    String description;
    List<Entity> inventory;

    public abstract void addItem(Entity item);

    public abstract void removeItem(Entity item);

    @Override
    public abstract String toString();

}
