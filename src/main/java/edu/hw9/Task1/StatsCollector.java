package edu.hw9.Task1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class StatsCollector {
    private final ConcurrentHashMap<String, AtomicInteger> count;
    private final ConcurrentHashMap<String, AtomicReference<Double>> sum;
    private final ConcurrentHashMap<String, AtomicReference<Double>> max;
    private final ConcurrentHashMap<String, AtomicReference<Double>> min;

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

        count.putIfAbsent(metricName, new AtomicInteger(0));
        sum.putIfAbsent(metricName, new AtomicReference<>(0.0));
        max.putIfAbsent(metricName, new AtomicReference<>(Double.NEGATIVE_INFINITY));
        min.putIfAbsent(metricName, new AtomicReference<>(Double.POSITIVE_INFINITY));

        for (double value : values) {
            count.get(metricName).incrementAndGet();
            sum.get(metricName).updateAndGet(v -> v + value);
            max.get(metricName).updateAndGet(v -> Math.max(v, value));
            min.get(metricName).updateAndGet(v -> Math.min(v, value));
        }
    }

    public Map<String, Double> stats(String metricName) {
        if (metricName == null) {
            throw new IllegalArgumentException("Metric name must not be null");
        }

        Map<String, Double> stats = new ConcurrentHashMap<>();
        stats.put("sum", sum.getOrDefault(metricName, new AtomicReference<>(0.0)).get());
        stats.put("avg", count.getOrDefault(metricName, new AtomicInteger(0)).get() == 0
            ?
            0 : sum.get(metricName).get() / count.get(metricName).get());
        stats.put("max", max.getOrDefault(metricName, new AtomicReference<>(Double.NEGATIVE_INFINITY)).get());
        stats.put("min", min.getOrDefault(metricName, new AtomicReference<>(Double.POSITIVE_INFINITY)).get());
        return stats;
    }
}
