package at.technikum.ode.memory;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Thomas on 15.01.16.
 */
public final class MemoryGame {
    final static Logger logger = Logger.getLogger(MemoryGame.class);
    private List<File> memoryCards = new ArrayList<>();

    MemoryGame(ImageFileProvider imageFileProvider, int maxNumberOfPairs) {
        createMemoryCards(imageFileProvider, maxNumberOfPairs);
    }

    public File getCard(int index) {
        return memoryCards.get(index);
    }

    public boolean isMatch(Guess guess) {
        if (guess.validGuessCount < 2 ) return false;
        return (memoryCards.get(guess.firstGuessIndex).equals(memoryCards.get(guess.secondGuessIndex)));
    }

    public int availableNumberOfCards() {
        return memoryCards.size();
    }

    private void createMemoryCards(final ImageFileProvider imageFileProvider, final int maxNumberOfPairs) {
        if (maxNumberOfPairs <= 0) return;

        int numberOfPairs = 0;
        for (File file: imageFileProvider.getImageFiles()) {
            // add card twice!!
            memoryCards.add(file);
            memoryCards.add(file);
            numberOfPairs++;
            if (numberOfPairs == maxNumberOfPairs) break;
        }

        Collections.shuffle(memoryCards);
    }
}
