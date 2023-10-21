package edu.hw1;

public class Task1 {

    private Task1() {
    }

    @SuppressWarnings("MagicNumber")
    public static int minutesToSeconds(String videoLength) {
        int result = -1;

        String[] parts = videoLength.split(":");
        if (parts.length == 2) {
            try {
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);

                if (seconds < 60 && seconds >= 0 && minutes >= 0 && minutes <= (Integer.MAX_VALUE / 60)) {
                    result = minutes * 60 + seconds;
                }

            } catch (NumberFormatException e) {
            }
        }

        return result;
    }
}
