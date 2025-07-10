package Hashing;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashedMapCode {
    static class HashMap<K, V> {
        private int N;
        private LinkedList<Node>[] buckets;
        private int n;

        @SuppressWarnings("unchecked")
        public HashMap() {
            this.N = 4;
            this.buckets = new LinkedList[N];
            for (int i = 0; i < N; i++) {
                buckets[i] = new LinkedList<>();
            }
        }

        public static void main(String[] args) {
            HashMap<String, Integer> hashMap = new HashMap<>();

            // Add some elements to the hashMap
            hashMap.put("India", 150);
            hashMap.put("USA", 200);
            hashMap.put("China", 300);
            hashMap.put("Japan", 400);
            hashMap.put("Germany", 500);
            hashMap.put("France", 600);
            hashMap.put("Italy", 700);
            hashMap.put("Spain", 800);
            hashMap.put("Brazil", 900);
            hashMap.put("Russia", 1000);
            hashMap.put("India", 100);  // Update value for India

            ArrayList<String> keys = hashMap.keySet();

            for (String key : keys) {
                System.out.println("keys:" + key);
            }
            // Print the updated value for India
            System.out.println("India: " + hashMap.get("India")); // Expected 100

            // Check if a key exists
            System.out.println("Contains Japan? " + hashMap.containsKey("Japan")); // Expected true
            System.out.println("Contains Mexico? " + hashMap.containsKey("Mexico")); // Expected false

            // Remove a key-value pair and print the removed value
            System.out.println("Removed Japan: " + hashMap.remove("Japan")); // Expected 400

            // Check if the hashMap is empty
            System.out.println("Is hashMap empty? " + hashMap.isEmpty()); // Expected false

            // Remove another key and check the hashMap's size
            hashMap.remove("India");
            System.out.println("Contains India after removal? " + hashMap.containsKey("India")); // Expected false

            // Finally, check if the hashMap is empty
            System.out.println("Is hashMap empty after removal? " + hashMap.isEmpty()); // Expected false
        }

        private int hashFunction(K key) {
            int bi = key.hashCode();
            return Math.abs(bi) % N;
        }

        private int searchInLL(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];
            for (int i = 0; i < ll.size(); i++) {
                if (ll.get(i).key.equals(key)) {
                    return i;
                }
            }
            return -1;
        }


        @SuppressWarnings("unchecked")
        private void reHash() {
            LinkedList<Node>[] oldBuckets = buckets;
            N = N * 2;  // Double the bucket size
            buckets = new LinkedList[N];

            for (int i = 0; i < N; i++) {
                buckets[i] = new LinkedList<>();
            }

            for (LinkedList<Node> ll : oldBuckets) {
                for (Node node : ll) {
                    put(node.key, node.value);  // Rehash all nodes
                }
            }
        }

        public void put(K key, V value) {
            int bi = hashFunction(key);  // bucket index
            int di = searchInLL(key, bi);  // data index

            if (di == -1) {
                buckets[bi].add(new Node(key, value));
                n++;
            } else {
                Node node = buckets[bi].get(di);
                node.value = value;
            }

            double lambda = (double) n / N;
            if (lambda > 2.0) {
                // Trigger rehashing when the load factor exceeds 2
                reHash();
            }
        }

        public V get(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                return null;
            } else {
                return buckets[bi].get(di).value;
            }
        }

        public boolean containsKey(K key) {
            return get(key) != null;
        }

        public V remove(K key) {
            int bi = hashFunction(key);
            int di = searchInLL(key, bi);
            if (di == -1) {
                return null;
            } else {
                V value = buckets[bi].get(di).value;
                buckets[bi].remove(di);
                n--;
                return value;
            }
        }

        public ArrayList<K> keySet() {
            ArrayList<K> keys = new ArrayList<>();
            for (LinkedList<Node> ll : buckets) {
                for (Node node : ll) {
                    keys.add(node.key);
                }
            }
            return keys;
        }

        public boolean isEmpty() {
            return n == 0;
        }

        private class Node {
            K key;
            V value;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}
