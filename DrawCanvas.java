import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class DrawCanvas extends Application {

    public static final int MAX_CANVAS = 600;
    int HEIGHT_CANVAS;
    int WIDTH_CANVAS;
    int X_GRID_FROM_USER;
    int Y_GRID_FROM_USER;
    int tileSize;
    private Group root = new Group();
    //Group point = new Group();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    }

    public void calculateCanvasSize() {
        int maxGridSize = Math.max(X_GRID_FROM_USER, Y_GRID_FROM_USER);
        this.tileSize = MAX_CANVAS / maxGridSize;
        this.WIDTH_CANVAS = tileSize * X_GRID_FROM_USER;
        this.HEIGHT_CANVAS = tileSize * Y_GRID_FROM_USER;
    }

    public void createGrid(Stage primaryStage) {
        Group gridLines = new Group();

        for (int i = 0; i <= X_GRID_FROM_USER; i++) {
            Line lineVertical = new Line(i * tileSize, 0, i * tileSize, HEIGHT_CANVAS);
            lineVertical.setStroke(Color.FORESTGREEN);
            gridLines.getChildren().add(lineVertical);
        }

        for (int i = 0; i <= Y_GRID_FROM_USER; i++) {
            Line lineHorizontal = new Line(0, i * tileSize, WIDTH_CANVAS, i * tileSize);
            lineHorizontal.setStroke(Color.FORESTGREEN);
            gridLines.getChildren().add(lineHorizontal);
        }

        Scene scene = new Scene(root, WIDTH_CANVAS, HEIGHT_CANVAS, Color.DARKGREEN);
        root.getChildren().add(gridLines);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void drawSnake(Position snake) {
        root.getChildren().removeIf(node -> node instanceof Group);
        Group snakeBody = new Group();

        Rectangle head = new Rectangle(snake.getXPosition() * tileSize, snake.getYPosition() * tileSize, tileSize,
                tileSize);
        head.setFill(Color.MAGENTA);
        snakeBody.getChildren().add(head);

        ArrayList<ArrayList<Integer>> dataXY = snake.getPosition();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            Rectangle tail = new Rectangle(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize, tileSize,
                    tileSize);
            tail.setFill(Color.LIGHTPINK);
            snakeBody.getChildren().add(tail);
        }
        root.getChildren().add(snakeBody);
    }

    public void drawPoint(ArrayList<ArrayList<Integer>> dataXY) {
        Group point = new Group();

        for (int i = 0; i < dataXY.size(); i++) {
            System.out.println("position of point: " + dataXY.get(i).get(0) + " and " + dataXY.get(i).get(1));

            Rectangle pointSpawn = new Rectangle(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize,
                    tileSize, tileSize);
            pointSpawn.setFill(Color.RED);
            point.getChildren().add(pointSpawn);
        }

        root.getChildren().add(point);


    }

    public Group getRoot() {
        return root;
    }
}
