import javafx.animation.AnimationTimer;
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
import java.io.File;

public class Main {
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
    private int gridX;
    private int gridY;

    String background_music = "Sound\\Blanks.mp3";
    Media sound = new Media(new File(background_music).toURI().toString());
    MediaPlayer mediaBG = new MediaPlayer(sound);

    public Main(Stage primaryStage) {
        showGridSizeInput(primaryStage);
    }

    private void showGridSizeInput(Stage primaryStage){
        VBox the_root = new VBox();
        Label labelX = new Label("Input a grid width between 5-100 (inclusive): ");
        Label labelY = new Label("Input a grid width between 5-100 (inclusive): ");
        TextField textFieldX = new TextField();
        TextField textFieldY = new TextField();
        Button createButton = new Button("Create");
        the_root.getChildren().addAll(labelX, textFieldX, labelY, textFieldY, createButton);

        the_root.setAlignment(Pos.CENTER);
        VBox.setMargin(labelX, new Insets(0, 0, 3, 0));
        VBox.setMargin(labelY, new Insets(15, 0, 3, 0));
        VBox.setMargin(textFieldX, new Insets(2, 0, 0, 0));
        VBox.setMargin(textFieldY, new Insets(2, 0, 0, 0));
        VBox.setMargin(createButton, new Insets(25, 0, 0, 0));

        textFieldX.setMaxWidth(100);
        textFieldY.setMaxWidth(100);

        the_root.setPrefWidth(200);

        Scene inputScene = new Scene(the_root, 500, 500);
        primaryStage.setScene(inputScene);

        createButton.setOnAction(event -> {
            try{
                gridX = Integer.parseInt(textFieldX.getText());
                gridY = Integer.parseInt(textFieldY.getText());

                if (gridX >= 5 && gridX <= 100 && gridY >= 5 && gridY <= 100){
                    startGame(primaryStage, gridX, gridY);
                } else{
                    System.out.println("Invalid size.");
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid input.");
            }
        });

        primaryStage.show();
    }
    


    //@Override
    private void startGame(Stage primaryStage, int gridX, int gridY) {
        /*Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int gridX = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int gridY = sizeInput.nextInt();
        sizeInput.close();*/

        try {
            mediaBG.setVolume(1.5);
            mediaBG.setCycleCount(MediaPlayer.INDEFINITE);
            mediaBG.play();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not play the music");
        }
        

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
