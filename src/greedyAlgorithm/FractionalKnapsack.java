package greedyAlgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class FractionalKnapsack {

    static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    static class Pair {
        double first; // value per unit weight
        Item second;

        public Pair(double first, Item second) {
            this.first = first;
            this.second = second;
        }
    }

    double fractionalKnapsack(int W, Item[] arr, int n) {
        Vector<Pair> v = new Vector<>();
        for (int i = 0; i < n; i++) {
            double perUnitValue = (double) arr[i].value / arr[i].weight;
            Pair pair = new Pair(perUnitValue, arr[i]);
            v.add(pair);
        }

        // Sort by value/weight in descending order
        Collections.sort(v, new Comparator<Pair>() {
            public int compare(Pair o1, Pair o2) {
                return Double.compare(o2.first, o1.first);
            }
        });

        double totalValue = 0;

        for (int i = 0; i < v.size(); i++) {
            Item currItem = v.get(i).second;
            if (currItem.weight > W) {
                totalValue += W * v.get(i).first;
                break;
            } else {
                totalValue += currItem.value;
                W -= currItem.weight;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        FractionalKnapsack fk = new FractionalKnapsack();

        // Example items
        Item[] items = {
                new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)
        };

        int W = 50;  // Maximum weight

        double maxValue = fk.fractionalKnapsack(W, items, items.length);
        System.out.println("Maximum value in knapsack = " + maxValue);
    }
}
