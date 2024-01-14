import java.util.ArrayList;
import java.util.Random;

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
        do {
            randomX = random.nextInt(maxX);
            randomY = random.nextInt(maxY);
        } while (occupiedSpaces(randomX, randomY, snake));
    }

    public boolean occupiedSpaces(int randomX, int randomY, Position snake) {
        for (ArrayList<Integer> bodyPart : snake.getPosition()) {
            if (randomX == bodyPart.get(0) && randomY == bodyPart.get(1)) {
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
