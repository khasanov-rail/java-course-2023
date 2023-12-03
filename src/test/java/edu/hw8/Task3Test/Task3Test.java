package edu.hw8.Task3Test;

import edu.hw8.Task3.Task3;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task3Test {

    private Task3 task3;
    private Map<String, String> passwordHashes;

    @BeforeEach
    void setUp() {
        passwordHashes = new HashMap<>();
        passwordHashes.put("e10adc3949ba59abbe56e057f20f883e", "123");
        passwordHashes.put("d8578edf8458ce06fbc5bb76a58c5ca4", "qwe");
        passwordHashes.put("482c811da5d5b4bc6d497ffa98491e38", "pas");
        passwordHashes.put("5f4dcc3b5aa765d61d8327deb882cf99", "pas");
        task3 = new Task3(passwordHashes);
    }

    @Test
    @DisplayName("Тест на null входные данные")
    void testWithNullInput() {
        assertThrows(NullPointerException.class, () -> new Task3(null));
    }

    @Test
    @DisplayName("Тест на пустую карту хешей")
    void testWithEmptyMap() throws NoSuchAlgorithmException {
        task3 = new Task3(new HashMap<>());
        assertTrue(task3.crackPasswords().isEmpty());
    }

    @Test
    @DisplayName("Тест на несуществующие хеши")
    void testWithNonMatchingHashes() throws NoSuchAlgorithmException {
        passwordHashes.put("nonexistenthash", "nonexistentuser");
        task3 = new Task3(passwordHashes);
        Map<String, String> crackedPasswords = task3.crackPasswords();
        assertNull(crackedPasswords.get("nonexistentuser"));
    }
}
