package edu.hw2.Task3;

public class FakeConnectionManager implements ConnectionManager {
    Connection connection;

    public FakeConnectionManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
