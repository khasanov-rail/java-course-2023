package edu.hw5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Task6Test {
    @ParameterizedTest
    @CsvSource({
        "abc, achfdbaabgabcaabg",
        "abc, ssassbsscs",
        "a, sssddda",
        "abc,abc"
    })
    void isSubsequence_true(String subsequence, String sequence) {
        // Arrange : Параметры предоставлены через CsvSource

        // Act
        boolean result = Task6.isSubsequence(subsequence, sequence);

        // Assert
        Assertions.assertTrue(result, "Строка должна быть подпоследовательностью");
    }

    @ParameterizedTest
    @CsvSource({
        "abcz, achfdbaabgabcaabg",
        "abc, ssassbss",
        "a, sssddd",
        "abc,sdf"
    })
    void isSubsequence_false(String subsequence, String sequence) {
        // Arrange : Параметры предоставлены через CsvSource

        // Act
        boolean result = Task6.isSubsequence(subsequence, sequence);

        // Assert
        Assertions.assertFalse(result, "Строка не должна быть подпоследовательностью");
    }

    @Test
    @DisplayName("Null")
    void isSubsequence_whenNull() {
        // Arrange, Act & Assert
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> Task6.isSubsequence(null, "abc"));
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> Task6.isSubsequence("abc", null));
        IllegalArgumentException thrown3 = Assertions.assertThrows(IllegalArgumentException.class, () -> Task6.isSubsequence(null, null));

        // Assert
        String expectedMessage = "Параметры функции не могут быть null!";
        Assertions.assertAll(
            "isSubsequence_whenNull",
            () -> Assertions.assertEquals(expectedMessage, thrown1.getMessage()),
            () -> Assertions.assertEquals(expectedMessage, thrown2.getMessage()),
            () -> Assertions.assertEquals(expectedMessage, thrown3.getMessage())
        );
    }
}
