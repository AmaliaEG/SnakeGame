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
import javafx.scene.layout.StackPane;

public class Draw {

    public static final int MAX_CANVAS = 575;
    private static final int SCENE_HEIGHT = 625;

    // User defined visual properties for the game
    int backroundColor = 0;
    int snakeColor = 0;

    public Image imgSnakeHead = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeHead.png").toString());
    public Image imgSnakeBody = new Image(ClassLoader.getSystemResource("Skins/SkeletonSnakeBody.png").toString());
    public Image imgFoodRed = new Image(ClassLoader.getSystemResource("Skins/cottageCoreFood.png").toString());
    public Image imgFoodYellow = new Image(ClassLoader.getSystemResource("Skins/cottageCoreGold.png").toString());
    public Image imgBackground = new Image(
            ClassLoader.getSystemResource("Skins/SkeletonSnakeBackground.png").toString());

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
    private Group powerUp = new Group();


    public Draw() {

    }

    public void calculateCanvasSize(int gridXInput, int gridYInput) {
        this.gridXInput = gridXInput;
        this.gridYInput = gridYInput;
        int maxGridSize = Math.max(gridXInput, gridYInput);
        this.tileSize = MAX_CANVAS / maxGridSize;
        this.WIDTH_CANVAS = tileSize * gridXInput;
        this.HEIGHT_CANVAS = tileSize * gridYInput;
    }

    public void initializeScene(Stage primaryStage) {
        Scene scene = new Scene(new Group(gridRoot, pointRoot, snakeRoot, scoreRoot, gamePause, powerUp), WIDTH_CANVAS, SCENE_HEIGHT, Color.GREEN);
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
            tail.setRotate(90 * dataXY.get(i).get(2));
            tail.setFill(new ImagePattern(imgSnakeBody));
            // tail.setFill(Color.FORESTGREEN);
            snakeRoot.getChildren().add(tail);
        }
        Rectangle head = new Rectangle(snake.getX() * tileSize, snake.getY() * tileSize, tileSize, tileSize);
        head.setRotate(90 * dataXY.get(dataXY.size()-1).get(2));
        head.setFill(new ImagePattern(imgSnakeHead));

        snakeRoot.getChildren().add(head);
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
                // pointSpawn.setFill(Color.YELLOW);
            } else {
                pointSpawn.setFill(new ImagePattern(imgFoodRed));
                // pointSpawn.setFill(Color.RED);
            }
            pointRoot.getChildren().add(pointSpawn);
        }
    }

    public void drawScore(int score) {
        scoreRoot.getChildren().clear();
        Text scoreText = new Text("Score: " + score);
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        scoreText.setFill(Color.rgb(255, 255, 255, 0.5));
        scoreText.setStroke(Color.rgb(255, 255, 255, 0.7));
        double textX = 12;
        double textY = SCENE_HEIGHT-12;
        scoreText.setX(textX);
        scoreText.setY(textY);
        scoreRoot.getChildren().add(scoreText);
    }


    public void drawGameIsPaused(Stage primaryStage) {
        Text gamePauseText = new Text("Pause");
        gamePauseText.setFont(Font.font("Arial", FontWeight.BOLD, 70));
        gamePauseText.setFill(Color.WHITE);
        gamePauseText.setStroke(Color.BLACK);
        double textX = WIDTH_CANVAS / 2 - gamePauseText.getLayoutBounds().getWidth() / 2;
        double textY = HEIGHT_CANVAS / 2;
        gamePauseText.setX(textX);
        gamePauseText.setY(textY);
        gamePause.getChildren().add(gamePauseText);
    }

    public void drawGameNotPaused(Stage primaryStage) {
        gamePause.getChildren().clear();
    }

    public void setSnakeSkin(Image imgSnakeHead, Image imgSnakeBody) {
        this.imgSnakeHead = imgSnakeHead;
        this.imgSnakeBody = imgSnakeBody;
    }

    public void setPointSkin(Image imgFoodRed, Image imgFoodYellow) {
        this.imgFoodRed = imgFoodRed;
        this.imgFoodYellow = imgFoodYellow;
    }

    public void setBackground(Image imgBackground) {
        this.imgBackground = imgBackground;
    }

    public void drawPowerUp(Stage primaryStage, String ability){
        powerUp.getChildren().clear();
        Text powerUpInfo = new Text("+ " + ability);
        powerUpInfo.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        powerUpInfo.setFill(Color.rgb(255, 255, 255, 0.5));
        powerUpInfo.setStroke(Color.rgb(0, 0,0, 0.5));
        double textX = WIDTH_CANVAS / 2;
        double textY = SCENE_HEIGHT - 12;
        powerUpInfo.setX(textX);
        powerUpInfo.setY(textY);
        powerUp.getChildren().add(powerUpInfo);
    }

    public void clearDraw(Stage primaryStage){
        StackPane root = (StackPane) primaryStage.getScene().getRoot();
        root.getChildren().clear();
    }
}
