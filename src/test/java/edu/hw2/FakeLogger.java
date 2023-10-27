package edu.hw2.Task3;

import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class FakeLogger extends Logger {
    List<String> logs = new ArrayList<>();

    protected FakeLogger() {
        super("FakeLogger", null);
    }

    @Override
    public void severe(String msg) {
        logs.add(msg);
    }
}
