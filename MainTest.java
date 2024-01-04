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

public class MainTest {

    public static void main(String[] args) {
        Scanner sizeInput = new Scanner(System.in);
        System.out.print("Input a grid width between 5-100 (inclusive): ");
        int xGridUser = sizeInput.nextInt();
        System.out.print("Input a grid height between 5-100 (inclusive): ");
        int yGridUser = sizeInput.nextInt();
        sizeInput.close();

        DrawCanvas canvas = new DrawCanvas(); 
        canvas.calculateCanvasSize(xGridUser, yGridUser);
        Application.launch(DrawCanvas.class);
    }
}