package edu.hw2.Task3;

import java.util.Random;
import java.util.logging.Logger;

public final class FaultyConnection implements Connection {
    private static final Logger LOGGER = Logger.getLogger(FaultyConnection.class.getName());
    private static final Random RANDOM = new Random();
    private static final String EXECUTION_FAILED_MSG = "Failed to execute command: ";

    @Override
    public void execute(String command) throws ConnectionException {
        if (RANDOM.nextBoolean()) {
            String errorMessage = EXECUTION_FAILED_MSG + command;
            LOGGER.severe(errorMessage);
            throw new ConnectionException(errorMessage);
        }
        LOGGER.info("Executing command: " + command);
    }

    @Override
    public void close() {
        LOGGER.info("Closing faulty connection");
    }
}
