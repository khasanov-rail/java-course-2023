package edu.hw1;

public class Task2 {

    private static final int DECIMAL_BASE = 10;

    private Task2() {

    }

    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int count = 0;
        int tempNumber = number;
        while (tempNumber != 0) {
            count++;
            tempNumber /= DECIMAL_BASE;
        }

        return count;
    }

//    public static void main(String[] args) {
//        System.out.println(countDigits(4666)); // 4
//        System.out.println(countDigits(544));  // 3
//        System.out.println(countDigits(0));    // 1
//    }
}
