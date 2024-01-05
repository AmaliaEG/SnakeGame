import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import java.util.*;
import javafx.event.EventHandler;

// THIS IS THE OPTIMIZED BRANCH

public class MainTest2 extends Application {
    private long lastUpdateTime = 0;

    boolean north, south, east, west;
    boolean apple = false;
    int lastDirection = 0;

    ArrayList<Integer> pointPosition = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> allPoints = new ArrayList<ArrayList<Integer>>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        pointPosition.add(2);
        pointPosition.add(2);

        allPoints.add(pointPosition);

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
        drawCanvas.drawPoint(allPoints);

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

                if (now - lastUpdateTime >= 800000000L) {

                    lastUpdateTime = now;
                    if (apple) {
                        apple = false;
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

                    snake.wallJump(yGridUser, xGridUser, snake);
                    snake.suicide(snake);
                    drawCanvas.drawSnake(snake);
                    drawCanvas.drawPoint(allPoints);
                    apple = checkForPoint(snake, allPoints);
                    System.out.println(snake);
                }
            }
        };
        timer.start();
    }

    public boolean checkForPoint(Position snake, ArrayList<ArrayList<Integer>> pointList) {
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

        for (int i = 0; i < pointList.size(); i++) {
            
            int pointX = pointList.get(i).get(0);
            int pointY = pointList.get(i).get(1);

            if (snakeNextX == pointX && snakeNextY == pointY) {
                this.allPoints.clear();
                snake.getBigger(pointX, pointY);
                apple = true;
            }
        }

        return apple;
    }
}