import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Movement implements KeyListener {
    int velocityX = 0;
    int velocityY = 0;
    public static void main(String[] args) {

    }

    public Movement() {
        position = new Position(10, 10);
        addKeyListener(this);
        setFocusable(true);
    }
    public void move(){
        ArrayList<ArrayList<Integer>> snakePosition = Position.getSnakePosition();
        snakePosition.get(i).get(1) += velocityX;
        snakehead.y += velocityY;
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