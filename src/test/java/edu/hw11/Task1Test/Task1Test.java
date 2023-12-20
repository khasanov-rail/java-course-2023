package edu.hw11.Task1Test;

import edu.hw11.Task1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {

    @Test
    @DisplayName("Проверка метода toString класса HelloByteBuddy")
    void testHelloByteBuddyToString() throws Exception {
        Task1 task1 = new Task1();
        Object instance = task1.createHelloByteBuddyClass().getDeclaredConstructor().newInstance();
        assertEquals("Hello, ByteBuddy!", instance.toString());
    }
}
