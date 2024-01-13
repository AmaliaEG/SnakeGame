import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

        Image backgroundImage = new Image("\\pages\\Background.png");
        ImageView background = new ImageView(backgroundImage);

        Font.loadFont(getClass().getResourceAsStream("\\pages\\Pixeboy-z8XGD.ttf"), 12);
        Font buttonFont = Font.font("Pixeboy", FontWeight.BOLD, 25);
                
        //Head Title
        title.setFont(Font.font("Pixeboy", FontWeight.BOLD, 60));
        title.setFill(Color.PINK);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(130, 0, 0, 0));

        this.game.setText("Start game");
        this.costumize.setText("Costumize");
        this.quit.setText("Quit");

        game.setFont(buttonFont);
        costumize.setFont(buttonFont);
        quit.setFont(buttonFont);

        game.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
        costumize.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
        quit.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;");
        
        String hover = "-fx-text-fill: white; -fx-background-color: #4CAF50; -FX-BORDER-COLOR: #4CAF50;";

        game.setOnMouseEntered(e -> game.setStyle(hover));
        game.setOnMouseExited(e -> game.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;"));

        costumize.setOnMouseEntered(e -> costumize.setStyle(hover));
        costumize.setOnMouseExited(e -> costumize.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;"));

        quit.setOnMouseEntered(e -> quit.setStyle(hover));
        quit.setOnMouseExited(e -> quit.setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;"));

        StackPane root = new StackPane();

        root.getChildren().add(background);

        root.getChildren().addAll(title, game, costumize, quit);

        //Aligns the buttons
        StackPane.setAlignment(game, Pos.TOP_CENTER);
        StackPane.setAlignment(costumize, Pos.CENTER);
        StackPane.setAlignment(quit, Pos.BOTTOM_CENTER);

        StackPane.setMargin(game, new Insets(230, 0, 30, 0));
        StackPane.setMargin(costumize, new Insets(110, 0, 30, 0));
        StackPane.setMargin(quit, new Insets(0, 0, 150, 0));

        //Actions for buttons
        game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main main = new Main(frontStage);
                // Ved ikke hvorfor den lyser gul under, it legit creates an instance and das it.
                // Ari - den lyser gul fordi den sigr at den ikke bliver brugt. 
            }
        });

        costumize.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CustomizePage customizePage = new CustomizePage(frontStage);
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
