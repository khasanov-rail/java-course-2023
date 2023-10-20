package edu.hw1;

public class Task5 {

    private Task5() {

    }

    public static boolean isPalindromeDescendant(int num) {
        String s = String.valueOf(num);

        while (s.length() > 1) {
            if (isPalindrome(s)) {
                return true;
            }
            s = getDescendant(s);
        }

        return false;
    }

    private static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    private static String getDescendant(String s) {
        StringBuilder descendant = new StringBuilder();

        for (int i = 0; i < s.length() - 1; i += 2) {
            int sum = Character.getNumericValue(s.charAt(i)) + Character.getNumericValue(s.charAt(i + 1));
            descendant.append(sum);
        }

        if (s.length() % 2 != 0) {  // Если длина строки нечетная, добавляем последний символ к потомку
            descendant.append(s.charAt(s.length() - 1));
        }

        return descendant.toString();
    }

}
