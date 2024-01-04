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
    for (int i = 0; i > iterations; i++) {
        snakePosition.get(i).set(0, snakePosition.get(i + 1).get(0));
        snakePosition.get(i).set(1, snakePosition.get(i + 1).get(1));
    }
    int newX = snake.getXPosition() + headX;
    int newY = snake.getYPosition() + headY;
    snakePosition.get(iterations).set(0, newX);
    snakePosition.get(iterations).set(1, newY);
}

public void wallJump(int gridHeight, int gridWidth, Position snake) {
    int headXValue = snake.getXPosition();
    int headYValue = snake.getYPosition();
    if (headXValue == gridWidth) {
        newSnake.get(snake.getSize() - 1).set(0, 0);
    }
    else if (headXValue < 0) {
        newSnake.get(snake.getSize() - 1).set(0, gridWidth-1);
    }
    else if (headYValue == gridHeight) {
        newSnake.get(snake.getSize() - 1).set(1, 0);
    }
    else if (headYValue < 0) {
        newSnake.get(snake.getSize() - 1).set(1, gridHeight-1);
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

