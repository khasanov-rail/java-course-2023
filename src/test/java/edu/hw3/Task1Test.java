package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    public void testAtbashWithLowerCase() {
        // Arrange
        String input = "abcdefghijklmnopqrstuvwxyz";
        String expected = "zyxwvutsrqponmlkjihgfedcba";

        // Act
        String result = Task1.atbash(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAtbashWithUpperCase() {
        // Arrange
        String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expected = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

        // Act
        String result = Task1.atbash(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAtbashWithMixedCase() {
        // Arrange
        String input = "AbcDefGhi";
        String expected = "ZyxWvuTsr";

        // Act
        String result = Task1.atbash(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAtbashWithSpecialCharacters() {
        // Arrange
        String input = "Hello, World!";
        String expected = "Svool, Dliow!";

        // Act
        String result = Task1.atbash(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAtbashWithEmptyString() {
        // Arrange
        String input = "";
        String expected = "";

        // Act
        String result = Task1.atbash(input);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testAtbashWithExampleText() {
        // Arrange
        String input = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String expected = "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";

        // Act
        String result = Task1.atbash(input);

        // Assert
        assertEquals(expected, result);
    }
}
