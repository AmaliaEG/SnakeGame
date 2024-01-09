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
    public boolean powerUp = false;
    public boolean firstMove = true;
    public boolean gameOver = false;
    public int lastDirection = 0;
    private Draw canvas;
    int pointX, pointY, pointType;
    public boolean gamePause = false;
    private long speed = 150000000;
    private Random random = new Random();

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

        canvas = new Draw(gridX, gridY);
        canvas.gridXInput = gridX;
        canvas.gridYInput = gridY;
        canvas.calculateCanvasSize();
        canvas.initializeScene(primaryStage);

        canvas.drawGrid();

        Position snake = new Position(gridX, gridY);
        snake.spawnPoint();
        canvas.drawSnake(snake, lastDirection);

        Point p = new Point(gridX, gridY);
        p.generateRandomPoint(snake, 3);
        canvas.drawPoint(p.getPointList());

        canvas.drawScore(snake.getScore());

        Scene scene = primaryStage.getScene();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER: // Pause function
                        if (gamePause) {
                            gamePause = false;
                            canvas.drawGameNotPaused(primaryStage);
                        } else {
                            gamePause = true;
                        }
                        break;
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
                        if (!(lastDirection == 4) && !(firstMove)) {
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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver) {
                    if (now - lastUpdateTime >= speed) {
                        apple = checkForPoint(snake, p);
                        lastUpdateTime = now;
                        if (!gamePause) { // Pause until gamePause is false.
                            if (apple) {
                                if (powerUp) {
                                    int surprise = random.nextInt(10);
                                    switch (surprise) {
                                        case 1:
                                            speed *= 0.9;
                                            break;
                                        case 2:
                                            speed *= 1.1;
                                            break;
                                        case 3:
                                            snake.multiplier += 1;
                                            break;
                                    }
                                } else {
                                    speed = acceleration(speed);
                                }
                                snake.getBigger(pointX, pointY, pointType, canvas);
                                p.deletePoint(pointX, pointY, pointType);
                                p.generateRandomPoint(snake, 1);
                                canvas.drawPoint(p.getPointList());
                                apple = false;
                                powerUp = false;
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
                        } else {
                            canvas.drawGameIsPaused(primaryStage);
                        }

                        snake.wallJump(gridY, gridX, snake);
                        snake.suicide(snake);
                        if (snake.suicide(snake)) {
                            gameOver = true;
                        }
                        canvas.drawSnake(snake, lastDirection);
                        System.out.println(snake);
                    }
                } else {
                    canvas.drawGameOver(primaryStage);
                    stop();
                }
            }
        };
        timer.start();
    }

    public long acceleration(long speed) {
        if (speed < 15000000) {
            ;
        } else {
            speed *= 0.99;
        }
        return speed;
    }

    public boolean checkForPoint(Position snake, Point p) {
        boolean apple = false;
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

        for (ArrayList<Integer> point : p.getPointList()) {
            pointX = point.get(0);
            pointY = point.get(1);
            pointType = point.get(2);

            if (snakeNextX == pointX && snakeNextY == pointY) {
                if (pointType == 0) {
                    powerUp = true;
                }
                apple = true;
                break;
            }
        }
        return apple;
    }
}
