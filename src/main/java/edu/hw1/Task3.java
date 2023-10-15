package edu.hw1;

import java.util.Arrays;

public class Task3 {

    private Task3() {

    }

    public static boolean isNestable(int[] a1, int[] a2) {
        if (a1 == null || a2 == null) {
            return false;
        }

        if (a1.length == 0 || a2.length == 0) {
            return false;
        }

        int minA1 = Arrays.stream(a1).min().getAsInt();
        int maxA1 = Arrays.stream(a1).max().getAsInt();
        int minA2 = Arrays.stream(a2).min().getAsInt();
        int maxA2 = Arrays.stream(a2).max().getAsInt();

        return minA1 > minA2 && maxA1 < maxA2;
    }

//    public static void main(String[] args) {
//        System.out.println(isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6}));  // true
//        System.out.println(isNestable(new int[]{3, 1}, new int[]{4, 0}));  // true
//        System.out.println(isNestable(new int[]{9, 9, 8}, new int[]{8, 9}));  // false
//        System.out.println(isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3}));  // false
//    }
}
