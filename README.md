# tripleA-text-adventure

<b> Game </b>

- Character player
- List <Room> rooms
- List <String> commands
- CanvasWindow canvas
- GraphicsText history— for testing use System.out.print for each input and return
- TextField input
- Compass compass


<b> Entity </b>

- String name
- String description
- List<Entity> inventory
- void addItemToInventory
- void removeItemFromInventory

<b> Character extends Entity </b>

- Talking

<b> Item extends Entity </b>




<b> Room </b>

- List<Entity> characters
- List<Entity> items
- String description
- String story
- Boolean firstTime
- Room[] connections
- Void addItem
- Void removeItem
- void addCharacter
- void removeCharacter

<b> Compass </b>

- GraphicsGroup icon
- Button north
- Button south
- Button east
- Button west



<b> Other features </b>

- score implementation
- timer affecting score
- game-player interactions with certain inputs
- talking & dialogue trees