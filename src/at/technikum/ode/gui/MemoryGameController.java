package at.technikum.ode.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import at.technikum.ode.memory.Guess;
import at.technikum.ode.memory.ImageFileProvider;
import at.technikum.ode.memory.MemoryGame;
import at.technikum.ode.memory.MemoryGameBuilder;
import org.apache.log4j.Logger;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * The controller class which handles all user interaction
 */
public final class MemoryGameController implements Initializable{
    final static Logger logger = Logger.getLogger(MemoryGameController.class);
    private static final int CARDS_PER_ROW = 4;
    private final Image backSide = new Image("/images/helloKitty.png");

    private final EventHandler imageViewClickEventHandler = clickEventHandler();

    ImageFileProvider fileProvider = new ImageFileProvider("/Users/Thomas/Pictures/Katze");
    MemoryGame memoryGame;
    Guess guess;

    @FXML
    Slider gameLevelSlider;

    @FXML
    GridPane memoryCardGrid;

    @FXML
    Button restartGameButton;

    @Override
    /**
     * Initializes the MemoryGame controller and attaches all event handlers,
     * sets appropriate labels to the game level slider.
     */
    public void initialize(URL location, ResourceBundle resources) {
        createNewGame();
        restartGameButton.setOnMouseClicked(event -> {
            createNewGame();
        });
        gameLevelSlider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                if (value == 4) return "einfach";
                else if (value == 6) return "mittel";
                else return "schwer";
            }

            @Override
            public Double fromString(String string) {
                return null;
            }
        });
    }

    private void createNewGame() {

        /* detach mouse-click event handler */
        for (Node childNode : memoryCardGrid.getChildren()) {
            childNode.removeEventHandler(MouseEvent.MOUSE_CLICKED, imageViewClickEventHandler);
        }

        /* and remove alle image views */
        memoryCardGrid.getChildren().clear();

        memoryGame = new MemoryGameBuilder(fileProvider).maxNumberOfPairs((int)gameLevelSlider.getValue()).buildMemoryGame();

        /* since the number of images depends on the selected game level we remove all imageViews first */
        /* there is room for optimization :-) */
        createImageViews(memoryGame.availableNumberOfCards());
        logger.debug("created new at.technikum.ode.memory game with number of cards: " + memoryGame.availableNumberOfCards());
        guess = new Guess();
    }


    private void createImageViews(int size) {
        int rowIndex = 0;
        int colIndex = 0;

        for (int cardIndex = 0; cardIndex < size; cardIndex++) {
            ImageView imageView = new ImageView(backSide);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setEffect(new DropShadow(5, Color.BLACK));

            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, imageViewClickEventHandler);

            memoryCardGrid.add(imageView, rowIndex, colIndex);
            rowIndex++;
            if (rowIndex % CARDS_PER_ROW == 0) {
                colIndex++;
                rowIndex = 0;
            }
        }
    }

    private EventHandler clickEventHandler() {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!(event.getSource() instanceof ImageView)) return;

                ImageView clickedImageView = (ImageView)event.getSource();

                int col = GridPane.getColumnIndex(clickedImageView);
                int row = GridPane.getRowIndex(clickedImageView);

                int selectedCardIndex = row * CARDS_PER_ROW + col;
                logger.debug("selected at.technikum.ode.memory card index: " + selectedCardIndex);

                if (guess.addGuess(selectedCardIndex)) {
                    clickedImageView.setImage(new Image(memoryGame.getCard(selectedCardIndex).toURI().toString()));
                }

                if (guess.validGuesses()) {
                    if (memoryGame.isMatch(guess)) {
                        logger.info("found a matching pair!");
                        memoryCardGrid.getChildren().get(guess.getFirstGuessIndex()).removeEventHandler(MouseEvent.MOUSE_CLICKED, imageViewClickEventHandler);
                        memoryCardGrid.getChildren().get(guess.getSecondGuessIndex()).removeEventHandler(MouseEvent.MOUSE_CLICKED, imageViewClickEventHandler);
                        guess = new Guess();
                    }
                    if (guess.isScrewed()) {
                        logger.info("no matching pair");
                        ((ImageView)memoryCardGrid.getChildren().get(guess.getFirstGuessIndex())).setImage(backSide);
                        ((ImageView)memoryCardGrid.getChildren().get(guess.getSecondGuessIndex())).setImage(backSide);
                        guess = new Guess();
                    }
                }
            }
        };
    }


}
