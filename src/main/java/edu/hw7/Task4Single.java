package edu.hw7;

import java.util.concurrent.ThreadLocalRandom;

public class Task4Single {

    private static final double MULTIPLIER_FOR_PI = 4.0;

    private Task4Single() {
    }

    public static double calculatePi(int numberOfSimulations) {
        int circleCount = 0;

        for (int i = 0; i < numberOfSimulations; i++) {
            double x = ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = ThreadLocalRandom.current().nextDouble(-1, 1);

            if (x * x + y * y <= 1) {
                circleCount++;
            }
        }

        return MULTIPLIER_FOR_PI * circleCount / numberOfSimulations;
    }
}
