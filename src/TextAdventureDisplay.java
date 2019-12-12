import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TextAdventureDisplay extends Application {

    public TextArea textDisplay;
    public TextField userInput, gameTitle;
    Button button;
    TextAdventure currentTA;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Triple-A Text Adventure");
        VBox organisation = new VBox();
        Scene scene = new Scene(organisation, 550,450);

        gameTitle = new TextField();
        gameTitle.setText("game name goes here");
        gameTitle.setEditable(false);
        gameTitle.setDisable(true);

        textDisplay = new TextArea();
        textDisplay.setPrefSize(300, 300);
        textDisplay.setPadding(new Insets(4));
        textDisplay.setEditable(false);
        textDisplay.setWrapText(true);


        //three little pigs start

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
                "brick houses. You sniff the air and have a good feeling about a house up ahead, which is made of bricks " +
                "and has a lovely skylight");
        house3.setDescription("Here is the site of the brick house. You can see a skylight.");


        Room house3Interior = new Room("Inside the brick house.");
        house3Interior.setStory("Aha! Here is the delicious pig! Your stomach is rumbling . . . ");

        Character user = new Character("You","A big bag wolf", List.of());
        user.setFirstDialogue("Why am I talking to myself?");
        user.setGeneralGreeting("Who better to keep me company than me...");

        Character owl = new Character("Owl", "A wise elderly owl who might have some helpful advice", null);
        owl.setFirstDialogue("I'm an owl, hoot hoot.");
        owl.setGeneralGreeting("Hello again. Hoot hoot!");
        owl.populateDialogueByTopics("advice", "That straw house over there looks pretty flimsy. Even a slight breeze could knock it down!" +
                " Hoot hoot!");
        owl.populateDialogueByTopics("owl things", "I eat mice. Hoot hoot!");
        owl.populateDialogueByTopics("the forest", "It sure is dark and scary in here. Hoot hoot!");

        Character pig1 = new Character("Billy", "A fat, delicious-looking pig", null);
        pig1.setFirstDialogue("Hi, I'm Billy, the pig! You're not a wolf, are you?");
        pig1.setGeneralGreeting("It's Billy the pig again!");
        pig1.populateDialogueByTopics("new house", "I've just built a new house out of straw! I think it looks great.");

        Character pig2 = new Character("Reginald", "A fat, delicious-looking pig", null);
        pig2.setFirstDialogue("Hi, I'm Reginald, the pig! Have you heard from my brothers?");
        pig2.setGeneralGreeting("It's Reginald again. I'm a little worried about my brothers.");
        pig2.populateDialogueByTopics("new house", "I just built a house out of sticks. I bet it can withstand anything!");

        Character pig3 = new Character("Spike", "A fat, delicious-looking pig", null);
        pig3.setFirstDialogue("What's up? I'm Spike, the pig! Have you seen a wolf around here? I think one is prowling around . . .");
        pig3.setGeneralGreeting("It's Reginald again. You should stay away, there's a wolf on the loose!");
        pig3.populateDialogueByTopics("the wolf", "I think my brothers were eaten by a wolf! Thankfully I " +
                "have my strong brick house to protect me.");

        Item leafBlower = new Item("leaf blower", "a gardening tool that generates a lot of wind", null);
        Item brick = new Item("brick", "looks like a leftover brick from building a house", null);

        woods.addCharacter(owl);
        woods.addCharacter(user);
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

        ArrayList<String> commands = new ArrayList<>(List.of("go", "show inventory", "pick up", "drop", "open", "eat", "talk"));
        ArrayList<Room> rooms = new ArrayList<>((List.of(woods, house1, house2, house3, house3Interior)));
        currentTA = new TextAdventure("Three Little Pigs", commands, rooms);
        currentTA.setStory("================================================== \n" +
                "This is a story about the big bad wolf.");
        //three little pigs end

        loadTextAdventure();

        userInput = new TextField();
        userInput.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                Node currentFocus = scene.getFocusOwner();
                if (currentFocus instanceof TextInputControl) {
                    System.out.println("textfieldinstance true");
                    retrieveCommand(((TextInputControl) currentFocus).getText());
                    //retrieveCommand(userInput.getText());
                    ((TextInputControl) currentFocus).setText("");
                }
                e.consume();
            }
            e.consume();
        });

        button = new Button();
        button.setText("test submit");
        button.setOnAction(e -> {});

        organisation.setPadding(new Insets(10));
        organisation.setSpacing(10.0);
        organisation.getChildren().addAll(gameTitle, textDisplay, userInput, button);

        stage.setScene(scene);
        stage.show();

    }

    public void printUserInputToDisplay(){
        if(textDisplay.getText().equals("")){
            textDisplay.setText(userInput.getText());
        } else {
            textDisplay.setText(textDisplay.getText() + "\n" + userInput.getText());
        }
        userInput.setText("");
    }

    public void loadTextAdventure() {
        gameTitle.setText(currentTA.getTitle());
        Room room = currentTA.getStartingRoom();
        printToDisplay("Available commands: " + currentTA.getCommandList());
        printLnToDisplay("Enter 'quit game' to quit.");
        printLnToDisplay(currentTA.getStory());
        printLnToDisplay(room.getStory());
        printLnToDisplay(room.getDescription());
        printLnToDisplay(room.getConnectionsDescription());
        if (room.getCharacterListDescription() != null) {
            printLnToDisplay(room.getCharacterListDescription());
        }
        if (room.getItemListDescription() != null) {
            printLnToDisplay(room.getItemListDescription());
        }
        currentTA.startGame();
    }

    public void retrieveCommand(String commandString){
        printLnToDisplay(currentTA.runGame(commandString));
    }

    public void printToDisplay(String text){
        textDisplay.setText(textDisplay.getText() + text);
    }

    public void printLnToDisplay(String text){
        textDisplay.setText(textDisplay.getText() + "\n" + text);
    }
}
