package edu.project3;

import java.util.Map;

public class LogReport {
    private int totalRequests;
    private Map<String, Long> requestsByResource;
    private Map<Integer, Long> statusCodes;
    private Map<String, Long> requestsByIP;
    private Map<Integer, Integer> requestsByHour;
    private Map<String, Long> requestsByDayOfWeek;
    private Map<String, Long> topIPsWith4xx5xxErrors;
    private double getPostRatio;
    private double averageResponseSize;

    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();

        sb.append("#### Общая информация\n\n");
        sb.append("| Метрика | Значение |\n");
        sb.append("|---------|---------:|\n");
        sb.append(String.format("| Количество запросов | %d |\n", totalRequests));

        formatMapAsTable(sb, "Запрашиваемые ресурсы", requestsByResource);
        formatMapAsTable(sb, "Коды ответа", statusCodes);
        formatMapAsTable(sb, "Запросы по IP", requestsByIP);
        formatMapAsTable(sb, "Запросы по часам", requestsByHour);
        formatMapAsTable(sb, "Запросы по дням недели", requestsByDayOfWeek);
        formatMapAsTable(sb, "Топ IP-адресов с ошибками 4xx и 5xx", topIPsWith4xx5xxErrors);
        sb.append("\n#### GET/POST Ratio\n\n");
        sb.append("* GET/POST Ratio: ").append(String.format("%.2f", getPostRatio)).append("\n");

        sb.append("\n#### Средний размер ответа сервера\n\n");
        sb.append("* Средний размер ответа: ").append(String.format("%.2f bytes", averageResponseSize)).append("\n");

        return sb.toString();
    }

    private <K, V> void formatMapAsTable(StringBuilder sb, String title, Map<K, V> map) {
        sb.append("\n#### ").append(title).append("\n\n");
        sb.append("| Ключ | Значение |\n");
        sb.append("|------|---------:|\n");
        map.forEach((key, value) -> sb.append("| ").append(key).append(" | ").append(value).append(" |\n"));
    }


    public int getTotalRequests() {
        return totalRequests;
    }

    public void setTotalRequests(int totalRequests) {
        this.totalRequests = totalRequests;
    }

    public Map<String, Long> getRequestsByResource() {
        return requestsByResource;
    }

    public void setRequestsByResource(Map<String, Long> requestsByResource) {
        this.requestsByResource = requestsByResource;
    }

    public Map<Integer, Long> getStatusCodes() {
        return statusCodes;
    }

    public void setStatusCodes(Map<Integer, Long> statusCodes) {
        this.statusCodes = statusCodes;
    }

    public Map<String, Long> getRequestsByIP() {
        return requestsByIP;
    }

    public void setRequestsByIP(Map<String, Long> requestsByIP) {
        this.requestsByIP = requestsByIP;
    }

    public Map<Integer, Integer> getRequestsByHour() {
        return requestsByHour;
    }

    public void setRequestsByHour(Map<Integer, Integer> requestsByHour) {
        this.requestsByHour = requestsByHour;
    }

    public Map<String, Long> getRequestsByDayOfWeek() {
        return requestsByDayOfWeek;
    }

    public void setRequestsByDayOfWeek(Map<String, Long> requestsByDayOfWeek) {
        this.requestsByDayOfWeek = requestsByDayOfWeek;
    }

    public Map<String, Long> getTopIPsWith4xx5xxErrors() {
        return topIPsWith4xx5xxErrors;
    }

    public void setTopIPsWith4xx5xxErrors(Map<String, Long> topIPsWith4xx5xxErrors) {
        this.topIPsWith4xx5xxErrors = topIPsWith4xx5xxErrors;
    }

    public double getGetPostRatio() {
        return getPostRatio;
    }

    public void setGetPostRatio(double getPostRatio) {
        this.getPostRatio = getPostRatio;
    }

    public double getAverageResponseSize() {
        return averageResponseSize;
    }

    public void setAverageResponseSize(double averageResponseSize) {
        this.averageResponseSize = averageResponseSize;
    }
}
