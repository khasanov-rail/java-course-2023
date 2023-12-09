package edu.hw9.Task1Test;

import edu.hw9.Task1.StatsCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StatsCollectorTest {

    @Test
    @DisplayName("Test simple stats collection")
    void testSimpleStatsCollection() {
        // Arrange
        StatsCollector collector = new StatsCollector();
        String metricName = "testMetric";
        double[] values = {1.0, 2.0, 3.0};

        // Act
        collector.push(metricName, values);

        // Assert
        assertEquals(6.0, collector.stats(metricName).get("sum"), "Sum should be correct");
        assertEquals(2.0, collector.stats(metricName).get("avg"), "Average should be correct");
        assertEquals(3.0, collector.stats(metricName).get("max"), "Max should be correct");
        assertEquals(1.0, collector.stats(metricName).get("min"), "Min should be correct");
    }

    @ParameterizedTest
    @CsvSource({
        "metric1, 0",
        "metric2, 1",
        "metric3, -1"
    })
    @DisplayName("Test stats with different metrics")
    void testStatsWithDifferentMetrics(String metric, double value) {
        // Arrange
        StatsCollector collector = new StatsCollector();
        double[] values = {value};

        // Act
        collector.push(metric, values);

        // Assert
        assertEquals(value, collector.stats(metric).get("sum"), "Sum should be correct for " + metric);
        assertEquals(value, collector.stats(metric).get("avg"), "Average should be correct for " + metric);
        assertEquals(value, collector.stats(metric).get("max"), "Max should be correct for " + metric);
        assertEquals(value, collector.stats(metric).get("min"), "Min should be correct for " + metric);
    }

    @Test
    @DisplayName("Test push with null metric name")
    void testPushWithNullMetricName() {
        // Arrange
        StatsCollector collector = new StatsCollector();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> collector.push(null, new double[] {1.0, 2.0}),
            "Push should throw IllegalArgumentException for null metric name"
        );
    }

    @Test
    @DisplayName("Test push with null values")
    void testPushWithNullValues() {
        // Arrange
        StatsCollector collector = new StatsCollector();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> collector.push("testMetric", null),
            "Push should throw IllegalArgumentException for null values array"
        );
    }

    @Test
    @DisplayName("Test push with empty values")
    void testPushWithEmptyValues() {
        // Arrange
        StatsCollector collector = new StatsCollector();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> collector.push("testMetric", new double[] {}),
            "Push should throw IllegalArgumentException for empty values array"
        );
    }

    @Test
    @DisplayName("Test stats with null metric name")
    void testStatsWithNullMetricName() {
        // Arrange
        StatsCollector collector = new StatsCollector();

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> collector.stats(null),
            "Stats should throw IllegalArgumentException for null metric name"
        );
    }
}
