package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {

    @Test
    public void testParseContactsAscendingOrder() {
        // Arrange
        String[] names = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        List<Task5.Contact> expected = List.of(
            new Task5.Contact("Thomas Aquinas"),
            new Task5.Contact("Rene Descartes"),
            new Task5.Contact("David Hume"),
            new Task5.Contact("John Locke")
        );

        // Act
        List<Task5.Contact> result = Task5.parseContacts(names, "ASC");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testParseContactsDescendingOrder() {
        // Arrange
        String[] names = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        List<Task5.Contact> expected = List.of(
            new Task5.Contact("Carl Gauss"),
            new Task5.Contact("Leonhard Euler"),
            new Task5.Contact("Paul Erdos")
        );

        // Act
        List<Task5.Contact> result = Task5.parseContacts(names, "DESC");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testParseContactsWithEmptyArray() {
        // Arrange
        String[] names = {};
        List<Task5.Contact> expected = List.of();

        // Act
        List<Task5.Contact> result = Task5.parseContacts(names, "DESC");

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testParseContactsWithNullArray() {
        // Arrange
        String[] names = null;
        List<Task5.Contact> expected = List.of();

        // Act
        List<Task5.Contact> result = Task5.parseContacts(names, "DESC");

        // Assert
        assertEquals(expected, result);
    }
}
