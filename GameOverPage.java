import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.EventHandler;

public class GameOverPage {
    private Button restart = new Button("Restart Game");
    private Button quit = new Button("Quit Game");
    private Text title = new Text("GAME OVER!");
    private Stage primaryStage;

    public GameOverPage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Game Over");

        // Head Title
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.PINK);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(130, 0, 0, 0));

        this.restart.setText("Restart Game");
        this.quit.setText("Quit Game");

        StackPane root = new StackPane();

        root.getChildren().addAll(restart, quit, title);

        // Aligns the buttons
        StackPane.setAlignment(restart, Pos.TOP_CENTER);
        StackPane.setAlignment(quit, Pos.CENTER);

        StackPane.setMargin(restart, new Insets(230, 0, 30, 0));
        StackPane.setMargin(quit, new Insets(110, 0, 30, 0));

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.show();
            }
        });

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("quit");
                primaryStage.close();
            }
        });
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
