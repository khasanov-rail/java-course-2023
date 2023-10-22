package edu.hw2.Task3;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {

    static class FakeConnectionManager implements ConnectionManager {
        Connection connection;

        FakeConnectionManager(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Connection getConnection() {
            return connection;
        }
    }

    static class FakeConnection implements Connection {
        int executeCount = 0;
        int closeCount = 0;
        boolean throwException;

        FakeConnection(boolean throwException) {
            this.throwException = throwException;
        }

        @Override
        public void execute(String command) {
            executeCount++;
            if (throwException) {
                throw new ConnectionException("Connection failed");
            }
        }

        @Override
        public void close() {
            closeCount++;
        }
    }

    static class FakeLogger extends Logger {
        List<String> logs = new ArrayList<>();

        protected FakeLogger() {
            super("FakeLogger", null);
        }

        @Override
        public void severe(String msg) {
            logs.add(msg);
        }
    }

    @Test
    void testUpdatePackagesSuccessfulExecution() {
        // Arrange
        Connection mockConnection = new FakeConnection(false);
        ConnectionManager mockManager = new FakeConnectionManager(mockConnection);
        FakeLogger fakeLogger = new FakeLogger();
        PopularCommandExecutor executor = new PopularCommandExecutor(mockManager, 3, fakeLogger);

        // Act
        executor.updatePackages();

        // Assert
        assertEquals(1, ((FakeConnection) mockConnection).executeCount, "Command should be executed once");
        assertEquals(1, ((FakeConnection) mockConnection).closeCount, "Connection should be closed once");
        assertEquals(0, fakeLogger.logs.size(), "No logs should be written");
    }

    @Test
    void testUpdatePackagesThrowsExceptionAfterMaxAttempts() {
        // Arrange
        Connection mockConnection = new FakeConnection(true);
        ConnectionManager mockManager = new FakeConnectionManager(mockConnection);
        FakeLogger fakeLogger = new FakeLogger();
        PopularCommandExecutor executor = new PopularCommandExecutor(mockManager, 3, fakeLogger);

        // Act
        ConnectionException thrownException = assertThrows(ConnectionException.class, executor::updatePackages);

        // Assert
        assertEquals(
            "Failed to execute command after 3 attempts",
            thrownException.getMessage(),
            "Exception message should match"
        );
        assertEquals(3, ((FakeConnection) mockConnection).executeCount, "Command should be executed three times");
        assertEquals(3, ((FakeConnection) mockConnection).closeCount, "Connection should be closed three times");
        assertEquals(
            "Attempt 1 failed: Connection failed",
            fakeLogger.logs.get(0),
            "First error log message should match"
        );
        assertEquals(
            "Attempt 2 failed: Connection failed",
            fakeLogger.logs.get(1),
            "Second error log message should match"
        );
        assertEquals(3, fakeLogger.logs.size(), "Three error logs should be written");
    }

}
