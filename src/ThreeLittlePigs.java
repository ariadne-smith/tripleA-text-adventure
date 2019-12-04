import java.util.ArrayList;
import java.util.List;

public class ThreeLittlePigs {
    public static void main(String[] args) {
        Room woods = new Room("Woods");
        woods.setStory("You are a ferocious wolf, hungry, and tired after a long excursion " +
                "out in the deep dark woods. You have just heard tell that there are three little " +
                "pigs who have recently done some construction in the neighborhood…");
        woods.setDescription("These woods are dark and scary.");


        Room house1 = new Room ("The Straw House");
        house1.setStory("You’ve trekked out of the woods and followed the rocky dirt road until you" +
                " find a neighborhood full of questionably constructed straw houses. Your nose twitches " +
                "and you realize the one right in front of you quite possibly contains bacon…");
        house1.setDescription("In front of you is a shoddily built straw house.");


        Room house2 = new Room("The Stick House");
        house2.setStory("You decide to look for some more food and continue on to the next neighborhood," +
                " filled with stick houses. You sniff the air and have a good feeling about a house to your left…");
        house2.setDescription("Here is the site of another flimsy house.");


        Room house3 = new Room("The Brick House");
        house3.setStory("You decide to look for some more food and continue on to the next neighborhood, filled with " +
                "brick houses. You sniff the air and have a good feeling about a house up ahead, which is made of bricks" +
                "and has a lovely skylight");
        house3.setDescription("Here is the site of the brick house. You can see a skylight.");


        Room house3Interior = new Room("Inside the brick house.");
        house3Interior.setStory("Aha! Here is the delicious pig! Your stomach is rumbling . . . ");

        Character owl = new Character("Wise Owl", "A wise elderly owl who might have some helpful advice", null);
        Character pig1 = new Character("Billy", "A fat, delicious-looking pig", null);
        Character pig2 = new Character("Reginald", "A fat, delicious-looking pig", null);
        Character pig3 = new Character("Spike", "A fat, delicious-looking pig", null);

        Item leafBlower = new Item("leaf blower", "a gardening tool that generates a lot of wind", null);
        Item brick = new Item("brick", "looks like a leftover brick from building a house", null);

        woods.addCharacter(owl);
        house1.addCharacter(pig1);
        house2.addCharacter(pig2);
        house2.addItemToRoom(leafBlower);
        house3.addItemToRoom(brick);
        house3Interior.addCharacter(pig3);


        woods.setConnections(new Room[] {null, null, house1, null, null, null});
        house1.setConnections(new Room[] {null, house2, null, woods, null, null});
        house2.setConnections(new Room[] {house1, null, house3, null, null, null});
        house3.setConnections(new Room[] {null, null, null, house2, house3Interior, null});
        house3Interior.setConnections(new Room[] {null, null, null, null, null, house3});

        ArrayList<String> commands = new ArrayList<>(List.of("go", "show inventory", "pick up", "drop", "open", "eat"));
        TextAdventure threePigs = new TextAdventure("", commands,
                new ArrayList<> (List.of(woods, house1, house2, house3, house3Interior)));
        threePigs.startGame();
        threePigs.runGame();

    }
}
