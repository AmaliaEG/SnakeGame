import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class DrawCanvas extends Application {

    public int MAX_CANVAS = 800;
    public int HEIGHT_CANVAS;
    public int WIDTH_CANVAS;
    public int X_GRID_FROM_USER = 5;
    public int Y_GRID_FROM_USER = 7;
    public int tileSize;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        calculateCanvasSize();
        createGrid(primaryStage);
    }

    public void setGridSize(int x, int y) {
        this.X_GRID_FROM_USER = x;
        this.Y_GRID_FROM_USER = y;

        calculateCanvasSize();
    }

    private void calculateCanvasSize() {

        if (X_GRID_FROM_USER > Y_GRID_FROM_USER) {
            tileSize = MAX_CANVAS / X_GRID_FROM_USER;
            WIDTH_CANVAS = MAX_CANVAS;
            HEIGHT_CANVAS = tileSize * Y_GRID_FROM_USER;
        } else if (X_GRID_FROM_USER < Y_GRID_FROM_USER) {
            tileSize = MAX_CANVAS / Y_GRID_FROM_USER;
            HEIGHT_CANVAS = MAX_CANVAS;
            WIDTH_CANVAS = tileSize * X_GRID_FROM_USER;
        } else {
            tileSize = MAX_CANVAS / X_GRID_FROM_USER;
            HEIGHT_CANVAS = MAX_CANVAS;
            WIDTH_CANVAS = MAX_CANVAS;
        }
    }

    private void createGrid(Stage primaryStage) {
        Group gridLines = new Group();

        for (int i = 0; i <= X_GRID_FROM_USER; i++) {
            Line lineVertical = new Line(i * tileSize, 0, i * tileSize, HEIGHT_CANVAS);

            lineVertical.setStroke(Color.WHITESMOKE);

            gridLines.getChildren().add(lineVertical);
        }

        for (int i = 0; i <= Y_GRID_FROM_USER; i++) {
            Line lineHorizontal = new Line(0, i * tileSize, WIDTH_CANVAS, i * tileSize);

            lineHorizontal.setStroke(Color.WHITESMOKE);

            gridLines.getChildren().add(lineHorizontal);
        }

        Group root = new Group(); // Look at the Word Document
        Scene scene = new Scene(root, WIDTH_CANVAS, HEIGHT_CANVAS, Color.BLACK);

        root.getChildren().add(gridLines);

        primaryStage.setScene(scene);
        primaryStage.show();
    
    }

}
