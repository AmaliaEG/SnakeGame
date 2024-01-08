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
import javafx.scene.image.*;


public class Draw{
    public static final int MAX_CANVAS = 600;

    // User defined visual properties for the game
    int backroundColor = 0;
    int snakeColor = 0;
    Image img = new Image("Skins\\pxArt__2_-removebg-preview.png");

    private int HEIGHT_CANVAS;
    private int WIDTH_CANVAS;
    
    public int gridXInput;
    public int gridYInput;
    private int tileSize;

    private Group gridRoot = new Group();
    private Group snakeRoot = new Group();
    private Group pointRoot = new Group();
    private Group scoreRoot = new Group();
    private Group mysteryBoxRoot = new Group();

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
        Scene scene = new Scene(new Group(gridRoot, pointRoot, mysteryBoxRoot, snakeRoot, scoreRoot), WIDTH_CANVAS, HEIGHT_CANVAS, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public void drawGrid() {
        for (int i = 0; i <= gridXInput; i++) {
            Line lineVertical = new Line(i * tileSize, 0, i * tileSize, HEIGHT_CANVAS);
            lineVertical.setStroke(Color.WHITE);
            gridRoot.getChildren().add(lineVertical);
        }

        for (int i = 0; i <= gridYInput; i++) {
            Line lineHorizontal = new Line(0, i * tileSize, WIDTH_CANVAS, i * tileSize);
            lineHorizontal.setStroke(Color.WHITE);
            gridRoot.getChildren().add(lineHorizontal);
        }
    }

    public void drawSnake(Position snake) {
        snakeRoot.getChildren().clear();
        ArrayList<ArrayList<Integer>> dataXY = snake.getPosition();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            Rectangle tail = new Rectangle(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize, tileSize, tileSize);
            tail.setFill(Color.FORESTGREEN);
            snakeRoot.getChildren().add(tail);
        }
        Rectangle head = new Rectangle(snake.getX() * tileSize, snake.getY() * tileSize, tileSize, tileSize);
        head.setFill(new ImagePattern(img));
        //head.setFill(Color.DARKGREEN);
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
                pointSpawn.setFill(Color.RED);
                pointRoot.getChildren().add(pointSpawn);  
            } else if (pointType == 1) {
                pointSpawn.setFill(Color.YELLOW);
                mysteryBoxRoot.getChildren().add(pointSpawn);
            }
        }
    }

    public void drawScore(int score) {
        scoreRoot.getChildren().clear();
        Text scoreText = new Text("Score: " + score);
        scoreText.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        scoreText.setFill(Color.FORESTGREEN);
        double textX = WIDTH_CANVAS / 2 - scoreText.getLayoutBounds().getWidth() / 2;;
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
}
