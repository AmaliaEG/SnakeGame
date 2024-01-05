import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.animation.AnimationTimer;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;

public class DrawCanvasTest extends Application {

    public static final int MAX_CANVAS = 600;
    int HEIGHT_CANVAS;
    int WIDTH_CANVAS;
    int X_GRID_FROM_USER;
    int Y_GRID_FROM_USER;
    int tileSize;
    private Group root = new Group();

    private Node snakie;

    boolean north, south, east, west;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group grass = new Group(snakie);

        moveSnakeTo(WIDTH_CANVAS / 2, HEIGHT_CANVAS / 2);

        Scene scene = new Scene(grass, WIDTH_CANVAS, HEIGHT_CANVAS, Color.FORESTGREEN);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    north = true; break;
                    case DOWN:  south = true; break;
                    case LEFT:  west  = true; break;
                    case RIGHT: east  = true; break;
                    default: break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:    north = false; break;
                    case DOWN:  south = false; break;
                    case LEFT:  west  = false; break;
                    case RIGHT: east  = false; break;
                    default: break;
                }
            }
        });

        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (north) dy -= 1;
                if (south) dy += 1;
                if (east)  dx += 1;
                if (west)  dx -= 1;

                moveSnakeBy(dx, dy);
            }
        };
        timer.start();
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
        Group snakeBody = new Group();
        Rectangle head = new Rectangle(snake.getXPosition() * tileSize, snake.getYPosition() * tileSize, tileSize, tileSize);
        head.setFill(Color.MAGENTA);
        snakeBody.getChildren().add(head);
        
        ArrayList<ArrayList<Integer>> dataXY = snake.getPosition();
        for (int i = dataXY.size() - 2; i >= 0; i--) {
            Rectangle tail = new Rectangle(dataXY.get(i).get(0) * tileSize, dataXY.get(i).get(1) * tileSize, tileSize, tileSize);
            tail.setFill(Color.LIGHTPINK);
            snakeBody.getChildren().add(tail);
        }
        root.getChildren().add(snakeBody);
    }

    private void moveSnakeBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = snakie.getBoundsInLocal().getWidth()  / 2;
        final double cy = snakie.getBoundsInLocal().getHeight() / 2;

        double x = cx + snakie.getLayoutX() + dx;
        double y = cy + snakie.getLayoutY() + dy;

        moveSnakeTo(x, y);
    }

    private void moveSnakeTo(double x, double y) {
        final double cx = snakie.getBoundsInLocal().getWidth()  / 2;
        final double cy = snakie.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
            x + cx <= WIDTH_CANVAS &&
            y - cy >= 0 &&
            y + cy <= HEIGHT_CANVAS) {
            snakie.relocate(x - cx, y - cy);
        }
    }

    /* public Group getRoot() {
        return root;
    } */
}
 