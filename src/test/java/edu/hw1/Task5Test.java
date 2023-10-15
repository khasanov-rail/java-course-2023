package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task5Test {

    @Test
    void testIsPalindromeDescendantTrueCases() {
        assertTrue(Task5.isPalindromeDescendant(11211230));  // 11211230 -> 2333 -> 56 -> 11
        assertTrue(Task5.isPalindromeDescendant(13001120));  // 13001120 -> 4022 ➞ 44
        assertTrue(Task5.isPalindromeDescendant(23336014));  // 23336014 -> 5665
        assertTrue(Task5.isPalindromeDescendant(11));        // 11
        assertTrue(Task5.isPalindromeDescendant(93287));     // 93287 -> 12107 -> 317 -> 47 -> 11
        assertTrue(Task5.isPalindromeDescendant(123));       // 123 -> 33
        assertTrue(Task5.isPalindromeDescendant(1377));
    }

    @Test
    void testIsPalindromeDescendantFalseCases() {
        assertFalse(Task5.isPalindromeDescendant(12345678));
        assertFalse(Task5.isPalindromeDescendant(9));
        assertFalse(Task5.isPalindromeDescendant(45));
        assertFalse(Task5.isPalindromeDescendant(1233211));
        assertFalse(Task5.isPalindromeDescendant(-11));
        assertFalse(Task5.isPalindromeDescendant(-123));
        assertFalse(Task5.isPalindromeDescendant(-17));
        assertFalse(Task5.isPalindromeDescendant(17));
    }

}

