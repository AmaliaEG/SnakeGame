import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.input.*;
import javafx.geometry.*;

public class FrontPage extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private Button game = new Button();
    private Button costumize = new Button();
    private Button quit = new Button();
    private Text title = new Text("Snake!");

    @Override   
    public void start(Stage frontStage) {
        frontStage.setTitle("Snake Game");
        
        //Head Title
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.PINK);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(130, 0, 0, 0));

        this.game.setText("Game");
        this.costumize.setText("Costumize");
        this.quit.setText("Quit");

        StackPane root = new StackPane();

        root.getChildren().addAll(title, game, costumize, quit);

        //Aligns the buttons
        StackPane.setAlignment(game, Pos.TOP_CENTER);
        StackPane.setAlignment(costumize, Pos.CENTER);
        StackPane.setAlignment(quit, Pos.BOTTOM_CENTER);

        StackPane.setMargin(game, new Insets(230, 0, 30, 0));
        StackPane.setMargin(costumize, new Insets(110, 0, 30, 0));
        StackPane.setMargin(quit, new Insets(0, 0, 150, 0));

       
        
        Scene scene = new Scene(root, 500, 500);
        frontStage.setScene(scene);
        frontStage.show();
    }
}
