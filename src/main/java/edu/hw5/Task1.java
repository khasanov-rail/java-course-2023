package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Task1 {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm", Locale.ENGLISH);

    public long calculateAverageSessionTime(String[] sessions) {
        if (sessions == null) {
            throw new IllegalArgumentException("Sessions cannot be null");
        }
        if (sessions.length == 0) {
            return 0;
        }

        long totalMinutes = 0;
        int count = 0;

        for (String session : sessions) {
            String[] times = session.split(" - ");
            LocalDateTime start = LocalDateTime.parse(times[0], formatter);
            LocalDateTime end = LocalDateTime.parse(times[1], formatter);

            Duration duration = Duration.between(start, end);
            totalMinutes += duration.toMinutes();
            count++;
        }

        return totalMinutes / count;
    }
}
