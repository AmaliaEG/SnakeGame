import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.application.Application;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import javax.swing.DefaultRowSorter;

public class Pages {
    private static final String DEFAULT = "-fx-background-color: transparent; -fx-border-width: 0;";
    private static final String HOVER = "-fx-text-fill: white; -fx-background-color: #4CAF50; -FX-BORDER-COLOR: #4CAF50;";
    private static final Font LABEL_FONT = Font.font("Pixeboy", FontWeight.BOLD, 20);

    public static void frontPage(Stage primaryStage, Main main) {
        Font.loadFont(Pages.class.getResourceAsStream("pages/Pixeboy-z8XGD.ttf"), 12);

        Text title = new Text("Snake!");
        title.setFont(Font.font("Pixeboy", FontWeight.BOLD, 60));
        title.setFill(Color.PINK);

        Button startGameButton = styleButton("Start game", "-fx-text-fill: white;", e -> gameBoardInitialization(primaryStage, main));
        Button costumizeButton = styleButton("Costumize", "-fx-text-fill: white;", e -> gameCustomize(primaryStage, main));
        Button quitButton = styleButton("Quit", "-fx-text-fill: white;", e -> primaryStage.close());

        Image backgroundImage = new Image(ClassLoader.getSystemResource("pages/Background.png").toString());
        ImageView background = new ImageView(backgroundImage);

        Font.loadFont(Pages.class.getResourceAsStream("/pages/Pixeboy-z8XGD.ttf"), 12);

        // Head Title
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(130, 0, 0, 0));

        // String hover = "-fx-text-fill: white; -fx-background-color: #4CAF50;
        // -FX-BORDER-COLOR: #4CAF50;";

        /*
         * startGameButton.setOnMouseEntered(e -> startGameButton.setStyle(hover));
         * startGameButton.setOnMouseExited(e -> startGameButton.
         * setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;"
         * ));
         * 
         * costumizeButton.setOnMouseEntered(e -> costumizeButton.setStyle(hover));
         * costumizeButton.setOnMouseExited(e -> costumizeButton.
         * setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;"
         * ));
         * 
         * quitButton.setOnMouseEntered(e -> quitButton.setStyle(hover));
         * quitButton.setOnMouseExited(e -> quitButton.
         * setStyle("-fx-background-color: transparent; -fx-border-width: 0; -fx-text-fill: white;"
         * ));
         */

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
        button.setFont(Font.font("Pixeboy", FontWeight.BOLD, 25));
        button.setStyle("-fx-background-color: transparent; -fx-border-width: 0; " + textColor);
        button.setOnAction(handler);
        button.setOnMouseEntered(e -> button.setStyle(HOVER));
        button.setOnMouseExited(e -> button.setStyle(DEFAULT));
        return button;
    }
    public static Button styleCustomButton(Image cutomPicture, double imageSize, EventHandler<ActionEvent> handler) {
        Button button = new Button();
        ImageView imageView = new ImageView(cutomPicture);
        imageView.setFitHeight(imageSize);
        imageView.setFitWidth(imageSize);
        button.setGraphic(imageView);     
        button.setStyle("-fx-background-color: transparent; -fx-border-width: 0; ");
        button.setOnAction(handler);
        button.setOnMouseEntered(e -> button.setStyle(HOVER));
        button.setOnMouseExited(e -> button.setStyle(DEFAULT));
        return button;
    } 

    private static void gameCustomize(Stage primaryStage, Main main) {
        double imageSize = 50;
        
        Button skel_bg = new Button();
        Button skel_p = new Button();
        Button skel_s = new Button();

        Button cot_bg = new Button();
        Button cot_p = new Button();
        Button cot_s = new Button();

        Button game = new Button();
        Button front = new Button();

        Stage customStage = new Stage();

        customStage.setTitle("Customization");

        Label maps = new Label("Maps");
        Label points = new Label("Points");
        Label snake = new Label("Snakes");
        Font labelFont = Font.font("Pixeboy", FontWeight.BOLD, 30);
        Font buttonFont = Font.font("Pixeboy", FontWeight.BOLD, 25);
        maps.setFont(labelFont);
        points.setFont(labelFont);
        snake.setFont(labelFont);

        // Images
        Image skeleton_bg = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeBackground.png").toString());
        Image skeleton_p = new Image(ClassLoader.getSystemResource("Skins/FlowerPoint.png").toString());
        Image skeleton_s = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeHead.png").toString());
        Image skeleton_body = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeBody.png").toString());
        Image skeleton_tail = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeTail.png").toString());

        ImageView skeleton_back = new ImageView(skeleton_bg);
        ImageView skeleton_point = new ImageView(skeleton_p);
        ImageView skeleton_snake = new ImageView(skeleton_s);

        Image cottage_bg = new Image(ClassLoader.getSystemResource("Skins/cottageCoreFlower6.png").toString());
        Image cottage_p = new Image(ClassLoader.getSystemResource("Skins/cottageCoreFood.png").toString());
        Image cottage_p_gold = new Image(ClassLoader.getSystemResource("Skins/cottageCoreGold.png").toString());
        Image cottage_s = new Image(ClassLoader.getSystemResource("Skins/cottageCoreHead.png").toString());
        

        ImageView cottage_back = new ImageView(cottage_bg);
        ImageView cottage_point = new ImageView(cottage_p);
        ImageView cottage_snake = new ImageView(cottage_s);



        skeleton_back.setFitWidth(imageSize);
        skeleton_back.setFitHeight(imageSize);

        skeleton_point.setFitWidth(imageSize);
        skeleton_point.setFitHeight(imageSize);

        skeleton_snake.setFitWidth(imageSize);
        skeleton_snake.setFitHeight(imageSize);

        cottage_back.setFitWidth(imageSize);
        cottage_back.setFitHeight(imageSize);

        cottage_point.setFitWidth(imageSize);
        cottage_point.setFitHeight(imageSize);

        cottage_snake.setFitWidth(imageSize);
        cottage_snake.setFitHeight(imageSize);

        // Buttons
        skel_bg.setGraphic(skeleton_back);
        skel_p.setGraphic(skeleton_point);
        skel_s.setGraphic(skeleton_snake);

        cot_bg.setGraphic(cottage_back);
        cot_p.setGraphic(cottage_point);
        cot_s.setGraphic(cottage_snake);

        game.setText("Start game");
        front.setText("Back");

        game.setFont(buttonFont);
        front.setFont(buttonFont);

        game.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
        front.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");

        skel_bg.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
        skel_p.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
        skel_s.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
        cot_bg.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
        cot_p.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
        cot_s.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");

        String hoverText = "-fx-text-fill: white; -fx-background-color: #4CAF50; -FX-BORDER-COLOR: #4CAF50;";
        String hoverButton = "-fx-background-color: transparent; -FX-BORDER-COLOR: #4CAF50;";

        game.setOnMouseEntered(e -> game.setStyle(hoverText));
        game.setOnMouseExited(e -> game.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        front.setOnMouseEntered(e -> front.setStyle(hoverText));
        front.setOnMouseExited(e -> front.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        skel_bg.setOnMouseEntered(e -> skel_bg.setStyle(hoverButton));
        skel_bg.setOnMouseExited(e -> skel_bg.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        skel_p.setOnMouseEntered(e -> skel_p.setStyle(hoverButton));
        skel_p.setOnMouseExited(e -> skel_p.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        skel_s.setOnMouseEntered(e -> skel_s.setStyle(hoverButton));
        skel_s.setOnMouseExited(e -> skel_s.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        cot_bg.setOnMouseEntered(e -> cot_bg.setStyle(hoverButton));
        cot_bg.setOnMouseExited(e -> cot_bg.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        cot_p.setOnMouseEntered(e -> cot_p.setStyle(hoverButton));
        cot_p.setOnMouseExited(e -> cot_p.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        cot_s.setOnMouseEntered(e -> cot_s.setStyle(hoverButton));
        cot_s.setOnMouseExited(e -> cot_s.setStyle("-fx-background-color: transparent; -fx-border-width: 0;"));

        StackPane root = new StackPane();

        root.setStyle("-fx-background-color: pink;");

        root.getChildren().addAll(front, maps, skel_bg, cot_bg, points, skel_p, cot_p, snake, skel_s, cot_s, game);
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

        StackPane.setMargin(front, new Insets(0, 370, 420, 0));

        root.setPrefWidth(20);
        game.setOnAction(e -> { 
            main.startGame(primaryStage, 20, 20);
        });

        front.setOnAction(e -> { 
            frontPage(primaryStage, main);
            customStage.close();
        });

        skel_bg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // draw.setBackground(skeleton_bg);
                System.out.println("You have pressed the skeleton background button");
            }
        });

        skel_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // draw.setPointSkin("\\Skins\\FlowerPoint.png",
                // "\\Skins\\SkeletonSnakeTail.png");
                System.out.println("You have pressed the skeleton point button");
            }
        });

        skel_s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // draw.setSnakeSkin(skeleton_s);
                System.out.println("You have pressed the skeleton skin button");
            }
        });

        cot_bg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // draw.setBackground(cottage_s);
                System.out.println("You have pressed the cottage-core background button");
            }
        });

        cot_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // draw.setPointSkin("\\Skins\\cottageCoreGold.png",
                // "\\Skins\\cottageCoreGold.png");
                System.out.println("You have pressed the cottage-core point button");
            }
        });

        cot_s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // draw.setSnakeSkin(cottage_s);
                System.out.println("You have pressed the cottage-core skin button");
            }
        });

        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
