import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javax.swing.*;

import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
        int canvasSize = 300;

        // launch(args);
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int xGridUser = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int yGridUser = sizeInput.nextInt();
        sizeInput.close();

        JFrame frame = new JFrame("Snake");
        frame.setVisible(true);
        frame.setSize(canvasSize, canvasSize);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Draw game = new Draw(xGridUser, yGridUser, canvasSize);
        frame.add(game);
        frame.pack();

    }

    @Override
    public void start(Stage primaryStage) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int width = sizeInput.nextInt() * 5;
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int height = sizeInput.nextInt() * 5;
        sizeInput.close();

        StackPane root = new StackPane();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        GridPane grid = new GridPane();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.fillRect(0, 0, width, height);
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Movement.requestFocus();
    }
}