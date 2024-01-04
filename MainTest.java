import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;

public class MainTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int xGridUser = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int yGridUser = sizeInput.nextInt();
        sizeInput.close();

        DrawCanvas drawCanvas = new DrawCanvas();
        drawCanvas.X_GRID_FROM_USER = xGridUser;
        drawCanvas.Y_GRID_FROM_USER = yGridUser;
        drawCanvas.calculateCanvasSize();
        drawCanvas.createGrid(primaryStage);
    }
}
