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

    int gridX;
    int gridY;
    int canvasSize;
    int tileAmount;
    int tileSize = 25;

    Tile snakeTile;
    Position snakePosition;

    Draw2(int gridX, int gridY, int canvasSize) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.canvasSize = canvasSize;

        setPreferredSize(new Dimension(this.canvasSize, this.canvasSize));
        setBackground(Color.BLACK);

        snakePosition = new Position(gridX, gridY);
        snakePosition.spawnPoint();
        snakeTile = new Tile(snakePosition.getXPosition() * tileSize, snakePosition.getYPosition() * tileSize);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
        drawSnake(g);
    }

    public void drawGrid(Graphics g){
        tileSize = canvasSize/gridX;
        for (int i = 0; i < tileSize; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, canvasSize);
            g.drawLine(0, i*tileSize, canvasSize, i*tileSize);
        }
    }

    public void drawSnake(Graphics g) {
        g.setColor(Color.yellow); // Head
        g.fillRect(snakeTile.x, snakeTile.y, tileSize, tileSize);

        ArrayList<ArrayList<Integer>> dataXY = snakePosition.getPosition();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            g.setColor(Color.green);
            g.fillRect(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize, tileSize, tileSize);
        }
    }
}

