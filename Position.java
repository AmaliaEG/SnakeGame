import java.util.ArrayList;

public class Position {
    private int n, m;

public Position(int n, int m) {
    this.n = n;
    this.m = m;
}

public ArrayList<ArrayList<Integer>> spawnPoint() { // Head is index 0
    ArrayList<ArrayList<Integer>> snakePosition = new ArrayList<>();
    snakePosition.get(0).add(n/2);
    snakePosition.get(0).add(m/2);
    snakePosition.get(1).add((n/2) + 1);
    snakePosition.get(1).add(m/2);
    return snakePosition;
}

public void drawBody(ArrayList<ArrayList<Integer>> snakePosition) {
    int length = snakePosition.get(0).size();
    for (int i = 0; i < length; i++) {
        int xPosition = snakePosition.get(i).get(1);
        int yPosition = snakePosition.get(i).get(2);
        StdDraw.filledSquare(xPosition, yPosition, 1);
    }
}



}
