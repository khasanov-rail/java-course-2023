package edu.hw1;

public class Task4 {

    private Task4() {

    }

    public static String fixString(String str) {

        if (str == null) {
            return null;
        }

        StringBuilder fixed = new StringBuilder();

        for (int i = 0; i < str.length() - 1; i += 2) {
            fixed.append(str.charAt(i + 1));
            fixed.append(str.charAt(i));
        }

        if (str.length() % 2 != 0) {
            fixed.append(str.charAt(str.length() - 1));
        }

        return fixed.toString();
    }

}
