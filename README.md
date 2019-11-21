# tripleA-text-adventure

Game
Character player
List <Room> rooms
List <String> commands
CanvasWindow canvas
GraphicsText history— for testing use System.out.print for each input and return
TextField input
Compass compass


Entity
String name
String description
List<Entity> inventory
void addItemToInventory
void removeItemFromInventory

Character extends Entity
Talking

Item extends Entity


Room
List<Entity> characters
List<Entity> items
String description
String story
Boolean firstTime
Room[] connections
Void addItem
Void removeItem
void addCharacter
void removeCharacter

Compass
GraphicsGroup icon
Button north
Button south
Button east
Button west



