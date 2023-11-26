package edu.hw7;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class Task2 {

    private Task2() {
    }

    private static final int MAX_FACTORIAL = 20;

    public static BigInteger factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Факториал отрицательного числа не определен");
        }
        if (n > MAX_FACTORIAL) {
            throw new IllegalArgumentException("Факториал числа больше " + MAX_FACTORIAL + " не поддерживается");
        }

        return IntStream.rangeClosed(1, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}
