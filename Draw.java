import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Draw extends JPanel{
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

    Draw(int gridX, int gridY, int canvasSize) {
        this.gridX = gridX;
        this.gridY = gridY;
        this.canvasSize = canvasSize;

        setPreferredSize(new Dimension(this.canvasSize, this.canvasSize));
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGrid(g);
    }

    public void drawGrid(Graphics g){
        tileSize = canvasSize/gridX;

        for (int i = 0; i < tileSize; i++) {
            g.drawLine(i*tileSize, 0, i*tileSize, canvasSize);
            g.drawLine(0, i*tileSize, canvasSize, i*tileSize);
        }
    }
}
