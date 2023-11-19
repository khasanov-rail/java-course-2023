package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogAnalyzerTest {

    @Test
    @DisplayName("Подсчет общего количества запросов")
    void testTotalRequestsCount() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);
        assertEquals(4, report.getTotalRequests(), "Общее количество запросов должно быть 4");
    }

    @Test
    @DisplayName("Подсчет наиболее часто запрашиваемых ресурсов")
    void testMostRequestedResources() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);

        String fullRequestText = "GET /index.html HTTP/1.1";
        assertTrue(report.getRequestsByResource().containsKey(fullRequestText), "Должен содержать запрос " + fullRequestText);
        assertEquals(2, report.getRequestsByResource().get(fullRequestText), fullRequestText + " должен быть запрошен 2 раза");
    }


    @Test
    @DisplayName("Подсчет наиболее часто встречающихся кодов ответа")
    void testMostFrequentResponseCodes() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);
        assertTrue(report.getStatusCodes().containsKey(200), "Должен содержать код 200");
        assertEquals(2, report.getStatusCodes().get(200), "Код 200 должен встречаться 2 раза");
    }

    @Test
    @DisplayName("Подсчет запросов по IP-адресам")
    void testRequestsByIP() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);
        assertEquals(1, report.getRequestsByIP().get("192.168.1.2"), "192.168.1.2 должен иметь 1 запрос");
    }

    @Test
    @DisplayName("Подсчет запросов по часам")
    void testRequestsByHour() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);
        assertEquals(1, report.getRequestsByHour().get(14), "В 14 часов должен быть 1 запрос");
    }

    @Test
    @DisplayName("Подсчет запросов по дням недели")
    void testRequestsByDayOfWeek() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);
        assertEquals(1, report.getRequestsByDayOfWeek().get("MONDAY"), "В понедельник должен быть 1 запрос");
    }

    @Test
    @DisplayName("Топ IP-адресов со статусом ошибки 4xx и 5xx")
    void testTopIPsWithErrors() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);
        assertEquals(2, report.getTopIPsWith4xx5xxErrors().size(), "Должен быть 2 IP с ошибками");
    }

    @Test
    @DisplayName("Соотношение GET и POST запросов")
    void testGetPostRatio() {
        LogAnalyzer analyzer = new LogAnalyzer();
        List<LogRecord> records = createTestLogRecords();
        LogReport report = analyzer.analyze(records);
        assertEquals(3.0, report.getGetPostRatio(), "Соотношение GET к POST должно быть 3");
    }

    private List<LogRecord> createTestLogRecords() {
        return Arrays.asList(
            new LogRecord.Builder()
                .remoteAddr("192.168.1.1")
                .remoteUser("-")
                .timeLocal(OffsetDateTime.of(2022, 10, 3, 14, 0, 0, 0, ZoneOffset.UTC))
                .request("GET /index.html HTTP/1.1")
                .status(200)
                .bodyBytesSent(500)
                .httpReferer("-")
                .userAgent("Mozilla/5.0")
                .build(),
            new LogRecord.Builder()
                .remoteAddr("192.168.1.1")
                .remoteUser("-")
                .timeLocal(OffsetDateTime.of(2022, 10, 4, 15, 0, 0, 0, ZoneOffset.UTC))
                .request("GET /index.html HTTP/1.1")
                .status(200)
                .bodyBytesSent(1500)
                .httpReferer("-")
                .userAgent("Mozilla/5.0")
                .build(),
            new LogRecord.Builder()
                .remoteAddr("192.168.1.2")
                .remoteUser("-")
                .timeLocal(OffsetDateTime.of(2023, 10, 5, 16, 0, 0, 0, ZoneOffset.UTC))
                .request("POST /contact.html HTTP/1.1")
                .status(404)
                .bodyBytesSent(600)
                .httpReferer("-")
                .userAgent("Mozilla/5.0")
                .build(),
            new LogRecord.Builder()
                .remoteAddr("192.168.1.3")
                .remoteUser("-")
                .timeLocal(OffsetDateTime.of(2024, 10, 5, 17, 0, 0, 0, ZoneOffset.UTC))
                .request("GET /error.html HTTP/1.1")
                .status(500)
                .bodyBytesSent(0)
                .httpReferer("-")
                .userAgent("Mozilla/5.0")
                .build()
        );
    }
}
