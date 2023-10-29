package edu.hw3;

import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {
    }

    public static List<String> clusterize(String input) {
        List<String> clusters = new ArrayList<>();
        StringBuilder cluster = new StringBuilder();
        int balance = 0;

        for (char c : input.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                balance--;
            }
            cluster.append(c);
            if (balance == 0 && cluster.length() > 0) {
                clusters.add(cluster.toString());
                cluster.setLength(0);
            }
        }

        if (balance != 0) {
            throw new IllegalArgumentException("Input string is not balanced");
        }

        return clusters;
    }
}
