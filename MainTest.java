import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyEvent;
import java.util.*;
import java.awt.*;
import javafx.event.EventHandler;
//import java.awt.event.*;



public class MainTest extends Application {
    private int velocityX = 0;
    private int velocityY = 0;
    private Position snake;

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

        //Sets up key event handling
        Scene scene = new Scene(drawCanvas.getRoot(), drawCanvas.WIDTH_CANVAS, drawCanvas.HEIGHT_CANVAS);
        scene.setOnKeyPressed(this::handleKeyPress);
        scene.setOnKeyReleased(this::handleKeyRelease);
        primaryStage.setScene(scene);
        primaryStage.show();

        javafx.animation.AnimationsTimer animationTimer = new javafx.animation.AnimationTimer(){
            @Override
            public void handle(long now) {
                updateSnakePosition(snake);
                drawCanvas.drawSnake(snake);
            }
        };
        animationTimer.start();
    }
        
        /*scene.setOnKeyPressed(new EventHandler <KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case UP: north = true; break;
                    case DOWN: south = true; break;
                    case LEFT: west = true; break;
                    case RIGHT: east = true; break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler <KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case UP: north = false; break;
                    case DOWN: south = false; break;
                    case LEFT: west = false; break;
                    case RIGHT: east = false; break;
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        //AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, int dy = 0;

                if (north) dy -= 1;
                if (south) dy += 1;
                if (east) dx += 1;
                if (west) dx -= 1;*/
    }

    private void handleKeyPress(KeyEvent e) {
        switch (e.getCode()) {
            case UP:
                if(velocityY!= 1) {
                    velocityX = 0;
                    velocityY = -1;
                }
                break;

            case DOWN:
                if(velocityY!= -1) {
                    velocityX = 0;
                    velocityY = 1;
                }
                break;

            case LEFT:
                if(velocityX!= 1) {
                    velocityX = -1;
                    velocityY = 0;
                }
                break;
            case RIGHT:
                if(velocityX!= -1) {
                    velocityX = 1;
                    velocityY = 0;
                }
                break;
        }
    }

    private void handleKeyRelease(KeyEvent e) {
        velocityX = 0;
        velocityY = 0;
    }

    private void updateSnakePosition() {
        int headX = snake.getXPosition();
        int headY = snake.getYPosition();

        headX += velocityX;
        headY += velocityY;

        snake.moveBody(snake, headX, headY);
        snake.getBigger(headX, headY);
            

    /*public MainTest(){
        addKeyListener(this);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
            velocityX = 0;
            velocityY = -1;
        }

        else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1){
            velocityX = 0;
            velocityY = 1;
        }

        else if(e.getKeyCode() == KeyEvent.VK_LEFT && velocityX != 1) {
            velocityX = -1;
            velocityY = 0;
        }

        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && velocityX != -1) {
            velocityX = 1;
            velocityY = 0;
        }
    }

    //Needs to be defined however not used
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }*/

}
