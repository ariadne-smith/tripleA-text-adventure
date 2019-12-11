public class ThreeLittlePigs_Interactions {

    /*
    item interaction methods -- must be static
     */

    static void doNothing(){
        System.out.println("You can't do that.");
    }

    static void destroyHouse(Room nextRoom, TextAdventure game, Character pig){
        nextRoom.setAccessible(true);
        game.addPoints(10);
        pig.setIsEatable(true);
        System.out.println("You have destroyed this house.");
    }

    //brick & window
    //leafblower & stick house
    //blow command
    //eat command
    //use _ with _

}
