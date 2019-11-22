
import comp127graphics.CanvasWindow;
import comp127graphics.FontStyle;
import comp127graphics.GraphicsText;
import comp127graphics.ui.Button;
import comp127graphics.ui.TextField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextAdventure {
    private CanvasWindow canvas = new CanvasWindow("Text Adventure", 800, 800);
    private GraphicsText history;
    private TextField input;

    private List<String> commands;
    private String story;

    public TextAdventure (String story, List<String> commands){
        this.story = story;
        this.commands = commands;

        history = new GraphicsText();
        history.setText("");
        history.setFont(FontStyle.PLAIN, 15);

        input = new TextField();
        input.setCenter(400, 700);
        canvas.add(input);

        canvas.draw();
    }

    public static void main(String[] args) {
        TextAdventure adventure = new TextAdventure("Once upon a time", List.of("a", "b"));
        adventure.startGame();
    }

    public void startGame(){
        Scanner in = new Scanner(System.in);
        String result = in.nextLine().trim().toLowerCase();
        // Following code is a test
        if (commands.contains(result)){
            System.out.println("Valid command");
        }
        else
            System.out.println("Invalid command");
    }
}
