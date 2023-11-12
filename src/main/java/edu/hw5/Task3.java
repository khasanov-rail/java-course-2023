package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Optional;

public class Task3 {

    public Optional<LocalDate> parseDate(String string) {
        LocalDate result = null;

        if (string == null) {
            throw new IllegalArgumentException("String cannot be null");
        }


        try {
            if (string.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                result = LocalDate.parse(string, formatter);
            } else if (string.matches("\\d{1,2}/\\d{1,2}/\\d{2,4}")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/[[uuuu][uu]]", Locale.ENGLISH);
                result = LocalDate.parse(string, formatter);
            } else if ("today".equals(string)) {
                result = LocalDate.now();
            } else if ("tomorrow".equals(string)) {
                result = LocalDate.now().plusDays(1);
            } else if ("yesterday".equals(string)) {
                result = LocalDate.now().minusDays(1);
            } else if (string.matches("\\d+ days? ago")) {
                int days = Integer.parseInt(string.split(" ")[0]);
                result = LocalDate.now().minusDays(days);
            }
        } catch (DateTimeParseException ignored) {
        }

        return Optional.ofNullable(result);
    }
}
