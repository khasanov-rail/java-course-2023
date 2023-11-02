package edu.hw3;

public class Task1 {

    private Task1() {
    }

    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            char atbashChar = convertChar(c);
            result.append(atbashChar);
        }
        return result.toString();
    }

    private static char convertChar(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) ('a' + 'z' - c);
        } else if (c >= 'A' && c <= 'Z') {
            return (char) ('A' + 'Z' - c);
        }
        return c;
    }
}
