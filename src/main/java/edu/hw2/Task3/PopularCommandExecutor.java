package edu.hw2.Task3;

import java.util.logging.Logger;

public final class PopularCommandExecutor {
    private final Logger logger;
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts, Logger logger) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
        this.logger = logger;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (ConnectionException e) {
                logger.severe("Attempt " + (i + 1) + " failed: " + e.getMessage());
                if (i == maxAttempts - 1) {
                    throw new ConnectionException("Failed to execute command after " + maxAttempts + " attempts", e);
                }
            }
        }
    }
}
