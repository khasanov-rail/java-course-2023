package edu.hw2.Task3;

import java.util.logging.Logger;

public final class StableConnection implements Connection {
    private static final Logger LOGGER = Logger.getLogger(StableConnection.class.getName());

    @Override
    public void execute(String command) {
        LOGGER.info("Executing command: " + command);
    }

    @Override
    public void close() {
        LOGGER.info("Closing stable connection");
    }
}
