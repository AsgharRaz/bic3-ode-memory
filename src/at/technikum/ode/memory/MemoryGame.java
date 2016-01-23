package at.technikum.ode.memory;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thomas on 15.01.16.
 */

/**
 *
 * use MemoryGameBuilder to create an instance of Memory Game
 */
public final class MemoryGame {
    final static Logger logger = Logger.getLogger(MemoryGame.class);
    private List<File> memoryCards = new ArrayList<>();

    /* package local constructor */
    MemoryGame(ImageFileProvider imageFileProvider, int maxNumberOfPairs) {
        createMemoryCards(imageFileProvider, maxNumberOfPairs);
    }


    /**
     *
     * @param index index of the card
     * @return a File which represents the image of the memory card
     */
    public File getCard(int index) {
        return memoryCards.get(index);
    }


    /**
     *
     * @param guess a guess object which contains two indexes
     * @return true if the guess leads to a matching pair, false otherwise
     */
    public boolean isMatch(final Guess guess) {
        if (guess.validGuessCount < 2 ) {
            logger.debug("too little guesses");
            return false;
        }
        return (memoryCards.get(guess.firstGuessIndex).equals(memoryCards.get(guess.secondGuessIndex)));
    }

    /**
     *
     * @return the number of cards available
     */
    public int availableNumberOfCards() {
        return memoryCards.size();
    }

    private void createMemoryCards(final ImageFileProvider imageFileProvider, final int maxNumberOfPairs) {
        if (maxNumberOfPairs <= 0) {
            logger.warn("invalid number of pairs: " + maxNumberOfPairs);
            return;
        }

        int numberOfPairs = 0;
        for (File file: imageFileProvider.getImageFiles()) {
            // add card twice!!
            memoryCards.add(file);
            memoryCards.add(file);
            numberOfPairs++;
            logger.debug("added file " + file.getAbsolutePath());

            if (numberOfPairs == maxNumberOfPairs) break;
        }

        Collections.shuffle(memoryCards);
        logger.debug("created memory card pairs: " + numberOfPairs);
    }
}
