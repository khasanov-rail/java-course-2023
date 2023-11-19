package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

public class Task3 {

    public Optional<LocalDate> parseDate(String dateString) {
        LocalDate parsedDate = null;

        if (dateString == null) {
            throw new IllegalArgumentException("Date string cannot be null");
        }

        try {
            if (dateString.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                parsedDate = LocalDate.parse(dateString, formatter);
            } else if (dateString.matches("\\d{1,2}/\\d{1,2}/\\d{2,4}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/[[uuuu][uu]]", Locale.ENGLISH);
                parsedDate = LocalDate.parse(dateString, formatter);
            } else if ("today".equals(dateString)) {
                parsedDate = LocalDate.now();
            } else if ("tomorrow".equals(dateString)) {
                parsedDate = LocalDate.now().plusDays(1);
            } else if ("yesterday".equals(dateString)) {
                parsedDate = LocalDate.now().minusDays(1);
            } else if (dateString.matches("\\d+ days? ago")) {
                int days = Integer.parseInt(dateString.split(" ")[0]);
                parsedDate = LocalDate.now().minusDays(days);
            }
        } catch (DateTimeParseException ignored) {
        }

        return Optional.ofNullable(parsedDate);
    }
}
