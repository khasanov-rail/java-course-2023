package edu.hw10.Task2.example;

import edu.hw10.Task2.annotations.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    long fib(int number);
}
