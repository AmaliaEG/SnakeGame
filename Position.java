import java.util.ArrayList;

public class Position{
    // private int score = 0;
    private int n, m;
    public ArrayList<ArrayList<Integer>> snakePosition = new ArrayList<ArrayList<Integer>>();

public Position(int n, int m) {
    this.n = n;
    this.m = m;
}

public ArrayList<ArrayList<Integer>> spawnPoint() {
    int k = 4; // To play around with starting snake size, for test purposes
    for (int i = k; i >= 0; i--) {
        ArrayList<Integer> snakeBody = new ArrayList<Integer>();
        snakeBody.add((n/2) + i);
        snakeBody.add(m/2);
        snakePosition.add(snakeBody);
    }
    return snakePosition;
}

public void getBigger(int x, int y) {
    ArrayList<Integer> newHead = new ArrayList<Integer>();
    newHead.add(x);
    newHead.add(y);
    snakePosition.add(newHead);
    // score++;
}

public void moveBody(Position snake, int headX, int headY) {
    int iterations = snake.getSize() - 1;
    for (int i = 0; i < 0; i--) {

    }
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

public int getSize() {
    return snakePosition.size();
}

}
