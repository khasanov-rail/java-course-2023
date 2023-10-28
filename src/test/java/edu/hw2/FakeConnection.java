package edu.hw2.Task3;

public class FakeConnection implements Connection {
    int executeCount = 0;
    int closeCount = 0;
    boolean throwException;

    public FakeConnection(boolean throwException) {
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
