package edu.project3;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private static final int STATUS_CODE_4XX = 400;
    private static final int STATUS_CODE_6XX = 600;
    private static final int TOP_IP_LIMIT = 10;

    public LogReport analyze(List<LogRecord> logs) {
        LogReport report = new LogReport();

        report.setTotalRequests(logs.size());
        report.setRequestsByResource(countRequestsByResource(logs));
        report.setStatusCodes(countStatusCodes(logs));
        report.setRequestsByIP(countRequestsByIP(logs));
        report.setRequestsByHour(countRequestsByHour(logs));
        report.setRequestsByDayOfWeek(countRequestsByDayOfWeek(logs));
        report.setTopIPsWith4xx5xxErrors(countTopIPsWithErrors(logs));
        report.setGetPostRatio(calculateGetPostRatio(logs));
        report.setAverageResponseSize(calculateAverageResponseSize(logs));

        return report;
    }

    private Map<String, Long> countRequestsByResource(List<LogRecord> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(LogRecord::getRequest, Collectors.counting()));
    }

    private Map<Integer, Long> countStatusCodes(List<LogRecord> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(LogRecord::getStatus, Collectors.counting()));
    }

    private Map<String, Long> countRequestsByIP(List<LogRecord> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(LogRecord::getRemoteAddr, Collectors.counting()));
    }

    private Map<Integer, Integer> countRequestsByHour(List<LogRecord> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(log -> log.getTimeLocal().getHour(), Collectors.summingInt(e -> 1)));
    }

    private Map<String, Long> countRequestsByDayOfWeek(List<LogRecord> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(
                log -> log.getTimeLocal().getDayOfWeek().toString(),
                Collectors.counting()));
    }

    private Map<String, Long> countTopIPsWithErrors(List<LogRecord> logs) {
        return logs.stream()
            .filter(log -> log.getStatus() >= STATUS_CODE_4XX && log.getStatus() < STATUS_CODE_6XX)
            .collect(Collectors.groupingBy(
                LogRecord::getRemoteAddr,
                Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(TOP_IP_LIMIT)
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new));
    }

    private double calculateGetPostRatio(List<LogRecord> logs) {
        long getCount = logs.stream().filter(log -> log.getRequest().startsWith("GET")).count();
        long postCount = logs.stream().filter(log -> log.getRequest().startsWith("POST")).count();
        return postCount == 0 ? 0 : (double) getCount / postCount;
    }

    private double calculateAverageResponseSize(List<LogRecord> logs) {
        if (logs.isEmpty()) {
            return 0;
        }
        long totalSize = logs.stream().mapToLong(LogRecord::getBodyBytesSent).sum();
        return (double) totalSize / logs.size();
    }
}
