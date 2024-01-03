import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Draw2 extends JPanel{
    private Position position;
    private class Tile {
        int x;
        int y;

        Tile(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int width;
    int height;
    int tileSize = 25;

    Draw2(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(this.width, this.height));
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
    }

    public void drawGrid(Graphics g){
        for (int i = 0; i < width/tileSize; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, height);
            g.drawLine(0, i*tileSize, width, i*tileSize);
        }
    }

    public void drawSnake(Graphics g, Position snake) {
        g.setColor(Color.yellow); // Head
        g.fillRect(snake.getXPosition(), snake.getYPosition(), tileSize, tileSize);
        g.setColor(Color.green); // Body
        ArrayList<ArrayList<Integer>> dataXY = snake.getPosition();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            g.fillRect(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1)*tileSize, tileSize, tileSize);
        }
    }
}

