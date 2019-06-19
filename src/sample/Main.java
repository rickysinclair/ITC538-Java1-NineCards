package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;

public class Main extends Application {

    private static int usedCards = 0;  // counts how many cards are pulled from the deck
    private static int rotationValOne = 45; // rotational value for joker row (clockwise)
    private static int rotationValTwo = 135;  // rotational value for card row (anticlockwise)

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create a deck of cards
        ArrayList<Integer> cards = new ArrayList<>();  // declare cards list for 52 integers
        for (int i = 0; i < 52; i++) {
            cards.add(i + 1);
        }
        System.out.println(cards);
        // Shuffle the deck
        java.util.Collections.shuffle(cards);  // shuffle list

        // create gridpane object and format settings
        GridPane grid = new GridPane();
        grid.setHgap(40); // set gaps
        grid.setVgap(40);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));

        // add 3 random cards to first row
        grid.add(getCard(cards), 0, 0);
        grid.add(getCard(cards), 1, 0);
        grid.add(getCard(cards), 2, 0);

        // add 3 jokers to second row with rotation
        grid.add(getJoker(), 0, 1);
        grid.add(getJoker(), 1, 1);
        grid.add(getJoker(), 2, 1);

        // add 3 random cards to third row with rotation
        grid.add(getRotatedCard(cards), 0, 2);
        grid.add(getRotatedCard(cards), 1, 2);
        grid.add(getRotatedCard(cards), 2, 2);

        // create scene object and place grid in scene
        Scene scene = new Scene(grid, 600, 600);

        primaryStage.setTitle("Nine Cards");  // title of screen
        primaryStage.setScene(scene);  // place scene in stage
        primaryStage.show();  // display stage

    }

    /**
     * The getJoker method returns a HBox with the joker card inside and sets the rotation
     * for the HBox
     *
     * @return hb HBox
     * @throws Exception if file not found
     */
    private HBox getJoker() throws Exception {
        HBox hb = new HBox();
        hb.getChildren().add(new ImageView(new Image(
                new FileInputStream("src/card/54.png")))); // add card 54 to HBox
        hb.setRotate(rotationValOne);  // set rotation value
        rotationValOne = rotationValOne + 45;  // increment rotational value
        // reset rotational value if needed
        if (rotationValOne > 135) {
            rotationValOne = 0;
        }
        return hb; // return HBox with rotation
    }

    /**
     * The getCard method returns a random card as an ImageView
     *
     * @param cards list
     * @return card ImageView
     * @throws Exception if file not found
     */
    private ImageView getCard(ArrayList<Integer> cards) throws Exception {
        usedCards++;  // increment number of cards used
        return new ImageView(new Image(new FileInputStream("src/card/" +
                cards.get(usedCards) + ".png")));
    }

    /**
     * The getRotatedCard method returns a HBox with the joker card inside and
     * sets the rotation of the HBox
     *
     * @param cards list
     * @return hb HBox
     * @throws Exception if file not found
     */
    private HBox getRotatedCard(ArrayList<Integer> cards) throws Exception {
        HBox hb = new HBox();
        usedCards++; // increment number of cards used
        hb.getChildren().add(new ImageView(new Image(new FileInputStream("src/card/" +
                cards.get(usedCards) + ".png"))));
        hb.setRotate(rotationValTwo);  // set rotation value
        rotationValTwo = rotationValTwo - 45;  // decrement rotational value
        // reset rotational value if needed
        if (rotationValTwo <= 0) {
            rotationValTwo = 135;
        }
        return hb;  // return HBox with rotation
    }

}
