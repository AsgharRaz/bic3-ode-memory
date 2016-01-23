package at.technikum.ode.memory;

/**
 * Created by Thomas on 16.01.16.
 */

/**
 * The Guess class represent two cards which are compared to find a match.
 */
public final class Guess {
    int firstGuessIndex = Integer.MIN_VALUE;
    int secondGuessIndex = Integer.MIN_VALUE;
    int validGuessCount = 0;
    boolean screwed = false;

    /**
     * Add the index of the selected card to the guess.
     * A card index can be added two times, every following call will screw up the guess
     * and set isScrewed() to "true".
     *
     * @param index Index of the card
     * @return true if the index is valid, false otherwise
     */
    public boolean addGuess(int index) {
        if (index < 0) return false;

        // can not compare with itself
        if (index == firstGuessIndex) {
            return false;
        }

        switch (validGuessCount) {
            case 0: {
                firstGuessIndex = index;
                validGuessCount++;
                return true;
            }
            case 1: {
                secondGuessIndex = index;
                validGuessCount++;
                return true;
            }
        }
        screwed = true;
        return false;
    }

    /**
     *
     * @return the value of the first card index
     */
    public int getFirstGuessIndex() {
        return firstGuessIndex;
    }

    /**
     * @return the value of the second card index
     */
    public int getSecondGuessIndex() {
        return secondGuessIndex;
    }

    /**
     *
     * @return true if the guess contains two non identical card indexes and there was no attempt to add a subsequent index
     */
    public boolean validGuesses() {
        return validGuessCount == 2;
    }

    /**
     *
     * @return true if there was an attempt to add mire than two index values
     */
    public boolean isScrewed() {
        return screwed;
    }
}
