package edu.hw2.Task3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task3Test {

    @Test void testUpdatePackagesSuccessfulExecution() {
        // Arrange
        Connection mockConnection = new edu.hw2.Task3.FakeConnection(false);
        ConnectionManager mockManager = new edu.hw2.Task3.FakeConnectionManager(mockConnection);
        edu.hw2.Task3.FakeLogger fakeLogger = new edu.hw2.Task3.FakeLogger();
        PopularCommandExecutor executor = new PopularCommandExecutor(mockManager, 3, fakeLogger);

        // Act
        executor.updatePackages();

        // Assert
        assertEquals(1,
            ((edu.hw2.Task3.FakeConnection) mockConnection).executeCount,
            "Command should be executed once"
        );
        assertEquals(1, ((edu.hw2.Task3.FakeConnection) mockConnection).closeCount, "Connection should be closed once");
        assertEquals(0, fakeLogger.logs.size(), "No logs should be written");
    }

    @Test void testUpdatePackagesThrowsExceptionAfterMaxAttempts() {
        // Arrange
        Connection mockConnection = new edu.hw2.Task3.FakeConnection(true);
        ConnectionManager mockManager = new edu.hw2.Task3.FakeConnectionManager(mockConnection);
        edu.hw2.Task3.FakeLogger fakeLogger = new edu.hw2.Task3.FakeLogger();
        PopularCommandExecutor executor = new PopularCommandExecutor(mockManager, 3, fakeLogger);

        // Act
        ConnectionException thrownException = assertThrows(ConnectionException.class, executor::updatePackages);

        // Assert
        assertEquals("Failed to execute command after 3 attempts",
            thrownException.getMessage(),
            "Exception message should match"
        );
        assertEquals(3,
            ((edu.hw2.Task3.FakeConnection) mockConnection).executeCount,
            "Command should be executed three times"
        );
        assertEquals(3,
            ((edu.hw2.Task3.FakeConnection) mockConnection).closeCount,
            "Connection should be closed three times"
        );
        assertEquals("Attempt 1 failed: Connection failed",
            fakeLogger.logs.get(0),
            "First error log message should match"
        );
        assertEquals("Attempt 2 failed: Connection failed",
            fakeLogger.logs.get(1),
            "Second error log message should match"
        );
        assertEquals(3, fakeLogger.logs.size(), "Three error logs should be written");
    }

}
