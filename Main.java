import java.util.Scanner;

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
        for (double i = 0; i < width; i++) {
            for (double j = 0; j < height; j++) {
                StdDraw.square(i, j, 1);
            }
        }
    }
}

