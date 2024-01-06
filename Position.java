import java.util.ArrayList;

public class Position{
    public int score = 0;
    private int n, m;
    public ArrayList<ArrayList<Integer>> snakePosition = new ArrayList<ArrayList<Integer>>();

public Position(int n, int m) {
    this.n = n;
    this.m = m;
}

public ArrayList<ArrayList<Integer>> spawnPoint() {
    int k = 2; // To play around with starting snake size, for test purposes
    for (int i = k; i >= 0; i--) {
        ArrayList<Integer> snakeBody = new ArrayList<Integer>();
        snakeBody.add((n/2) + i);
        snakeBody.add(m/2);
        snakePosition.add(snakeBody);
    }
    return snakePosition;
}

public ArrayList<ArrayList<Integer>> moveBody(Position snake, int headX, int headY) {
    int iterations = snake.getSize() - 1;
    for (int i = 0; i < iterations; i++) {
        snakePosition.get(i).set(0, snakePosition.get(i + 1).get(0));
        snakePosition.get(i).set(1, snakePosition.get(i + 1).get(1));
    }
    int newX = snake.getX() + headX;
    int newY = snake.getY() + headY;
    snakePosition.get(iterations).set(0, newX);
    snakePosition.get(iterations).set(1, newY);
    return snakePosition;
}

public void getBigger(int x, int y, Draw canvas) {
    ArrayList<Integer> newHead = new ArrayList<Integer>();
    newHead.add(x);
    newHead.add(y);
    snakePosition.add(newHead);
    score += 100;
    canvas.drawScore(score);
}

public void wallJump(int gridHeight, int gridWidth, Position snake) {
    int headXValue = snake.getX();
    int headYValue = snake.getY();
    if (headXValue == gridWidth) {
        snakePosition.get(snake.getSize() - 1).set(0, 0);
    }
    else if (headXValue < 0) {
        snakePosition.get(snake.getSize() - 1).set(0, gridWidth-1);
    }
    else if (headYValue == gridHeight) {
        snakePosition.get(snake.getSize() - 1).set(1, 0);
    }
    else if (headYValue < 0) {
        snakePosition.get(snake.getSize() - 1).set(1, gridHeight-1);
    }
}

public boolean suicide(Position snake) {
    int headX = snake.getX();
    int headY = snake.getY();

    for (int i = 0; i < snake.getSize() - 1; i++) {
        int bodyX = snakePosition.get(i).get(0);
        int bodyY = snakePosition.get(i).get(1);

        if (headX == bodyX && headY == bodyY) {
            return true;
        }
    }

    return false;
}

public ArrayList<ArrayList<Integer>> getPosition() {
    return snakePosition;
}
public int getX() {
    return snakePosition.get(snakePosition.size() - 1).get(0);
}

public int getY() {
    return snakePosition.get(snakePosition.size() - 1).get(1);
}

public int getSize() {
    return snakePosition.size();
}

public int getScore() {
    return score;
}

public String toString() {
    String s = "";
    for (int i = 0; i < snakePosition.size(); i++) {
        for (int j = 0; j < 2; j++) {
            s = s + snakePosition.get(i).get(j) + "Body part: " + i + "\n";
        }
    }
    return s;
}

}

