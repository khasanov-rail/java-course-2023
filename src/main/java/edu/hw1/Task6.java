package edu.hw1;

import java.util.Arrays;

public class Task6 {

    private static final int MIN_VALUE = 1000;
    private static final int MAX_VALUE = 9999;
    private static final int SPECIAL_VALUE = 6174;
    private static final String FORMAT_STRING = "%04d";

    private Task6() {
        // This utility class is not publicly instantiable.
    }

    public static int countK(int n) {
        if (n < MIN_VALUE || n > MAX_VALUE || allDigitsSame(n) || n == MIN_VALUE) {
            return -1;
        }
        if (n == SPECIAL_VALUE) {
            return 0;
        }
        return 1 + countK(getKValue(n));
    }

    private static boolean allDigitsSame(int n) {
        char[] chars = Integer.toString(n).toCharArray();
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] != chars[0]) {
                return false;
            }
        }
        return true;
    }

    private static int getKValue(int n) {
        int ascending = sortAscending(n);
        int descending = sortDescending(n);
        return descending - ascending;
    }

    private static int sortAscending(int n) {
        char[] chars = String.format(FORMAT_STRING, n).toCharArray();
        Arrays.sort(chars);
        return Integer.parseInt(new String(chars));
    }

    private static int sortDescending(int n) {
        char[] chars = String.format(FORMAT_STRING, n).toCharArray();
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder(new String(chars));
        return Integer.parseInt(sb.reverse().toString());
    }

//    public static void main(String[] args) {
//        System.out.println(countK(3524));  // 3
//        System.out.println(countK(6621));  // 5
//        System.out.println(countK(6554));  // 4
//        System.out.println(countK(1234));  // 3
//        System.out.println(countK(999));   // -1
//        System.out.println(countK(10000)); // -1
//        System.out.println(countK(-1234)); // -1
//        System.out.println(countK(1000));  // -1
//        System.out.println(countK(1002));  // 3
//        System.out.println(countK(4444));  // -1
//        System.out.println(countK(6666));  // -1
//    }
}





