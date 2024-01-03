import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.shape.Rectangle;


public class chathjaelp extends Application {

    private GridPane gridPane = new GridPane();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake Game Grid Configuration");

        // Create UI elements
        Label rowsLabel = new Label("Rows:");
        TextField rowsTextField = new TextField();

        Label columnsLabel = new Label("Columns:");
        TextField columnsTextField = new TextField();

        Button createGridButton = new Button("Create Grid");

        // Add UI elements to the grid
        gridPane.add(rowsLabel, 0, 0);
        gridPane.add(rowsTextField, 1, 0);

        gridPane.add(columnsLabel, 0, 1);
        gridPane.add(columnsTextField, 1, 1);

        gridPane.add(createGridButton, 0, 2, 2, 1); // Spanning two columns

        // Set button action
        createGridButton.setOnAction(e -> createGrid(
                Integer.parseInt(rowsTextField.getText()),
                Integer.parseInt(columnsTextField.getText())));

        // Set grid layout properties
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Set column constraints
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHalignment(HPos.CENTER);
        gridPane.getColumnConstraints().add(colConstraints);

        // Create the scene
        Scene scene = new Scene(gridPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createGrid(int rows, int columns) {
        // Clear existing grid content
        gridPane.getChildren().clear();

        // Create a new grid with the specified rows and columns
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                Rectangle cellButton = new Rectangle(500/rows, 500/columns);
                gridPane.add(cellButton, col, row);
            }
        }
    }
}

