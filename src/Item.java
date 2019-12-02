import java.util.List;

public class Item extends Entity {

    boolean isOpenable;
    boolean isOpen;

    public Item (String name, String description, List<Entity> inventory){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        isOpenable = false;
        isOpen = false;
    }

    public Item (String name, String description, List<Entity> inventory, boolean canBeOpened, boolean currentlyOpen){
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        isOpenable = canBeOpened;
        isOpen = currentlyOpen;
    }

    @Override
    public void addItemToInventory(Entity item) {
        inventory.add(item);
    }

    @Override
    public void removeItemFromInventory(Entity item) {
        inventory.remove(item);
    }

    @Override
    public String getInventoryList() {
        String result = "";
        for (Entity item : inventory){
            result = result + " " + item.getDescription();
        }
        return result;
    }

    public String describeContents() {
        String desc = "Inside here there is ";
        int ct = 0;
        for (Entity i : inventory) {
            ct++;
        }
        for (Entity i : inventory) {
            {
                desc += "a(n) " + i.getName();
                if (ct == 1) {
                    desc += ".";
                    return desc;
                } else {
                    ct--;
                }
            }
        }
        return desc;
    }

    @Override
    public String getDescription() {
        return null;
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

    public void setOpen(boolean open) {
        isOpen = open;
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

    @Override
    public String toString() {
        return "This is " + name + ". " + description;
    }
}
