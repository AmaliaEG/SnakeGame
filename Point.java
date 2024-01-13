import java.util.ArrayList;
import java.util.Random;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Point {

    private int maxX, maxY;
    public ArrayList<ArrayList<Integer>> pointList = new ArrayList<ArrayList<Integer>>();
    private int randomX;
    private int randomY;
    public int type;
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

            type = random.nextInt(10);
            ArrayList<Integer> point = new ArrayList<>();
            point.add(randomX);
            point.add(randomY);
            point.add(type);
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

    public void deletePoint(int pointX, int pointY, int pointType) {
        for (int i = 0; i < pointList.size(); i++) {
            ArrayList<Integer> point = pointList.get(i);
            if (point.get(0) == pointX && point.get(1) == pointY && point.get(2) == pointType) {
                pointList.remove(i);
                try {
                    String background_music = "Sound/Eat.mp3";
                    Media sound = new Media(ClassLoader.getSystemResource(background_music).toString());
                    MediaPlayer mediaE = new MediaPlayer(sound);
                    
                    mediaE.setVolume(1.5);
                    mediaE.play();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Could not play the music");
                }
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
