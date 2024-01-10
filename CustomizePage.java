import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.*;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomizePage extends Application{
    private Draw draw;
    public static void main(String[] args) {
        launch(args);
    }
    private Button skel_bg = new Button();
    private Button skel_p = new Button();
    private Button skel_s = new Button();

    private Button cot_bg = new Button();
    private Button cot_p = new Button();
    private Button cot_s = new Button();

    private Button game = new Button();

    @Override   
    public void start(Stage frontStage) {
        frontStage.setTitle("Customization");
        
        Label maps = new Label("Maps");
        Label points = new Label("Points");
        Label snake = new Label("Snakes");

        //Images
        Image skeleton_bg = new Image("Skins\\SkeletonSnakeBackground.png");
        Image skeleton_p = new Image("\\Skins\\FlowerPoint.png");
        Image skeleton_s = new Image("\\Skins\\SkeletonSnakeHead.png");
        ImageView skeleton_back = new ImageView(skeleton_bg);
        ImageView skeleton_point = new ImageView(skeleton_p);
        ImageView skeleton_snake = new ImageView(skeleton_s);

        Image cottage_bg = new Image("\\Skins\\cottageCoreFlower6.png");
        Image cottage_p = new Image("\\Skins\\cottageCoreFood.png");
        Image cottage_s = new Image("\\Skins\\cottageCoreHead.png"); 
        ImageView cottage_back = new ImageView(cottage_bg);
        ImageView cottage_point = new ImageView(cottage_p);
        ImageView cottage_snake = new ImageView(cottage_s);

        double width = 50;
        double height = 50;
        skeleton_back.setFitWidth(width);
        skeleton_back.setFitHeight(height);

        skeleton_point.setFitWidth(width);
        skeleton_point.setFitHeight(height);
        
        skeleton_snake.setFitWidth(width);
        skeleton_snake.setFitHeight(height);

        cottage_back.setFitWidth(width);
        cottage_back.setFitHeight(height);

        cottage_point.setFitWidth(width);
        cottage_point.setFitHeight(height);
        
        cottage_snake.setFitWidth(width);
        cottage_snake.setFitHeight(height);
        
        //Buttons
        skel_bg.setGraphic(skeleton_back);
        skel_p.setGraphic(skeleton_point);
        skel_s.setGraphic(skeleton_snake);

        cot_bg.setGraphic(cottage_back);
        cot_p.setGraphic(cottage_point);
        cot_s.setGraphic(cottage_snake);
        
        this.game.setText("Game");

        StackPane root = new StackPane();

        root.getChildren().addAll(maps, skel_bg, cot_bg, points, skel_p, cot_p, snake, skel_s, cot_s, game);
        root.setAlignment(Pos.CENTER);

        StackPane.setMargin(maps, new Insets(0, 0, 380, 0));
        StackPane.setMargin(skel_bg, new Insets(0, 0, 280, 80));
        StackPane.setMargin(cot_bg, new Insets(0, 80, 280, 0));

        StackPane.setMargin(points, new Insets(0, 0, 150, 0));
        StackPane.setMargin(skel_p, new Insets(0, 0, 50, 80));
        StackPane.setMargin(cot_p, new Insets(0, 80, 50, 0));

        StackPane.setMargin(snake, new Insets(80, 0, 0, 0));
        StackPane.setMargin(skel_s, new Insets(180, 0, 0, 80));
        StackPane.setMargin(cot_s, new Insets(180, 80, 0, 0));

        StackPane.setMargin(game, new Insets(330, 0, 0, 0));

        root.setPrefWidth(20);

        //Actions for buttons
        game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main main = new Main(frontStage);
            }
        });

        skel_bg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                draw.setSnakeSkin(skeleton_bg);
            }
        });

        skel_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                draw.setSnakeSkin(skeleton_p);
            }
        });

        skel_s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                draw.setSnakeSkin(skeleton_s);
            }
        });

        cot_bg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                draw.setSnakeSkin(cottage_bg);
            }
        });

        cot_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                draw.setSnakeSkin(cottage_p);
            }
        });

        cot_s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                draw.setSnakeSkin(cottage_s);
            }
        });

        Scene scene = new Scene(root, 500, 500);
        frontStage.setScene(scene);
        frontStage.show();
    }
}
