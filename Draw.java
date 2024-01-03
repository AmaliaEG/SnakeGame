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

    int width;
    int height;
    int tileSize = 25;

    Draw(int width, int height) {
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
}
