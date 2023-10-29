package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    void testAddAndMostValuableStock() {
        // Arrange
        Task6.StockMarket stockMarket = new Task6.StockMarketImpl();
        Task6.Stock stock1 = new Task6.Stock("Apple", 150.0);
        Task6.Stock stock2 = new Task6.Stock("Microsoft", 200.0);
        Task6.Stock stock3 = new Task6.Stock("Google", 250.0);

        // Act
        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        // Assert
        assertEquals(stock3, stockMarket.mostValuableStock(), "The most valuable stock should be Google");
    }

    @Test
    void testRemove() {
        // Arrange
        Task6.StockMarket stockMarket = new Task6.StockMarketImpl();
        Task6.Stock stock1 = new Task6.Stock("Apple", 150.0);
        Task6.Stock stock2 = new Task6.Stock("Microsoft", 200.0);
        Task6.Stock stock3 = new Task6.Stock("Google", 250.0);

        stockMarket.add(stock1);
        stockMarket.add(stock2);
        stockMarket.add(stock3);

        // Act
        stockMarket.remove(stock2);

        // Assert
        assertEquals(stock3, stockMarket.mostValuableStock(), "After removing Microsoft, the most valuable stock should be Google");
    }

    @Test
    void testMostValuableStockWithEmptyMarket() {
        // Arrange
        Task6.StockMarket stockMarket = new Task6.StockMarketImpl();

        // Act & Assert
        assertNull(stockMarket.mostValuableStock(), "With an empty stock market, the most valuable stock should be null");
    }
}
