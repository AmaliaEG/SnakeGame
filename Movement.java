import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Movement implements KeyListener {
    private Position position;
    int velocityX = 0;
    int velocityY = 0;
    public static void main(String[] args) {

    }

    public Movement(Position position) {
        this.position = position;
        addKeyListener(this);
        setFocusable(true);
    }
    public void move(){
        Integer[][] snakePosition = position.getSnakePosition;
        getXposition.snakePosition[][] += velocityX;
        getYPosition.snakePosition[][] += velocityY; 
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