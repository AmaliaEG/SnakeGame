import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import java.util.*;
import javafx.event.EventHandler;



public class MainTest2 extends Application {
    private long lastUpdateTime = 0;

    boolean north, south, east, west;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int xGridUser = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int yGridUser = sizeInput.nextInt();
        sizeInput.close();

        DrawCanvas drawCanvas = new DrawCanvas();
        drawCanvas.X_GRID_FROM_USER = xGridUser;
        drawCanvas.Y_GRID_FROM_USER = yGridUser;
        drawCanvas.calculateCanvasSize();
        drawCanvas.createGrid(primaryStage);

        Position snake = new Position(xGridUser, yGridUser);
        snake.spawnPoint();
        drawCanvas.drawSnake(snake);

        Scene scene = primaryStage.getScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        north = true;
                        south = false;
                        east = false;
                        west = false;
                        break;
                    case DOWN:
                        north = false;
                        south = true;
                        east = false;
                        west = false;
                        break;
                    case RIGHT:
                        north = false;
                        south = false;
                        east = true;
                        west = false;
                        break;
                    case LEFT:
                        north = false;
                        south = false;
                        east = false;
                        west = true;
                        break;
                    default:
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdateTime >= 500000000L) {
                    lastUpdateTime = now;

                    if (north) {
                        snake.moveBody(snake, 0, -1);
                    } else if (south) {
                        snake.moveBody(snake, 0, 1);
                    } else if (east) {
                        snake.moveBody(snake, 1, 0);
                    } else if (west) {
                        snake.moveBody(snake, -1, 0);
                    }
                    snake.wallJump(yGridUser, xGridUser, snake);
                    snake.suicide(snake);
                    drawCanvas.drawSnake(snake);
                    System.out.println(snake);
                }
            }
        };
        timer.start();
    }
}