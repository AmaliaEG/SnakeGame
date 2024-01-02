import java.util.Scanner;

import javafx.scene.paint.Color;

public class Main {
    public static void main(String[] args) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int width = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int height = sizeInput.nextInt();
        drawTrack(width, height);
        sizeInput.close();
    }

    public static void drawTrack(int width, int height) {
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);
        StdDraw.square(0, 0, height);
        double corner = 0;
        for (double i = corner; i < width; i++) {
            for (double j = corner; j < height; j++) {
                StdDraw.square(i, j, 1);
            }
        }
    }
}

