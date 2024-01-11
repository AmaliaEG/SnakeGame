import java.util.ArrayList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.geometry.*;
import javafx.event.EventHandler;

public class Draw {
    private Position position;

    public static final int MAX_CANVAS = 600;

    // User defined visual properties for the game
    int backroundColor = 0;
    int snakeColor = 0;

    public Image imgSnakeHead = new Image("\\Skins\\SkeletonSnakeHead.png");
    public Image imgSnakeBody = new Image("\\Skins\\SkeletonSnakeBody.png");
    public Image imgFoodRed = new Image("\\Skins\\cottageCoreFood.png"); 
    public Image imgFoodYellow = new Image("\\Skins\\cottageCoreGold.png");
    public Image imgBackground = new Image("\\Skins\\SkeletonSnakeBackground.png");

    private int HEIGHT_CANVAS;
    private int WIDTH_CANVAS;

    public int gridXInput;
    public int gridYInput;
    private int tileSize;

    private Group gridRoot = new Group();
    private Group snakeRoot = new Group();
    private Group pointRoot = new Group();
    private Group scoreRoot = new Group();
    private Group gamePause = new Group();

    //Costumize Page
    private Button skel_bg = new Button();
    private Button skel_p = new Button();
    private Button skel_s = new Button();

    private Button cot_bg = new Button();
    private Button cot_p = new Button();
    private Button cot_s = new Button();

    private Button game = new Button();

    private Button front = new Button();

    public void setSnakeSkin(String snkH, String snkB) {
        Image body = new Image("\\Skins\\SkeletonSnakeHead.png");
        Image head = new Image("\\Skins\\SkeletonSnakeBody.png");
        drawSnake(position, 4, head, body);
    }

    public Draw(Stage frontStage, int gridXInput, int gridYInput) {
        this.position = new Position(gridXInput, gridYInput);
        this.gridXInput = gridXInput;
        this.gridYInput = gridYInput;
        calculateCanvasSize();

        frontStage.setTitle("Customization");
        
        Label maps = new Label("Maps");
        Label points = new Label("Points");
        Label snake = new Label("Snakes");

        //Images
        Image skeleton_bg = new Image("\\Skins\\SkeletonSnakeBackground.png");
        Image skeleton_p = new Image("\\Skins\\FlowerPoint.png");
        Image skeleton_s = new Image("\\Skins\\SkeletonSnakeHead.png");
        Image skeleton_body = new Image("\\Skins\\SkeletonSnakeBody.png");
        Image skeleton_tail = new Image("\\Skins\\SkeletonSnakeTail.png");

        ImageView skeleton_back = new ImageView(skeleton_bg);
        ImageView skeleton_point = new ImageView(skeleton_p);
        ImageView skeleton_snake = new ImageView(skeleton_s);

        Image cottage_bg = new Image("\\Skins\\cottageCoreFlower6.png");
        Image cottage_p = new Image("\\Skins\\cottageCoreFood.png");
        Image cottage_p_gold = new Image("\\Skins\\cottageCoreGold.png");
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
        this.front.setText("Back");

        StackPane root = new StackPane();

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

        //Actions for buttons
        game.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main main = new Main(frontStage);
            }
        });

        front.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FrontPage front = new FrontPage();
                front.start(frontStage);
            }
        });

        skel_bg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setBackground(new Image("\\Skins\\SkeletonSnakeBackground.png"));
            }
        });

        skel_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setPointSkin("\\Skins\\FlowerPoint.png", "\\Skins\\SkeletonSnakeTail.png");
                System.out.println("Flower point ooon");
            }
        });

        skel_s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSnakeSkin("\\Skins\\SkeletonSnakeHead.png", "\\Skins\\SkeletonSnakeBody.png");
                System.out.println("Skeleton skin ooon");
                
            }
        });

        cot_bg.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setBackground(cottage_s);
            }
        });

        cot_p.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //setPointSkin("\\Skins\\cottageCoreGold.png", "\\Skins\\cottageCoreGold.png");
                System.out.println("Cottage point ooon");
            }
        });

        cot_s.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setSnakeSkin("\\Skins\\cottageCoreHead.png", "\\Skins\\cottageCoreFlower6.png");
                //Image body = new Image("\\Skins\\cottageCoreFlower6.png");
                //Image head = new Image("\\Skins\\cottageCoreHead.png");
                //drawSnake(position, 4);
                System.out.println("Cottage skin ooon");
            }
        });

        Scene scene = new Scene(root, 500, 500);
        frontStage.setScene(scene);
        frontStage.show();
    }

    public void calculateCanvasSize() {
        int maxGridSize = Math.max(gridXInput, gridYInput);
        this.tileSize = MAX_CANVAS / maxGridSize;
        this.WIDTH_CANVAS = tileSize * gridXInput;
        this.HEIGHT_CANVAS = tileSize * gridYInput;
    }

    public void initializeScene(Stage primaryStage) {
        Scene scene = new Scene(new Group(gridRoot, pointRoot, snakeRoot, scoreRoot, gamePause), WIDTH_CANVAS,
            HEIGHT_CANVAS, Color.GREEN);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    

    /*public void setPointSkin(String imgFoodRed, String imgFoodYellow) {
        this.imgFoodRed = new Image(imgFoodRed);
        this.imgFoodYellow = new Image(imgFoodYellow);
    }

    public void setBackground(Image imgBackground) {
        this.imgBackground = imgBackground;
    }*/

    public void drawGrid() {
        for (int i = 0; i <= gridXInput; i++) {
            Line lineVertical = new Line(i * tileSize, 0, i * tileSize, HEIGHT_CANVAS);
            lineVertical.setStroke(Color.DARKGREEN);
            gridRoot.getChildren().add(lineVertical);
        }

        for (int i = 0; i <= gridYInput; i++) {
            Line lineHorizontal = new Line(0, i * tileSize, WIDTH_CANVAS, i * tileSize);
            lineHorizontal.setStroke(Color.DARKGREEN);
            gridRoot.getChildren().add(lineHorizontal);
        }
    }

    public void drawSnake(Position snake, int lastDirection, Image imgSnakeHead, Image imgSnakeBody) {
        snakeRoot.getChildren().clear();
        ArrayList<ArrayList<Integer>> dataXY = snake.getPosition();
        int size = dataXY.size();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            Rectangle tail = new Rectangle(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize, tileSize,
                    tileSize);

            tail.setFill(new ImagePattern(imgSnakeBody));
            //tail.setFill(Color.FORESTGREEN);
            snakeRoot.getChildren().add(tail);
        }

        if(!dataXY.isEmpty()){
            Rectangle head = new Rectangle(snake.getX() * tileSize, snake.getY() * tileSize, tileSize, tileSize);

            switch (lastDirection) {
                case 1: // UP
                    head.setRotate(0);
                    break;
                case 2: // DOWN
                    head.setRotate(180);
                    break;
                case 3: // RIGHT
                    head.setRotate(90);
                    break;
                case 4: // LEFT
                    head.setRotate(270);
                    break;
                default:
                    head.setRotate(270);
                    break;
            }
            
            head.setFill(new ImagePattern(imgSnakeHead));

            snakeRoot.getChildren().add(head);
        }
    }

    public void drawPoint(ArrayList<ArrayList<Integer>> pointList) {
        pointRoot.getChildren().clear();
        for (ArrayList<Integer> point : pointList) {
            int pointX = point.get(0);
            int pointY = point.get(1);
            int pointType = point.get(2);
            Rectangle pointSpawn = new Rectangle(pointX * tileSize, pointY * tileSize, tileSize, tileSize);
            if (pointType == 0) {
                pointSpawn.setFill(new ImagePattern(imgFoodYellow));
                //pointSpawn.setFill(Color.YELLOW);
            } else {
                pointSpawn.setFill(new ImagePattern(imgFoodRed));
                //pointSpawn.setFill(Color.RED);
            }
            pointRoot.getChildren().add(pointSpawn);
        }
    }

    public void drawScore(int score) {
        scoreRoot.getChildren().clear();
        Text scoreText = new Text("Score: " + score);
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        scoreText.setFill(Color.FORESTGREEN);
        double textX = WIDTH_CANVAS / 2 - scoreText.getLayoutBounds().getWidth() / 2;
        ;
        double textY = HEIGHT_CANVAS / 7;
        scoreText.setX(textX);
        scoreText.setY(textY);
        scoreRoot.getChildren().add(scoreText);
    }

    public void drawGameOver(Stage primaryStage) {
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        gameOverText.setFill(Color.WHITE);
        double textX = WIDTH_CANVAS / 2 - gameOverText.getLayoutBounds().getWidth() / 2;
        double textY = HEIGHT_CANVAS / 2;
        gameOverText.setX(textX);
        gameOverText.setY(textY);
        ((Group) primaryStage.getScene().getRoot()).getChildren().add(gameOverText);
    }

    public void drawGameIsPaused(Stage primaryStage) {
        Text gamePauseText = new Text("Pause");
        gamePauseText.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        gamePauseText.setFill(Color.WHITE);
        double textX = WIDTH_CANVAS / 2 - gamePauseText.getLayoutBounds().getWidth() / 2;
        double textY = HEIGHT_CANVAS / 2;
        gamePauseText.setX(textX);
        gamePauseText.setY(textY);
        gamePause.getChildren().add(gamePauseText);
    }

    public void drawGameNotPaused(Stage primaryStage) {
        gamePause.getChildren().clear();
    }
}
