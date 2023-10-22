package edu.hw2.Task3;

import java.util.Random;

public final class DefaultConnectionManager implements ConnectionManager {
    private static final Random RANDOM = new Random();

    @Override
    public Connection getConnection() {
        return RANDOM.nextBoolean() ? new FaultyConnection() : new StableConnection();
    }
}
