import java.util.ArrayList;

public class Position {
    private int n, m;
    public Integer[][] snakePosition = new Integer[2][2];

public Position(int n, int m) {
    this.n = n;
    this.m = m;
}

public Integer[][] spawnPoint() { // Head is index 0
    snakePosition[0][0] = n/2;
    snakePosition[0][1] = m/2;
    snakePosition[1][0] = (n/2) + 1;
    snakePosition[1][1] = m/2;
    return snakePosition;
}

public void drawBody(Integer[][] snakePosition) {
    int length = snakePosition[0].length;
    for (int i = 0; i < length; i++) {
        int xPosition = snakePosition[i][0];
        int yPosition = snakePosition[i][1];
        StdDraw.filledSquare(xPosition, yPosition, 1);
    }
}

public void getBigger(int x, int y) {

}

public String toString() {
    String s = "";
    for (int i = 0; i < snakePosition[0].length; i++) {
        for (int j = 0; j < snakePosition.length; j++) {
            s = s + snakePosition[i][j] + "\n";
        }
    }
    return s;
}

public Integer[][] getPosition() {
    return snakePosition;
}

}
