import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomizePage extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private Button game = new Button();

    @Override   
    public void start(Stage frontStage) {
        frontStage.setTitle("Customization");
        
        Label maps = new Label("Maps");
        Label points = new Label("Points");
        Label snake = new Label("Snakes");

        this.game.setText("Game");

        StackPane root = new StackPane();

        root.getChildren().addAll(maps, points, snake, game);
        root.setAlignment(Pos.CENTER);

        StackPane.setMargin(game, new Insets(230, 0, 30, 0));
        StackPane.setMargin(costumize, new Insets(110, 0, 30, 0));
        StackPane.setMargin(quit, new Insets(0, 0, 150, 0));

        //Actions for buttons
        game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GamePage.show(frontStage);
            }
        });

        costumize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //CustomizePage.show(frontStage);
                System.out.println("costumize");
            }
        });

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("quit");
                frontStage.close();
            }
        });

        
        
        Scene scene = new Scene(root, 500, 500);
        frontStage.setScene(scene);
        frontStage.show();
    }
}
