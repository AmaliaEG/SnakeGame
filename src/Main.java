import javafx.application.Application;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import java.util.*;

import javafx.event.EventHandler;

public class Main extends Application {
    private long lastUpdateTime = 0;
    private boolean north, south, east, west;
    public boolean apple = false;
    public boolean firstMove = true;
    public boolean gameOver = false;
    public int lastDirection = 0;
    private Draw canvas;
    int pointX, pointY;
    int gridX, gridY;

    // Both the main method and the start override method, are only necessary for
    // the main class, which is the one you run to run the whole code, it is the
    // entry point of the program.
    // Therefore i removed it from the draw class.
    public static void main(String[] args) {
        launch(args); // initializes the start method, launch is a method provided by Application
                      // class.
    }

    @Override // Overrides the start method in the super class which is the application class,
              // we use the Stage parameter to create the application
    public void start(Stage primaryStage) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        gridX = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        gridY = sizeInput.nextInt();
        sizeInput.close();

        canvas = new Draw(gridX, gridY);
        canvas.gridXInput = gridX; //Not nessecary, isn't really used
        canvas.gridYInput = gridY; //Not nessecary, isn't really used
        canvas.calculateCanvasSize();
        canvas.initializeScene(primaryStage); // Seperate method from all the draw methods, it initializes the scene,
                                              // and layers the groups so that the snake is above the grid etc.

        canvas.drawGrid();

        Position snake = new Position(gridX, gridY);
        snake.spawnPoint();
        canvas.drawSnake(snake);

        Point p = new Point(gridX, gridY);
        p.generateRandomPoint(snake);
        canvas.drawPoint(p);

        canvas.drawScore(snake.getScore());

        Scene scene = primaryStage.getScene();  // Why did we use scene instead of stage?
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() { // sets up an event handler for key press events, this
                                                             // method is from the Scene class.
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) { // switch case, that looks at the following 4 key options
                    case UP:
                        if (!(lastDirection == 2)) {
                            north = true;
                            south = false;
                            east = false;
                            west = false;
                            firstMove = false;
                        }
                        break;
                    case DOWN:
                        if (!(lastDirection == 1)) {
                            north = false;
                            south = true;
                            east = false;
                            west = false;
                            firstMove = false;
                        }
                        break;
                    case RIGHT:
                        if (!(lastDirection == 4) && !(firstMove)) { // Since the snake looks left by default, firstMove
                                                                     // variable makes sure the first option cant be
                                                                     // right.
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
                            firstMove = false;
                        }
                        break;
                    default:
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() { // AnimationTimer class, for frame-based animations.
            @Override
            public void handle(long now) { // this is the handle method from the Eventhandler interface.
                if (!gameOver) {  //Could have been a while-loop
                    if (now - lastUpdateTime >= 150000000) { // Now is the current time in nanoseconds.
                        apple = checkForPoint(snake, p);
                        lastUpdateTime = now; // updates the lastUpdatetime so that the difference between now and
                                              // lastupdatetime is 0.
                            if (apple) {
                                apple = false;
                                snake.getBigger(pointX, pointY, canvas);
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
                        if (snake.suicide(snake)) {
                            gameOver = true;
                        }
                        System.out.println(snake); // Here for debugging purposes
                    }
                } else {
                    canvas.drawGameOver(primaryStage);
                    stop(); // Stops the animation timer.
                }
            }
        };
        timer.start(); // Starts the animation timer, now the handle method will be called each frame.
    }

    public boolean checkForPoint(Position snake, Point p) { // Method to check the box that the snake is about to go on,
                                                            // this is neccesary for a possible point to become the new
                                                            // snake head.
                                                            // This happens before the snake moves
        int snakeNextX = snake.getX();
        int snakeNextY = snake.getY();

        if (north) {
            snakeNextY -= 1;
        } else if (south) {
            snakeNextY += 1;
        } else if (east) {
            snakeNextX += 1;
        } else if (west) {
            snakeNextX -= 1;
        }

        //Makes sure point is taken if snake is out of the boundary
        if (snakeNextX == gridX) {
            snakeNextX = 0;
        } else if (snakeNextX < 0) {
            snakeNextX = gridX - 1;
        } else if (snakeNextY == gridY) {
            snakeNextY = 0;
        } else if (snakeNextY < 0) {
            snakeNextY = gridY - 1;
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