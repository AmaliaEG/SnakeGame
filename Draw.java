import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Draw extends Application {

    public static final int MAX_CANVAS = 600;
    private int HEIGHT_CANVAS;
    private int WIDTH_CANVAS;
    public int X_GRID_FROM_USER;
    public int Y_GRID_FROM_USER;
    private int tileSize;
    private Group gridRoot = new Group();
    private Group snakeRoot = new Group();
    private Group pointRoot = new Group();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        calculateCanvasSize();
        createGrid(primaryStage);
    }

    public void calculateCanvasSize() {
        int maxGridSize = Math.max(X_GRID_FROM_USER, Y_GRID_FROM_USER);
        this.tileSize = MAX_CANVAS / maxGridSize;
        this.WIDTH_CANVAS = tileSize * X_GRID_FROM_USER;
        this.HEIGHT_CANVAS = tileSize * Y_GRID_FROM_USER;
    }

    public void createGrid(Stage primaryStage) {
        for (int i = 0; i <= X_GRID_FROM_USER; i++) {
            Line lineVertical = new Line(i * tileSize, 0, i * tileSize, HEIGHT_CANVAS);
            lineVertical.setStroke(Color.WHITE);
            gridRoot.getChildren().add(lineVertical);
        }

        for (int i = 0; i <= Y_GRID_FROM_USER; i++) {
            Line lineHorizontal = new Line(0, i * tileSize, WIDTH_CANVAS, i * tileSize);
            lineHorizontal.setStroke(Color.WHITE);
            gridRoot.getChildren().add(lineHorizontal);
        }

        Scene scene = new Scene(new Group(gridRoot, snakeRoot, pointRoot), WIDTH_CANVAS, HEIGHT_CANVAS, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawSnake(Position snake) {
        snakeRoot.getChildren().clear();

        Rectangle head = new Rectangle(snake.getXPosition() * tileSize, snake.getYPosition() * tileSize, tileSize, tileSize);
        head.setFill(Color.DARKGREEN);
        snakeRoot.getChildren().add(head);

        ArrayList<ArrayList<Integer>> dataXY = snake.getPosition();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            Rectangle tail = new Rectangle(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize, tileSize, tileSize);
            tail.setFill(Color.FORESTGREEN);
            snakeRoot.getChildren().add(tail);
        }
    }

    public void drawPoint(Point p) {
        pointRoot.getChildren().clear();

        int pointX = p.getX();
        int pointY = p.getY();
        Rectangle pointSpawn = new Rectangle(pointX * tileSize, pointY * tileSize, tileSize, tileSize);
        pointSpawn.setFill(Color.RED);
        pointRoot.getChildren().add(pointSpawn);
    }
}
