import java.util.ArrayList;

public class Position {
    private int n, m;
    public ArrayList<ArrayList<Integer>> snakePosition = new ArrayList<ArrayList<Integer>>();

public Position(int n, int m) {
    this.n = n;
    this.m = m;
}

public ArrayList<ArrayList<Integer>> spawnPoint() { // Head is index 0
    ArrayList<Integer> head = new ArrayList<Integer>();
    ArrayList<Integer> tail = new ArrayList<Integer>();
    head.add(n/2);
    head.add(m/2);
    tail.add((n/2)+1);
    tail.add(m/2);
    snakePosition.add(tail);
    snakePosition.add(head);
    return snakePosition;
}

public void drawBody(ArrayList<ArrayList<Integer>> snakePosition) {
    int length = snakePosition.size();
    for (int i = 0; i < length; i++) {
        int xPosition = snakePosition.get(i).get(0);
        int yPosition = snakePosition.get(i).get(1);
        StdDraw.filledSquare(xPosition, yPosition, 1);
    }
}

public void getBigger(int x, int y) {
    ArrayList<Integer> newHead = new ArrayList<Integer>();
    newHead.add(x);
    newHead.add(y);
    snakePosition.add(newHead);
}

public String toString() {
    String s = "";
    for (int i = 0; i < snakePosition.size(); i++) {
        for (int j = 0; j < 2; j++) {
            s = s + snakePosition.get(i).get(j) + "\n";
        }
    }
    return s;
}

public ArrayList<ArrayList<Integer>> getPosition() {
    return snakePosition;
}
public int getXPosition() {
    return snakePosition.get(snakePosition.size() - 1).get(0);
}

public int getYPosition() {
    return snakePosition.get(snakePosition.size() - 1).get(1);
}

}
