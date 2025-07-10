package Trie.Interview;

public class StartWithProblem {

    static class Node {
        Node[] children;
        boolean eow;

        public Node() {
            children = new Node[26];
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            eow = false;
        }
    }

    static Node root = new Node();

    public static void Insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            if (i == word.length() - 1) {
                curr.children[idx].eow = true;
            }
            curr = curr.children[idx];
        }
    }

    public static boolean Search(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }

            if (i == word.length() - 1 && curr.children[idx].eow == false) {
                return false;
            }

            curr = curr.children[idx];
        }
        return true;
    }

    public static boolean startWith(String prefix) {
        Node curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';

            if (curr.children[idx] == null)
                return false;
            curr = curr.children[idx];
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = { "apple", "app", "mango", "man", "woman" };
        String prefix = "moon";

        for (int i = 0; i < words.length; i++) {
            Insert(words[i]);
        }

        System.out.println(startWith(prefix));
    }
}
