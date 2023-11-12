package edu.hw5;

public class Task8 {

    private Task8() {
    }

    // Метод для проверки строки на нечетную длину
    public static boolean isOddLength(String str) {
        return str != null && !str.isEmpty() && str.matches("([01]{2})*[01]");
    }

    // Метод для проверки строки, начинающейся с 0 и имеющей нечетную длину, или начинающейся с 1 и имеющей четную длину
    public static boolean startsWith0OddLengthOr1EvenLength(String str) {
        return str != null && !str.isEmpty() && (str.startsWith("0") && isOddLength(str)
            || str.startsWith("1") && !isOddLength(str));
    }

    // Метод для проверки, что количество 0 кратно 3
    public static boolean zeroCountMultipleOfThree(String str) {
        return str != null && !str.isEmpty() && str.matches("(0{3})*[01]*");
    }

    // Метод для проверки, что строка не является "11" или "111"
    public static boolean not11Or111(String str) {
        return str != null && !str.isEmpty() && !str.equals("11") && !str.equals("111");
    }

    // Метод для проверки, что каждый нечетный символ равен 1
    public static boolean everyOddCharIs1(String str) {
        return str != null && !str.isEmpty() && str.matches("(1[01])*(1|)");
    }

    // Метод для проверки, что в строке не менее двух 0 и не более одной 1
    public static boolean atLeastTwoZerosAtMostOneOne(String str) {
        return str != null && !str.isEmpty() && str.matches("[01]*0[01]*0[01]*(1|)");
    }

    // Метод для проверки, что в строке нет последовательных 1
    public static boolean noConsecutiveOnes(String str) {
        return str != null && !str.isEmpty() && !str.contains("11");
    }
}
