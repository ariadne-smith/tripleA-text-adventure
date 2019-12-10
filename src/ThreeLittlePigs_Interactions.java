public class ThreeLittlePigs_Interactions {

    /*
    item interaction methods -- must be static
     */

    static void doNothing(){
        System.out.println("You can't do that.");
    }

    static void breakWindow(){

    }

    static void destroyHouse(Room nextRoom){
        nextRoom.setAccessible(true);
        //increase points;
        //pig set eatable;
        System.out.println("You have destroyed this house.");

    }

    //brick & window
    //leafblower & stick house
    //blow command
    //eat command
    //use _ with _

}
