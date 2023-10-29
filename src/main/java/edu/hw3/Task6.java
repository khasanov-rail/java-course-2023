package edu.hw3;

import java.util.Objects;
import java.util.PriorityQueue;

public class Task6 {

    private Task6() {
    }

    interface StockMarket {
        void add(Stock stock);

        void remove(Stock stock);

        Stock mostValuableStock();
    }

    public static class Stock {
        private final String name;
        private final double price;

        public Stock(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Stock stock = (Stock) obj;
            return Double.compare(stock.price, price) == 0 && name.equals(stock.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, price);
        }
    }

    public static class StockMarketImpl implements StockMarket {
        private final PriorityQueue<Stock> stocks =
            new PriorityQueue<>((s1, s2) -> Double.compare(s2.getPrice(), s1.getPrice()));

        @Override
        public void add(Stock stock) {
            stocks.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            stocks.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            return stocks.peek();
        }
    }
}
