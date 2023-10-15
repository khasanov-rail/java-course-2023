package edu.hw1;

public class Task1 {

    private static final int SECONDS_PER_MINUTE = 60;

    private Task1() {
        // This utility class is not publicly instantiable.
    }

    public static int minutesToSeconds(String videoLength) {

        String[] parts = videoLength.split(":");
        if (parts.length != 2) {
            return -1;
        }

        try {
            int minutes = Integer.parseInt(parts[0]);
            int seconds = Integer.parseInt(parts[1]);

            if (seconds >= SECONDS_PER_MINUTE || seconds < 0 || minutes < 0) {
                return -1;
            }

            return minutes * SECONDS_PER_MINUTE + seconds;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(minutesToSeconds("01:00")); // 60
//        System.out.println(minutesToSeconds("13:56")); // 836
//        System.out.println(minutesToSeconds("10:60")); // -1
//    }
}
