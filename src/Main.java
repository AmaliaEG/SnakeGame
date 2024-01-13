import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import java.util.*;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.geometry.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.File;

public class Main extends Application {

    private Draw gameBoard = new Draw();
    private Position snake;
    private Point p;
    private Scene scene;
    private Random random = new Random();
    private long speed = 150_000_000;    
    private long lastUpdateTime = 0;
    private boolean north, south, east, west;

    public int lastDirection = 0;
    public int gridX, gridY;
    public boolean apple, powerUp, gameOver, gamePause = false;
    public boolean firstMove = true;

    int pointX, pointY, pointType;

    // Music for the game
    String background_music = ClassLoader.getSystemResource("Sound/Blanks.mp3").toString();
    Media sound = new Media(background_music);
    MediaPlayer mediaBG = new MediaPlayer(sound);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pages.frontPage(primaryStage, this, gameBoard);
    }


    public void startGame(Stage primaryStage, int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;

        setBackgroundMusic();
        
        gameBoard.calculateCanvasSize(gridX, gridY);
        gameBoard.initializeScene(primaryStage);
        gameBoard.drawGrid();

        this.snake = new Position(gridX, gridY);
        snake.spawnPoint();

        gameBoard.drawSnake(snake, lastDirection);

        this.p = new Point(gridX, gridY);
        p.generateRandomPoint(snake, 3);

        gameBoard.drawPoint(p.getPointList());

        gameBoard.drawScore(snake.getScore());

        scene = primaryStage.getScene();
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case ENTER: // Pause function
                        if (gamePause) {
                            gamePause = false;
                            gameBoard.drawGameNotPaused(primaryStage);
                            mediaBG.play();
                        } else {
                            gamePause = true;
                            mediaBG.pause();
                        }
                        break;
                    case UP:
                        if (!(lastDirection == 2)) {
                            directionChange(true, false, false, false);
                        }
                        break;
                    case DOWN:
                        if (!(lastDirection == 1)) {
                            directionChange(false, true, false, false);
                        }
                        break;
                    case RIGHT:
                        if (!(lastDirection == 4) && !(firstMove)) {
                            directionChange(false, false, true, false);
                        }
                        break;
                    case LEFT:
                        if (!(lastDirection == 3)) {
                            directionChange(false, false, false, true);
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
                                    powerUpCollision();
                                }
                                appleCollision();
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
                            gameBoard.drawGameIsPaused(primaryStage);
                        }

                        snake.wallJump(gridY, gridX, snake);
                        snake.suicide(snake);
                        if (snake.suicide(snake)) {
                            gameOver = true;
                        }
                        gameBoard.drawSnake(snake, lastDirection);
                        System.out.println(snake);
                    }
                } else {
                    stop();
                    showGameOverPage(primaryStage);
                }
            }
        };
        timer.start();
    }

    private void showGameOverPage(Stage primaryStage) {
        GameOverPage gameOverPage = new GameOverPage(primaryStage);
        mediaBG.stop();
    }

    public long acceleration(long newSpeed) {
        if (newSpeed < speed) {
            ;
        } else {
            newSpeed *= 0.99;
        }
        return newSpeed;
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
            this.pointX = point.get(0);
            this.pointY = point.get(1);
            this.pointType = point.get(2);

            if (snakeNextX == pointX && snakeNextY == pointY) {
                if (pointType == 0) {
                    this.powerUp = true;
                }
                apple = true;
                break;
            }
        }
        return apple;
    }

    private void setBackgroundMusic() {
        try {
            mediaBG.setVolume(1.5);
            mediaBG.setCycleCount(MediaPlayer.INDEFINITE);
            mediaBG.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not play the music");
        }

    }

    private void directionChange(boolean north, boolean south, boolean east, boolean west){
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.firstMove = false;
    }

    private void appleCollision() {
        this.speed =  acceleration(speed);
        this.snake.getBigger(pointX, pointY, pointType, gameBoard);
        this.p.deletePoint(pointX, pointY, pointType);
        this.p.generateRandomPoint(snake, 1);
        this.gameBoard.drawPoint(p.getPointList());
        this.apple = false;
        this.powerUp = false;
    }

    private void powerUpCollision() {
        int surprise = random.nextInt(10);
        switch (surprise) {
            case 1:
                this.speed *= 0.9;
                break;
            case 2:
                this.speed *= 1.1;
                break;
            case 3:
                this.snake.multiplier += 1;
                break;
        }
        //appleCollision();
    }

}
