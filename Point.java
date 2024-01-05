import java.util.*;

public class Point {

    private int maxX, maxY;
    private int randomX;
    private int randomY;
    private Random random = new Random();

    public Point(int gridX, int gridY) {
        this.maxX = gridX;
        this.maxY = gridY;
    }

    public void generateRandomPoint(Position snake) {
        randomX = random.nextInt(maxX);
        randomY = random.nextInt(maxY);
        while (occupiedSpaces(randomX, randomY, snake)) {
            randomX = random.nextInt(maxX);
            randomY = random.nextInt(maxY);
        }
    }

    public boolean occupiedSpaces(int randomX, int randomY, Position snake) {
        ArrayList<ArrayList<Integer>> snakePosition = snake.getPosition();
        for (int i = 0; i < snake.getSize(); i++) {
            if ((randomX == snakePosition.get(i).get(0)) && (randomY == snakePosition.get(i).get(1))) {
                return true;
            }
        }
        return false;
    }

    public int getX() {
        return randomX;
    }
    public int getY() {
        return randomY;
    }
}
