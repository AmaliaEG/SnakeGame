import java.util.ArrayList;
import java.util.Random;

public class Point {

    private int maxX, maxY;
    public ArrayList<ArrayList<Integer>> pointList = new ArrayList<ArrayList<Integer>>();
    private int randomX;
    private int randomY;
    private Random random = new Random();

    public Point(int gridX, int gridY) {
        this.maxX = gridX;
        this.maxY = gridY;
    }

    public void generateRandomPoint(Position snake, int numberOfPoints) {
        for (int i = 0; i < numberOfPoints; i++) {
            do {
                randomX = random.nextInt(maxX);
                randomY = random.nextInt(maxY);
            } while (occupiedSpaces(randomX, randomY, snake));
            
            ArrayList<Integer> point = new ArrayList<>();
            point.add(randomX);
            point.add(randomY);
            pointList.add(point);
        }
    }

    public boolean occupiedSpaces(int randomX, int randomY, Position snake) {
        for (ArrayList<Integer> bodyPart : snake.getPosition()) {
            if (randomX == bodyPart.get(0) && randomY == bodyPart.get(1)) {
                return true;
            }
        }
        for (ArrayList<Integer> existingPoint : pointList) {
            if (randomX == existingPoint.get(0) && randomY == existingPoint.get(1)) {
                return true;
            }
        }
        return false;
    }

    public void deletePoint(int pointX, int pointY) {
        for (int i = 0; i < pointList.size(); i++) {
            ArrayList<Integer> point = pointList.get(i);
            if (point.get(0) == pointX && point.get(1) == pointY) {
                pointList.remove(i);
                break;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getPointList() {
        return pointList;
    }

    public int getX() {
        return randomX;
    }

    public int getY() {
        return randomY;
    }
}
