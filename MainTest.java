import javafx.application.Application;
import javafx.stage.Stage;
import java.util.*;
import java.awt.*;
import java.awt.event.*;



public class MainTest extends Application implements KeyListener {
    int velocityX = 0;
    int velocityY = 0;
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
    }

    public MainTest(){
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
    }

}
