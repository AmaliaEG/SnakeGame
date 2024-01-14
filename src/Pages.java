import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Pages {
    private static final String DEFAULT = "-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;";
    private static final String HOVER = "-fx-text-fill: white; -fx-background-color: #4CAF50; -FX-BORDER-COLOR: #4CAF50;";
    private static final Font BUTTON_FONT = Font.loadFont(Pages.class.getResourceAsStream("pages/Pixeboy-z8XGD.ttf"),
            25);
    private static final Font LABEL_FONT = Font.loadFont(Pages.class.getResourceAsStream("pages/Pixeboy-z8XGD.ttf"),
            22);
    private static final double buttonImageSize = 50;

    public static void frontPage(Stage primaryStage, Main main, Draw gameBoard) {
        Text title = new Text("Snake!");
        title.setFont(Font.font("Pixeboy", FontWeight.BOLD, 60));
        title.setFill(Color.PINK);

        Button startGameButton = styleButton("Start game", "-fx-text-fill: white;",
                e -> gameBoardInitialization(primaryStage, main));
        Button costumizeButton = styleButton("Costumize", "-fx-text-fill: white;",
                e -> gameCustomize(primaryStage, main, gameBoard));
        Button quitButton = styleButton("Quit", "-fx-text-fill: white;", e -> primaryStage.close());

        Image backgroundImage = new Image(ClassLoader.getSystemResource("pages/Background.png").toString());
        ImageView background = new ImageView(backgroundImage);

        // Head Title
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(130, 0, 0, 0));

        StackPane root = new StackPane();

        root.getChildren().add(background);
        root.getChildren().addAll(title, startGameButton, costumizeButton, quitButton);

        // Aligns the buttons
        StackPane.setAlignment(startGameButton, Pos.TOP_CENTER);
        StackPane.setAlignment(costumizeButton, Pos.CENTER);
        StackPane.setAlignment(quitButton, Pos.BOTTOM_CENTER);

        StackPane.setMargin(startGameButton, new Insets(230, 0, 30, 0));
        StackPane.setMargin(costumizeButton, new Insets(110, 0, 30, 0));
        StackPane.setMargin(quitButton, new Insets(0, 0, 150, 0));

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void gameBoardInitialization(Stage primaryStage, Main main) {
        VBox the_root = new VBox();
        Label labelX = new Label("Input a grid width between 5-100 (inclusive) ");
        Label labelY = new Label("Input a grid height between 5-100 (inclusive) ");
        TextField textFieldX = new TextField();
        TextField textFieldY = new TextField();

        Button createButton = styleButton("Create", "-fx-text-fill: white;", e -> {
            try {
                int gridX = Integer.parseInt(textFieldX.getText());
                int gridY = Integer.parseInt(textFieldY.getText());

                if (gridX >= 5 && gridX <= 100 && gridY >= 5 && gridY <= 100) {
                    main.startGame(primaryStage, gridX, gridY);
                } else {
                    System.out.println("Invalid size.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input.");
            }
        });

        labelX.setFont(LABEL_FONT);
        labelY.setFont(LABEL_FONT);

        createButton.setOnMouseEntered(e -> createButton.setStyle(HOVER));
        createButton.setOnMouseExited(e -> createButton.setStyle(DEFAULT));

        the_root.setStyle("-fx-background-color: pink;");
        the_root.getChildren().addAll(labelX, textFieldX, labelY, textFieldY, createButton);

        the_root.setAlignment(Pos.CENTER);
        VBox.setMargin(labelX, new Insets(0, 0, 3, 0));
        VBox.setMargin(labelY, new Insets(15, 0, 3, 0));
        VBox.setMargin(textFieldX, new Insets(2, 0, 0, 0));
        VBox.setMargin(textFieldY, new Insets(2, 0, 0, 0));
        VBox.setMargin(createButton, new Insets(25, 0, 0, 0));

        textFieldX.setMaxWidth(100);
        textFieldY.setMaxWidth(100);

        the_root.setPrefWidth(200);

        Scene inputScene = new Scene(the_root, 500, 500);
        primaryStage.setScene(inputScene);
        primaryStage.show();
    }

    public static Button styleButton(String text, String textColor, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setFont(BUTTON_FONT);
        button.setStyle("-fx-background-color: transparent; -fx-border-width: 0; " + textColor);
        button.setOnAction(handler);
        button.setOnMouseEntered(e -> button.setStyle(HOVER));
        button.setOnMouseExited(e -> button.setStyle(DEFAULT));
        return button;
    }

    public static Button styleCustomButton(Image cutomPicture, EventHandler<ActionEvent> handler) {
        Button button = new Button();
        ImageView imageView = new ImageView(cutomPicture);
        imageView.setFitHeight(buttonImageSize);
        imageView.setFitWidth(buttonImageSize);
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent; -fx-border-width: 0; ");
        button.setOnAction(handler);
        button.setOnMouseEntered(e -> button.setStyle(HOVER));
        button.setOnMouseExited(e -> button.setStyle(DEFAULT));
        return button;
    }

    private static void gameCustomize(Stage primaryStage, Main main, Draw gameBoard) {
        Stage customStage = new Stage();
        customStage.setTitle("Customization");

        // Images
        Image skeleton_bg = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeBackground.png").toString());
        Image skeleton_p = new Image(ClassLoader.getSystemResource("Skins/FlowerPoint.png").toString());
        Image skeleton_s = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeHead.png").toString());
        Image skeleton_body = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeBody.png").toString());
        Image skeleton_tail = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeTail.png").toString());

        Image cottage_bg = new Image(ClassLoader.getSystemResource("Skins/cottageCoreFlower6.png").toString());
        Image cottage_p = new Image(ClassLoader.getSystemResource("Skins/cottageCoreFood.png").toString());
        Image cottage_p_gold = new Image(ClassLoader.getSystemResource("Skins/cottageCoreGold.png").toString());
        Image cottage_s = new Image(ClassLoader.getSystemResource("Skins/cottageCoreHead.png").toString());

        // Buttons
        Button skel_bg = styleCustomButton(skeleton_bg, e -> {
            gameBoard.setBackground(skeleton_bg);

        });

        Button skel_p = styleCustomButton(skeleton_p, e -> {
            gameBoard.setPointSkin(skeleton_p, cottage_p_gold);

        });

        Button skel_s = styleCustomButton(skeleton_s, e -> {
            gameBoard.setSnakeSkin(skeleton_s, skeleton_body);
        });

        Button cot_bg = styleCustomButton(cottage_bg, e -> {
            gameBoard.setBackground(skeleton_bg);
        });
        Button cot_p = styleCustomButton(cottage_p, e -> {
            gameBoard.setPointSkin(cottage_p, cottage_p_gold);
        });

        Button cot_s = styleCustomButton(cottage_s, e -> {
            gameBoard.setSnakeSkin(cottage_s, cottage_bg);
        });

        Button gameButton = styleButton("Start Game", "", e -> {
            gameBoardInitialization(primaryStage, main);
            customStage.close();
        });

        Button frontPageButton = styleButton("Back", "", e -> {
            frontPage(primaryStage, main, gameBoard);
            customStage.close();
        });

        Label maps = new Label("Maps");
        Label points = new Label("Points");
        Label snake = new Label("Snakes");

        maps.setFont(LABEL_FONT);
        points.setFont(LABEL_FONT);
        snake.setFont(LABEL_FONT);

        StackPane root = new StackPane();

        root.setStyle("-fx-background-color: pink;");

        root.getChildren().addAll(frontPageButton, maps, skel_bg, cot_bg, points, skel_p, cot_p, snake, skel_s, cot_s,
                gameButton);
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

        StackPane.setMargin(gameButton, new Insets(330, 0, 0, 0));

        StackPane.setMargin(frontPageButton, new Insets(0, 370, 420, 0));

        root.setPrefWidth(20);

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void gameOverPage(Stage primaryStage, Draw gameBoard) {
        StackPane root = new StackPane();

        root.setStyle("-fx-background-color: purple;");

        Stage gameOverStage = new Stage();
        gameOverStage.setTitle("Game Over");

        Button restartButton = styleButton("Restart Game", "-fx-text-fill: white;", e -> {
            Main main = new Main();
            main.start(primaryStage);
            gameOverStage.close();
        });

        Button quitButton = styleButton("Quit Game", "-fx-text-fill: white;", e -> primaryStage.close());

        Text gameOvertitle = new Text("GAME OVER!");

        gameOvertitle.setFont(Font.font("Pixeboy", FontWeight.BOLD, 60));
        gameOvertitle.setFill(Color.PINK);

        StackPane.setAlignment(gameOvertitle, Pos.TOP_CENTER);
        StackPane.setMargin(gameOvertitle, new Insets(130, 0, 0, 0));

        root.getChildren().addAll(restartButton, quitButton, gameOvertitle);

        StackPane.setAlignment(restartButton, Pos.TOP_CENTER);
        StackPane.setAlignment(quitButton, Pos.CENTER);

        StackPane.setMargin(restartButton , new Insets(230, 0, 30, 0));
        StackPane.setMargin(quitButton, new Insets(110, 0, 30, 0));
        
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
}
