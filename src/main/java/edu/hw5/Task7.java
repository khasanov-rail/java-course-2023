package edu.hw5;

public class Task7 {

    private static final String ERROR_MESSAGE = "String cannot be null or empty";

    private Task7() {
    }

    public static boolean matchesPattern1(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return str.matches("[01]{2}0[01]*");
    }

    public static boolean matchesPattern2(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return str.matches("([01])\\1*");
    }

    public static boolean matchesPattern3(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
        return str.matches("[01]{1,3}");
    }
}
