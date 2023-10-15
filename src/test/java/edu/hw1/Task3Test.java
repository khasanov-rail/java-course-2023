package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Task3Test {

    @Test
    void testIsNestableForCommonCases() {
        assertTrue(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{0, 6}));
        assertTrue(Task3.isNestable(new int[]{3, 1}, new int[]{4, 0}));
        assertFalse(Task3.isNestable(new int[]{9, 9, 8}, new int[]{8, 9}));
        assertFalse(Task3.isNestable(new int[]{1, 2, 3, 4}, new int[]{2, 3}));
    }

    @Test
    void testIsNestableForBoundaryCases() {
        assertFalse(Task3.isNestable(new int[]{Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE}));
    }

    @Test
    void testIsNestableForEmptyArrays() {
        assertFalse(Task3.isNestable(new int[]{}, new int[]{1, 2, 3}));
        assertFalse(Task3.isNestable(new int[]{1, 2, 3}, new int[]{}));
        assertFalse(Task3.isNestable(new int[]{}, new int[]{}));
    }

    @Test
    void testIsNestableForNullArrays() {
        assertFalse(Task3.isNestable(null, new int[]{1, 2, 3}));
        assertFalse(Task3.isNestable(new int[]{1, 2, 3}, null));
        assertFalse(Task3.isNestable(null, null));
    }
}
