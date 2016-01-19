package at.technikum.ode.memory;

/**
 * Created by Thomas on 15.01.16.
 */

import org.apache.log4j.Logger;

/**
 * Builder class which allows to specify an optional parameter to limit the number of pairs
 * used by MemoryGame in a fluent way.
 */
public final class MemoryGameBuilder {
    final static Logger logger = Logger.getLogger(MemoryGameBuilder.class);
    /* default values for at.technikum.ode.memory game */
    private final ImageFileProvider imageFileProvider;
    private int maxNumberOfPairs = Integer.MAX_VALUE;

    public MemoryGameBuilder(ImageFileProvider imageFileProvider) {
        this.imageFileProvider = imageFileProvider;
        logger.debug("created imageFileProvider for path " + imageFileProvider.getImageRootPath());
    }

    /**
     *
     * @param maxNumberOfPairs upper bound for the number of pairs used by at.technikum.ode.memory game. The effective number of pairs used
     *                         might be lower and depends on the number of available images files
     * @return the instance of the MemoryGameBuilder
     */
    public MemoryGameBuilder maxNumberOfPairs(int maxNumberOfPairs) {
        this.maxNumberOfPairs = maxNumberOfPairs;
        logger.debug("max. number of pairs is now: " + this.maxNumberOfPairs);
        return this;
    }

    /**
     *
     * @return The MemoryGame built with the specified values (default or set via fluent api)
     */
    public MemoryGame buildMemoryGame() {
        logger.debug(String.format("creating new MemoryGame with path %s limited to %d pairs"
                , this.imageFileProvider.getImageRootPath()
                , this.maxNumberOfPairs));
        return new MemoryGame(this.imageFileProvider, this.maxNumberOfPairs);
    }
}
