package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    @SuppressWarnings("MagicNumber")
    public static List<LocalDate> findFridays13(int year) {
        List<LocalDate> fridays13 = new ArrayList<>();
        LocalDate date = LocalDate.of(year, 1, 13);

        for (int month = 1; month <= 12; month++) {
            LocalDate thirteenth = date.withMonth(month);
            if (thirteenth.getDayOfWeek() == DayOfWeek.FRIDAY) {
                fridays13.add(thirteenth);
            }
        }

        return fridays13;
    }

    @SuppressWarnings("MagicNumber")
    public static LocalDate findNextFriday13(LocalDate from) {
        LocalDate nextFriday13 = from.with(TemporalAdjusters.firstDayOfNextMonth());
        while (true) {
            nextFriday13 = nextFriday13.withDayOfMonth(13);
            if (nextFriday13.getDayOfWeek() == DayOfWeek.FRIDAY) {
                break;
            }
            nextFriday13 = nextFriday13.with(TemporalAdjusters.firstDayOfNextMonth());
        }

        return nextFriday13;
    }
}
