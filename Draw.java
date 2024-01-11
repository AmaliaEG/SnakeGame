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

public class Draw {
    private CustomizePage customizePage;
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

    public Draw(int gridXInput, int gridYInput) {
        this.gridXInput = gridXInput;
        this.gridYInput = gridYInput;
        calculateCanvasSize();
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

    public void drawSnake(Position snake, int lastDirection) {
        snakeRoot.getChildren().clear();
        ArrayList<ArrayList<Integer>> dataXY = snake.getPosition();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            Rectangle tail = new Rectangle(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize, tileSize,
                    tileSize);

            tail.setFill(new ImagePattern(imgSnakeBody));
            //tail.setFill(Color.FORESTGREEN);
            snakeRoot.getChildren().add(tail);
        }
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

    public void setSnakeSkin(Image snkH, Image snkB) {
        imgSnakeHead = snkH;
        imgSnakeBody = snkB;
    }

    public void setPointSkin(String imgFoodRed, String imgFoodYellow) {
        this.imgFoodRed = new Image(imgFoodRed);
        this.imgFoodYellow = new Image(imgFoodYellow);
    }

    public void setBackground(Image imgBackground) {
        this.imgBackground = imgBackground;
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
