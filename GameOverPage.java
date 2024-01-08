import java.beans.EventHandler;

import org.w3c.dom.Text;

public class GameOver extends Application{
    private Button restart = new Button ("Restart Game");
    private Button quit = new Button ("Quit Game");
    private Text title = new text ("GAME OVER!");

    @Override
    public void end (Stage GameOver){
     frontStage.setTitle("Game Over");
        
        //Head Title
        title.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        title.setFill(Color.PINK);
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(130, 0, 0, 0));

        this.restart.setText("Restart Game");
        this.quit.setText("Quit Game");

        StackPane root = new StackPane();

        root.getChildren().addAll(restart, quit, title);

        //Aligns the buttons
        StackPane.setAlignment(restart, Pos.TOP_CENTER);
        StackPane.setAlignment(quit, Pos.CENTER);

        StackPane.setMargin(restart, new Insets(230, 0, 30, 0));
        StackPane.setMargin(quit, new Insets(110, 0, 30, 0));

        restart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                GamePage.show(frontStage);
            }
        });

        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("quit");
                frontStage.close();
            }
        });
        Scene scene = new Scene(root, 500, 500);
        frontStage.setScene(scene);
        frontStage.show();

    }
}