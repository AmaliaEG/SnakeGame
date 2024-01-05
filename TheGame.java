import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeGame extends Application {

    public static final int MAX_CANVAS = 600;
    private int HEIGHT_CANVAS;
    private int WIDTH_CANVAS;
    private int X_GRID_FROM_USER;
    private int Y_GRID_FROM_USER;
    private int tileSize;
    private Group root = new Group();
    private Position snake;
    private boolean north, south, east, west;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Get user input for grid size
        // For simplicity, hardcoding grid size, you can replace this with your logic
        X_GRID_FROM_USER = 20;
        Y_GRID_FROM_USER = 20;

        calculateCanvasSize();
        createGrid(primaryStage);

        snake = new Position(X_GRID_FROM_USER, Y_GRID_FROM_USER);
        snake.spawnPoint();
        drawSnake(snake);

        Scene scene = new Scene(root, WIDTH_CANVAS, HEIGHT_CANVAS);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        primaryStage.setScene(scene);
        primaryStage.show();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateSnakePosition();
                drawSnake(snake);
            }
        }, 0, 200);  // Adjust the timer delay based on your preference
    }

    private void calculateCanvasSize() {
        int maxGridSize = Math.max(X_GRID_FROM_USER, Y_GRID_FROM_USER);
        tileSize = MAX_CANVAS / maxGridSize;
        WIDTH_CANVAS = tileSize * X_GRID_FROM_USER;
        HEIGHT_CANVAS = tileSize * Y_GRID_FROM_USER;
    }

    private void createGrid(Stage primaryStage) {
        Group gridLines = new Group();

        for (int i = 0; i <= X_GRID_FROM_USER; i++) {
            Line lineVertical = new Line(i * tileSize, 0, i * tileSize, HEIGHT_CANVAS);
            lineVertical.setStroke(Color.FORESTGREEN);
            gridLines.getChildren().add(lineVertical);
        }

        for (int i = 0; i <= Y_GRID_FROM_USER; i++) {
            Line lineHorizontal = new Line(0, i * tileSize, WIDTH_CANVAS, i * tileSize);
            lineHorizontal.setStroke(Color.FORESTGREEN);
            gridLines.getChildren().add(lineHorizontal);
        }

        root.getChildren().add(gridLines);
    }

    private void drawSnake(Position snake) {
        root.getChildren().removeIf(node -> node instanceof Rectangle);

        Group snakeBody = new Group();
        List<ArrayList<Integer>> dataXY = snake.getPosition();

        for (ArrayList<Integer> position : dataXY) {
            Rectangle segment = new Rectangle(position.get(0) * tileSize, position.get(1) * tileSize, tileSize, tileSize);
            segment.setFill(Color.LIGHTPINK);
            snakeBody.getChildren().add(segment);
        }

        root.getChildren().add(snakeBody);
    }

    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case UP:
                if (!south) {
                    north = true;
                    east = false;
                    west = false;
                }
                break;

            case DOWN:
                if (!north) {
                    south = true;
                    east = false;
                    west = false;
                }
                break;

            case LEFT:
                if (!east) {
                    west = true;
                    north = false;
                    south = false;
                }
                break;

            case RIGHT:
                if (!west) {
                    east = true;
                    north = false;
                    south = false;
                }
                break;
        }
    }

    private void updateSnakePosition() {
        if (north) snake.move(0, -1);
        if (south) snake.move(0, 1);
        if (east) snake.move(1, 0);
        if (west) snake.move(-1, 0);

        snake.getBigger();
    }
}


