package edu.hw3;

public class Task4 {

    private Task4() {
    }

    private static final int[] VALUES = {
        1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1
    };

    private static final String[] ROMAN_LITERALS = {
        "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
    };

    @SuppressWarnings("MagicNumber")
    public static String convertToRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("Number must be between 1 and 3999 for this task");
        }

        StringBuilder roman = new StringBuilder();
        int number = num;
        for (int i = 0; i < VALUES.length; i++) {
            while (number >= VALUES[i]) {
                number -= VALUES[i];
                roman.append(ROMAN_LITERALS[i]);
            }
        }

        return roman.toString();
    }
}
