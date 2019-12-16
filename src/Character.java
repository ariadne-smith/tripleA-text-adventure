import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A child class of entity that contains methods that allow the characters to talk and deals with the game's inventory
 */

public class Character extends Entity{

    private List<Entity> inventory;
    private boolean playersFirstTimeSpeakingTo;
    private String firstDialogue, generalGreeting;
    private Map<String, String> dialogueByTopics;
    private boolean isPickUpAble = false;

    public Character(String name, String description, List<Entity> inventory) {
        this.name = name;
        this.description = description;
        this.inventory = inventory;
        this.playersFirstTimeSpeakingTo = true;
        isEatable = false;
        this.firstDialogue = "This is my first dialogue.";
        this.generalGreeting = "Well hello there";
        dialogueByTopics = new HashMap<>();
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

    /**
     * Methods that relate to 'PlayersFirstTimeSpeakingTo' document the first instance that the user interacts with
     * characters that have been placed throughout the adventure. So, the second time the character is spoken to, it
     * will not print out the same introductory message.
     */

    public boolean getIsPlayersFirstTimeSpeakingTo() {
        return playersFirstTimeSpeakingTo;
    }

    public void setIsPlayersFirstTimeSpeakingTo(boolean playerFirstSpeaksTo) {
        this.playersFirstTimeSpeakingTo = playerFirstSpeaksTo;
    }

    public String getFirstDialogue() {
        return firstDialogue;
    }

    public void setFirstDialogue(String firstDialogue) {
        this.firstDialogue = firstDialogue;
    }

    public String getGeneralGreeting() {
        return generalGreeting;
    }

    public void setGeneralGreeting(String generalGreeting) {
        this.generalGreeting = generalGreeting;
    }

    /**
     * An map, (dialogueByTopics) has been written for each character that has the ability to 'speak' to the user.
     * These methods make calls to the specific maps, each 'topic' key has 'response' values that correspond to it
     * that the user receives upon interacting with the character.
     */

    public Map<String, String> getDialogueByTopics() {
        return dialogueByTopics;
    }

    public void setDialogueByTopics(Map<String, String> dialogueByTopics) {
        this.dialogueByTopics = dialogueByTopics;
    }

    public void populateDialogueByTopics(String topic, String dialogue){
        this.dialogueByTopics.put(topic, dialogue);
    }

    public String beSpokenToAbout(String topic){ //test this and figure out char null
        if(dialogueByTopics.isEmpty()){
            return "They have nothing to say to you.";
        } else {
            return dialogueByTopics.getOrDefault(topic, "You can't talk to them about that.");
        }
    }

    @Override
    public String toString() {
        return "This is " + name + ". " + description;
    }

    public String getInventoryList() {
        String result = "";
        if(inventory == null || inventory.isEmpty()){
            return "There's nothing in your inventory.";
        }
        for (Entity item : inventory){
            result = result + " " + item.getName();
            if(item.getDescription()!= null){
                result = result + "\n" + item.getDescription();
            }
        }
        return result;
    }

    public String getListOfItems(){
        String result = "";
        if(inventory == null || inventory.isEmpty()){
            return "There's nothing in your inventory.";
        }
        for (Entity item : inventory){
            result = result + " | " + item.getName();
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

    /**
     * These methods describe the items throughout the rooms and what actions make happen to them.
     */

    @Override
    public boolean getIsPickUpAble() {
        return isEatable;
    }

    @Override
    public boolean getIsEatable() {
        return isEatable;
    }

    @Override
    public void setIsEatable(boolean set) {
        isEatable = set;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

}
