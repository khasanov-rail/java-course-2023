package edu.hw1;

public class Task2 {

    private Task2() {

    }

    @SuppressWarnings("MagicNumber")
    public static int countDigits(int number) {
        if (number == 0) {
            return 1;
        }

        int count = 0;
        int tempNumber = number;
        while (tempNumber != 0) {
            count++;
            tempNumber /= 10;
        }

        return count;
    }
}
