import javafx.application.Application;

public class DrawCanvasTest {
    public static void main(String[] args) throws Exception {
        int x = 5;
        int y = 7;

        DrawCanvas canvas = new DrawCanvas(); 
        canvas.setGridSize(x, y);
        Application.launch(DrawCanvas.class);
    }
}
