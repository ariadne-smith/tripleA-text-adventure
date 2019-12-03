public class ThreeLittlePigs {
    public static void main(String[] args) {
        Room woods = new Room("Woods");
        woods.setStory("You are a ferocious wolf, hungry, and tired after a long excursion\n " +
                "out in the deep dark woods. You have just heard tell that there are three little\n " +
                "pigs who have recently done some construction in the neighborhood…\n");
        woods.setDescription("These woods are dark and scary.");
        Room house1 = new Room ("The Straw House");
        house1.setStory("You’ve trekked out of the woods and followed the rocky dirt road until you\n" +
                " find a neighborhood full of questionably constructed straw houses. Your nose twitches\n" +
                "and you realize the one right in front of you quite possibly contains bacon…\n");
        house1.setDescription("In front of you is a shoddily built straw house.");
        Room house2 = new Room("The Stick House");
        house2.setStory("You decide to look for some more food and continue on to the next neighborhood,\n" +
                " filled with stick houses. You sniff the air and have a good feeling about a house to your left…\n");
        house2.setDescription("Here is the site of another flimsy house.");
        Room house3 = new Room("The Brick House");
        house3.setStory("You decide to look for some more food and continue on to the next neighborhood, filled with " +
                "brick houses. You sniff the air and have a good feeling about a house up ahead...\n");
        house3.setDescription("Here is the site of the brick house. You can see a chimney. ");
        Room house3Inside = new Room("Inside the brick house.");


    }
}
