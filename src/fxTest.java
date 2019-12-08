import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class fxTest extends Application {

    // launch the application 
    public void start(Stage s)
    {
        // set title for the stage 
        s.setTitle("creating TextField");

        // create a textfield 
        TextField b = new TextField();

        // create a stack pane 
        StackPane r = new StackPane();

        // add textfield 
        r.getChildren().add(b);

        // create a scene 
        Scene sc = new Scene(r, 200, 200);

        // set the scene 
        s.setScene(sc);

        s.show();
    }

    public static void main(String args[])
    {
        // launch the application 
        launch(args);
    }
} 