import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid size between 5-100 (inclusive): ");
        int size = sizeInput.nextInt();
        drawTrack(size);
        sizeInput.close();
    }

    public static void drawTrack(int n) {
        StdDraw.setXscale(-2*n, 2*n);
        StdDraw.setYscale(-2*n, 2*n);
        StdDraw.square(0, 0, 2*n);
        double dn = n * 2;
        double corner = -dn + 0.5;
        for (double i = corner; i < dn; i++) {
            for (double j = corner; j < dn; j++) {
                StdDraw.square(i, j, 0.5);
            }
        }
    }
}

