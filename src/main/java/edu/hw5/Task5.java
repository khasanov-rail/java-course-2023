package edu.hw5;

public class Task5 {

    private Task5() {
    }

    public static boolean isValidRussianLicensePlate(String plate) {
        if (plate == null) {
            throw new IllegalArgumentException("License plate can not be null");
        }

        return plate.matches("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");
    }
}
