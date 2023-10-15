package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task4Test {

    @Test
    void testFixStringForCommonCases() {
        assertEquals("214365", Task4.fixString("123456"));
        assertEquals("This is a mixed up string.", Task4.fixString("hTsii  s aimex dpus rtni.g"));
        assertEquals("abcde", Task4.fixString("badce"));
    }

    @Test
    void testFixStringForEdgeCases() {

        assertEquals("", Task4.fixString(""));
        assertEquals(" ", Task4.fixString(" "));
        assertEquals(null, Task4.fixString(null));

        assertEquals("a", Task4.fixString("a"));
        assertEquals("ba", Task4.fixString("ab"));
    }

    @Test
    void testFixStringForSpecialCharacters() {

        assertEquals("@!$#^%*&)(-_+=<?>", Task4.fixString("!@#$%^&*()_-=+?<>"));
    }

    @Test
    void testFixStringForLongStrings() {
        // Тестирование длинной строки
        String longStr = "a" + "b".repeat(9999);
        String expectedStr = "ba" + "b".repeat(9998);
        assertEquals(expectedStr, Task4.fixString(longStr));
    }

}
