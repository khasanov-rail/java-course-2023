package edu.project3;

import java.time.format.DateTimeParseException;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LogParserTest {

    @Test
    @DisplayName("Тест парсинга строки лога")
    void testParseLogLine() {
        LogParser parser = new LogParser();
        String logLine = "192.168.1.1 - john [10/Oct/2000:13:55:36 -0700] \"GET /apache_pb.gif HTTP/1.0\" 200 2326";

        List<LogRecord> result = parser.parse(List.of(logLine));

        assertNotNull(result, "Результат не должен быть null");
        assertEquals(1, result.size(), "Должен быть один лог");
        LogRecord record = result.get(0);
        assertEquals("192.168.1.1", record.getRemoteAddr(), "IP адрес должен соответствовать");
        assertEquals(200, record.getStatus(), "Код ответа должен соответствовать");
        assertEquals(2326, record.getBodyBytesSent(), "Размер ответа должен соответствовать");
    }

    @Test
    @DisplayName("Парсинг лога с пустым запросом")
    void testParseEmptyRequest() {
        LogParser parser = new LogParser();
        String logLine = "192.168.1.1 - - [10/Oct/2000:13:55:36 -0700] \"\" 200 2326";

        List<LogRecord> result = parser.parse(List.of(logLine));

        assertNotNull(result, "Результат не должен быть null");
        assertEquals("", result.get(0).getRequest(), "Запрос должен быть пустым");
    }

    @Test
    @DisplayName("Парсинг лога с null запросом")
    void testParseNullRequest() {
        LogParser parser = new LogParser();
        String logLine = "192.168.1.1 - - [10/Oct/2000:13:55:36 -0700] \"null\" 200 2326";

        List<LogRecord> result = parser.parse(List.of(logLine));

        assertNotNull(result, "Результат не должен быть null");
        assertEquals(result.get(0).getRequest(), "null");
    }

    @Test
    @DisplayName("Парсинг лога с некорректным форматом")
    void testParseInvalidLogFormat() {
        LogParser parser = new LogParser();
        String logLine = "Некорректный формат лога";

        List<LogRecord> result = parser.parse(List.of(logLine));

        assertTrue(result.isEmpty(), "Результат должен быть пустым");
    }

    @Test
    @DisplayName("Парсинг лога с некорректной датой")
    void testParseInvalidDate() {
        LogParser parser = new LogParser();
        String logLine = "192.168.1.1 - - [НекорректнаяДата] \"GET /index.html HTTP/1.1\" 200 2326";

        assertThrows(DateTimeParseException.class, () -> parser.parse(List.of(logLine)),
            "Должно возникнуть исключение DateTimeParseException");
    }
}
