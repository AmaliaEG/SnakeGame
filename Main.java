import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import java.util.*;
import javafx.event.EventHandler;

// THIS IS THE OPTIMIZED BRANCH

public class Main extends Application {
    private long lastUpdateTime = 0;
    private boolean north, south, east, west;
    public boolean apple = false;
    public int lastDirection = 0;
    private Draw canvas;
    int pointX;
    int pointY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int gridX = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int gridY = sizeInput.nextInt();
        sizeInput.close();

        canvas = new Draw();
        canvas.X_GRID_FROM_USER = gridX;
        canvas.Y_GRID_FROM_USER = gridY;
        canvas.calculateCanvasSize();
        canvas.createGrid(primaryStage);

        Position snake = new Position(gridX, gridY);
        snake.spawnPoint();
        canvas.drawSnake(snake);
        
        Point p = new Point(gridX, gridY);
        p.generateRandomPoint(snake);
        canvas.drawPoint(p);

        Scene scene = primaryStage.getScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        if (!(lastDirection == 2)) {
                            north = true;
                            south = false;
                            east = false;
                            west = false;
                        }
                        break;
                    case DOWN:
                        if (!(lastDirection == 1)) {
                            north = false;
                            south = true;
                            east = false;
                            west = false;
                        }
                        break;
                    case RIGHT:
                        if (!(lastDirection == 4)) {
                            north = false;
                            south = false;
                            east = true;
                            west = false;
                        }
                        break;
                    case LEFT:
                        if (!(lastDirection == 3)) {
                            north = false;
                            south = false;
                            east = false;
                            west = true;
                        }
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
                    if (apple) {
                        apple = false;
                        snake.getBigger(pointX, pointY);
                        canvas.drawPoint(p);
                    } else if (north) {
                        lastDirection = 1;
                        snake.moveBody(snake, 0, -1);
                    } else if (south) {
                        lastDirection = 2;
                        snake.moveBody(snake, 0, 1);
                    } else if (east) {
                        lastDirection = 3;
                        snake.moveBody(snake, 1, 0);
                    } else if (west) {
                        lastDirection = 4;
                        snake.moveBody(snake, -1, 0);
                    }

                    snake.wallJump(gridY, gridX, snake);
                    snake.suicide(snake);
                    canvas.drawSnake(snake);
                    apple = checkForPoint(snake, p);
                    System.out.println(snake);
                }
            }
        };
        timer.start();
    }

    public boolean checkForPoint(Position snake, Point p) {
        boolean apple = false;
        int snakeNextX = snake.getXPosition();
        int snakeNextY = snake.getYPosition();

        if (north) {
            snakeNextY -= 1;
        } else if (south) {
            snakeNextY += 1;
        } else if (east) {
            snakeNextX += 1;
        } else if (west) {
            snakeNextX -= 1;
        }

        pointX = p.getX();
        pointY = p.getY();

        if (snakeNextX == pointX && snakeNextY == pointY) {
            apple = true;
            p.generateRandomPoint(snake);
        }
        return apple;
    }
}