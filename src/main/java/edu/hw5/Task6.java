package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    private Task6() {
    }

    public static boolean isSubsequence(String subsequence, String sequence) {
        if (subsequence == null || sequence == null) {
            throw new IllegalArgumentException("Параметры функции не могут быть null!");
        }

        String regex = ".*" + subsequence.replace("", ".*") + ".*";
        return Pattern.compile(regex).matcher(sequence).find();
    }
}
