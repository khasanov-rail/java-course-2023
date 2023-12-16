package edu.hw9.Task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StatsCollector {
    private final ConcurrentHashMap<String, Integer> count;
    private final ConcurrentHashMap<String, Double> sum;
    private final ConcurrentHashMap<String, Double> max;
    private final ConcurrentHashMap<String, Double> min;

    public StatsCollector() {
        count = new ConcurrentHashMap<>();
        sum = new ConcurrentHashMap<>();
        max = new ConcurrentHashMap<>();
        min = new ConcurrentHashMap<>();
    }

    public void push(String metricName, double[] values) {
        if (metricName == null || values == null || values.length == 0) {
            throw new IllegalArgumentException("Metric name and values must not be null or empty");
        }

        count.putIfAbsent(metricName, 0);
        sum.putIfAbsent(metricName, 0.0);
        max.putIfAbsent(metricName, Double.NEGATIVE_INFINITY);
        min.putIfAbsent(metricName, Double.POSITIVE_INFINITY);

        for (double value : values) {
            count.compute(metricName, (k, v) -> (v == null) ? 1 : v + 1);
            sum.compute(metricName, (k, v) -> (v == null) ? value : v + value);
            max.compute(metricName, (k, v) -> (v == null) ? value : Math.max(v, value));
            min.compute(metricName, (k, v) -> (v == null) ? value : Math.min(v, value));
        }
    }

    public Map<String, Double> stats(String metricName) {
        if (metricName == null) {
            throw new IllegalArgumentException("Metric name must not be null");
        }

        Map<String, Double> stats = new ConcurrentHashMap<>();
        stats.put("sum", sum.getOrDefault(metricName, 0.0));
        stats.put("avg", count.getOrDefault(metricName, 0) == 0
            ? 0 : sum.get(metricName) / count.get(metricName));
        stats.put("max", max.getOrDefault(metricName, Double.NEGATIVE_INFINITY));
        stats.put("min", min.getOrDefault(metricName, Double.POSITIVE_INFINITY));
        return stats;
    }
}
