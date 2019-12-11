import java.util.ArrayList;
import java.util.List;

public class ThreeLittlePigs {
    public static TextAdventure threeLittlePigs;
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
//        house2.setAccessible(false);// must set accessible again after the first pig is eaten

        Room house3 = new Room("The Brick House");
        house3.setStory("You decide to look for some more food and continue on to the next neighborhood, filled with " +
                "brick houses. You sniff the air and have a good feeling about a house up ahead, which is made of bricks " +
                "and has a lovely skylight");
        house3.setDescription("Here is the site of the brick house. You can see a skylight.");
//        house3.setAccessible(false);// must set accessible again after the second pig is eaten

        Room house3Interior = new Room("Inside the brick house.");
        house3Interior.setStory("Aha! Here is the delicious pig! Your stomach is rumbling . . . ");
//        house3Interior.setAccessible(false);// must set accessible again after the window is broken

        Room woods2 = new Room("More Woods");
        woods2.setStory("You're feeling pretty good now, having gobbled up some pigs and successfully stopped your stomach from growling." +
                "You walk out of the pig neighborhood and back towards the forest. " +
                "But you have a feeling that you're not out of the woods yet-- literally. There's a pig cop here, and if you don't get out of here fast, " +
                "you might find yourself in trouble again.");
        woods2.setDescription("A dark and ominous forest.");
//        woods2.setAccessible(false); // must set accessible again after the third pig is eaten

        Room river = new Room ("River shore");
        river.setStory("You've run from the pig cop into another section of woods, but there's a wide rushing river in front of you. Surely you won't be able " +
                        "to cross it without some sort of bridge . . .");
        river.setDescription("A river cuts through the forest, rushing by quickly." );

        Room home = new Room("Home");
        home.setStory("You're home! After a long and harrowing trip through the woods and a neighborhood full of pigs, you've managed to evade the cops" +
                "and arrive home. Your family cheers when they see you and you swear to never trek into the woods without some extra provisions" +
                "ever again." );
        home.setDescription("");
//        home.setAccessible(false); //must set accessible again after the river is crossed

        Room woods3 = new Room("Back into the woods");
        woods3.setStory("Congrats! You made it through a day of pig-eating and law-evading! After a good nights rest you are ready to" +
                " venture back out into the woods and try your luck at finding breakfast. You've recently heard that Grandma Riding-Hood" +
                "has fallen ill...Perhaps this is a good opportunity...");
        woods3.setDescription("A dark and ominous forest.");

        Character owl = new Character("Owl", "A wise elderly owl who might have some helpful advice", null);
        owl.setFirstDialogue("I'm an owl, hoot hoot.");
        owl.setGeneralGreeting("Hello again. Hoot hoot!");
        owl.populateDialogueByTopics("advice", "That straw house over there looks pretty flimsy. Even a slight breeze could knock it down!" +
                "Hoot hoot!");
        owl.populateDialogueByTopics("owl things", "I eat mice. Hoot hoot!");
        owl.populateDialogueByTopics("the forest", "It sure is dark and scary in here. Hoot hoot!");

        Character pig1 = new Character("Billy", "A fat, delicious-looking pig", null);
        pig1.setFirstDialogue("Hi, I'm Billy, the pig! You're not a wolf, are you?");
        pig1.setGeneralGreeting("It's Billy the pig again!");
        pig1.populateDialogueByTopics("new house", "I've just built a new house out of straw! I think it looks great.");
        pig1.setIsEatable(true);

        Character pig2 = new Character("Reginald", "A fat, delicious-looking pig", null);
        pig2.setFirstDialogue("Hi, I'm Reginald, the pig! Have you heard from my brothers?");
        pig2.setGeneralGreeting("It's Reginald again. I'm a little worried about my brothers.");
        pig2.populateDialogueByTopics("new house", "I just built a house out of sticks. I bet it can withstand anything!");
        pig2.setIsEatable(true);

        Character pig3 = new Character("Spike", "A fat, delicious-looking pig", null);
        pig3.setFirstDialogue("What's up? I'm Spike, the pig! Have you seen a wolf around here? I think one is prowling around . . .");
        pig3.setGeneralGreeting("It's Reginald again. You should stay away, there's a wolf on the loose!");
        pig3.populateDialogueByTopics("the wolf", "I think my brothers were eaten by a wolf! Thankfully I " +
                "have my strong brick house to protect me.");
        pig3.setIsEatable(true);

        Character cop = new Character("Officer Pig", "A pig cop looking to find the wolf who ate his friends", null);
        cop.setFirstDialogue("I'm Officer Pig. Hey! You look like the wolf who ate my friends!");
        cop.setGeneralGreeting("I'm Officer Pig.");
        cop.populateDialogueByTopics("wolf", "A wolf has been terrorizing the neighborhood. And you look quite a lot like a wolf . . .");

        Item leafBlower = new Item("leaf blower", "a gardening tool that generates a lot of wind", null);
        Item brick = new Item("brick", "looks like a leftover brick from building a house", null);
        Item window = new Item ("window", "a nice skylight on the roof of the brick house", null);
        window.setPickUpAble(false);
        Item rock = new Item("rock", "a flat gray rock", null);
        Item vine = new Item("vine", "a long green vine suspended from a tree branch", null);
        vine.setPickUpAble(false);

        woods.addCharacter(owl);
        house1.addCharacter(pig1);
        house2.addCharacter(pig2);
        house2.addItemToRoom(leafBlower);
        house3.addItemToRoom(brick);
        house3.addItemToRoom(window);
        house3Interior.addCharacter(pig3);
        river.addItemToRoom(rock);
        river.addItemToRoom(vine);

        woods.setConnections(new Room[] {null, null, house1, null, null, null});
        house1.setConnections(new Room[] {null, house2, null, woods, null, null});
        house2.setConnections(new Room[] {house1, null, house3, null, null, null});
        house3.setConnections(new Room[] {null, null, null, house2, house3Interior, null});
        house3Interior.setConnections(new Room[] {null, null, null, null, null, house3});
        woods2.setConnections(new Room[] {house3, null, null, null, null, null});
        //river
        //home
        woods3.setConnections(new Room[] {house3, null, null, null, null, null});

        //N , S, E, W, in, out



        ArrayList<String> commands = new ArrayList<>(List.of("go", "show inventory", "pick up", "drop", "open", "eat", "talk"));
        ArrayList<Room> rooms = new ArrayList<>((List.of(woods, house1, house2, house3, house3Interior, woods2, river, home)));
        threeLittlePigs = new TextAdventure("Three Little Pigs", commands,
                rooms);

        //add house1 and house2 objects
        threeLittlePigs.addInteraction(leafBlower, null, () ->{
            house2.setAccessible(true);
            threeLittlePigs.addPoints(10);
            pig1.setIsEatable(true);
            System.out.println("You have destroyed this house.");
        });

        threeLittlePigs.addInteraction(leafBlower, null, () ->{
            house3.setAccessible(true);
            threeLittlePigs.addPoints(10);
            pig2.setIsEatable(true);
            System.out.println("You have destroyed this house.");
        });

        threeLittlePigs.addInteraction(brick, window, () ->{
            house3Interior.setAccessible(true);
            threeLittlePigs.getCurrentRoom().removeItemFromRoom(window);
            threeLittlePigs.getCurrentRoom().removeItemFromRoom(brick);
            house3Interior.addItemToRoom(brick);
            System.out.println("You broke the window with the brick!");
        });


        threeLittlePigs.startGame();
        threeLittlePigs.runGame();

    }
}
