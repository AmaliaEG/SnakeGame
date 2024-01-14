import java.util.ArrayList;
import java.util.List;

public class Position{
    public int score = 0;
    private int n, m;
    public int multiplier = 1;
    public ArrayList<ArrayList<Integer>> snakePosition = new ArrayList<ArrayList<Integer>>();

    public Position(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public ArrayList<ArrayList<Integer>> spawnPoint() {
        int k = 2;
        for (int i = k; i >= 0; i--) {
            snakePosition.add(new ArrayList<>(List.of((n/2) + i, m/2, 3)));
        }
        return snakePosition;
    }

    public ArrayList<ArrayList<Integer>> moveBody(Position snake, int headX, int headY, int direction) {
        int iterations = snake.getSize() - 1;
        for (int i = 0; i < iterations; i++) {
            snakePosition.get(i).set(0, snakePosition.get(i + 1).get(0));
            snakePosition.get(i).set(1, snakePosition.get(i + 1).get(1));
            snakePosition.get(i).set(2, snakePosition.get(i + 1).get(2));
        }
        snakePosition.get(iterations).set(0, snake.getX() + headX);
        snakePosition.get(iterations).set(1, snake.getY() + headY);
        snakePosition.get(iterations).set(2, direction);

        return snakePosition;
    }

    public void getBigger(int x, int y, int pointType, Draw gameBoard, int lastDirection) {
        snakePosition.add(new ArrayList<>(List.of(x, y, lastDirection)));
        if (!(pointType == 0)) {
            score += 100 * multiplier;
        }
        gameBoard.drawScore(score);
    }

    public void wallJump(int gridHeight, int gridWidth, Position snake) {
        int headXValue = snake.getX();
        int headYValue = snake.getY();
        if (headXValue == gridWidth) {
            snakePosition.get(snake.getSize() - 1).set(0, 0);
        } else if (headXValue < 0) {
            snakePosition.get(snake.getSize() - 1).set(0, gridWidth-1);
        } else if (headYValue == gridHeight) {
            snakePosition.get(snake.getSize() - 1).set(1, 0);
        } else if (headYValue < 0) {
            snakePosition.get(snake.getSize() - 1).set(1, gridHeight-1);
        }
    }

    public boolean suicide(Position snake) {
        int headX = snake.getX();
        int headY = snake.getY();

        for (int i = 0; i < snake.getSize() - 1; i++) {
            if (headX == snakePosition.get(i).get(0) && headY == snakePosition.get(i).get(1)) {
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
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < snakePosition.size(); i++) {
            s.append("Body part: ").append(i).append(", X: ").append(snakePosition.get(i).get(0))
             .append(", Y: ").append(snakePosition.get(i).get(1)).append("\n");
        }
        return s.toString();
    }

}

