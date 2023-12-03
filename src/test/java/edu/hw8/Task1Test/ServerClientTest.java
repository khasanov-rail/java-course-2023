package edu.hw8.Task1Test;

import java.io.IOException;
import edu.hw8.Task1.Client;
import edu.hw8.Task1.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerClientTest {

    private static Server server;
    private Client client;

    @BeforeAll
    static void startServer() {
        server = new Server(8080, 4);
        new Thread(() -> server.start()).start();
    }

    @BeforeEach
    void setupClient() {
        client = new Client("localhost", 8080);
    }

    @Test
    @DisplayName("Тест на получение цитаты 'личности'")
    void testQuoteForPersonalities() throws IOException {
        // Arrange
        String expectedResponse = "Не переходи на личности там, где их нет";
        String request = "личности";

        // Act
        String actualResponse = client.sendRequest(request);

        // Assert
        assertEquals(
            expectedResponse,
            actualResponse,
            "Ответ сервера на запрос 'личности' не соответствует ожидаемому"
        );
    }

    @Test
    @DisplayName("Тест на получение цитаты 'интеллект'")
    void testQuoteForIntellect() throws IOException {
        // Arrange
        String expectedResponse = "Чем ниже интеллект, тем громче оскорбления";
        String request = "интеллект";

        // Act
        String actualResponse = client.sendRequest(request);

        // Assert
        assertEquals(
            expectedResponse,
            actualResponse,
            "Ответ сервера на запрос 'интеллект' не соответствует ожидаемому"
        );
    }

    @Test
    @DisplayName("Тест на несуществующую цитату")
    void testNonExistingQuote() throws IOException {
        // Arrange
        String expectedResponse = "Цитата не найдена";
        String request = "неизвестно";

        // Act
        String actualResponse = client.sendRequest(request);

        // Assert
        assertEquals(
            expectedResponse,
            actualResponse,
            "Ответ сервера на несуществующий запрос не соответствует ожидаемому"
        );
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }
}
