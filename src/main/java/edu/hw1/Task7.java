package edu.hw1;

public class Task7 {

    public static final int MSB = 1 << (Integer.SIZE - 1);

    private Task7() {

    }

    private static int countBinaryDigits(int n) {
        int locNum = n;
        int count = 0;
        while (locNum != 0) {
            locNum >>= 1;
            count++;
        }
        return count;
    }

    private static int rotate(int n, int shift, boolean isLeft) {
        final int binaryDigitsAmount = countBinaryDigits(n);
        final int unperiodicShift = shift % binaryDigitsAmount;

        int firstPart;
        int secondPart;

        if (isLeft) {
            firstPart = n << unperiodicShift;
            secondPart = n >> (binaryDigitsAmount - unperiodicShift);
        } else {
            firstPart = n >> unperiodicShift;
            secondPart = n << (binaryDigitsAmount - unperiodicShift);
        }

        return (firstPart | secondPart) & ((1 << binaryDigitsAmount) - 1);
    }

    public static int rotateRight(int n, int shift) {
        if (n == MSB) {
            return MSB;
        }
        return rotate(n, shift, false);
    }

    public static int rotateLeft(int n, int shift) {
        if (n == MSB) {
            return MSB;
        }
        return rotate(n, shift, true);
    }

//    public static void main(String[] args) {
//        System.out.println(rotateRight(8, 1));   // 4
//        System.out.println(rotateLeft(16, 1));  // 1
//        System.out.println(rotateLeft(17, 2));  // 6
//    }
}
