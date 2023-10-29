package edu.hw3;

public class Task1 {

    private Task1() {
    }

    public static String atbash(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            char atbashChar = c;
            if (c >= 'a' && c <= 'z') {
                atbashChar = (char) ('a' + 'z' - c);
            } else if (c >= 'A' && c <= 'Z') {
                atbashChar = (char) ('A' + 'Z' - c);
            }
            result.append(atbashChar);
        }
        return result.toString();
    }
}
