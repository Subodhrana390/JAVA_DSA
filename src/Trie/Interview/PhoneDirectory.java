package Trie.Interview;

import java.util.Vector;

public class PhoneDirectory {

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

    void insert(String word) {
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

    void printSuggestions(Node curr, Vector<String> temp, StringBuilder prefix) {
        if (curr.eow) {
            temp.add(prefix.toString());
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            Node next = curr.children[ch - 'a'];
            if (next != null) {
                prefix.append(ch);
                printSuggestions(next, temp, prefix);
                prefix.deleteCharAt(prefix.length() - 1); // backtrack
            }
        }
    }

    Vector<Vector<String>> getSuggestions(String str) {
        Node prev = root;
        Vector<Vector<String>> output = new Vector<>();
        StringBuilder prefix = new StringBuilder();

        for (char ch : str.toCharArray()) {
            prefix.append(ch);
            Node curr = prev.children[ch - 'a'];
            if (curr == null) {
                break;
            }

            Vector<String> temp = new Vector<>();
            printSuggestions(curr, temp, prefix);
            output.add(temp);

            prev = curr;
        }

        return output;
    }

    Vector<Vector<String>> findDirectory(Vector<String> contactList, String queryStr) {
        for (String word : contactList) {
            insert(word.toLowerCase());
        }

        return getSuggestions(queryStr.toLowerCase());
    }

    public static void main(String[] args) {
        PhoneDirectory pd = new PhoneDirectory();

        Vector<String> contacts = new Vector<>();
        contacts.add("alice");
        contacts.add("alex");
        contacts.add("bob");
        contacts.add("bobby");
        contacts.add("carol");

        String query = "ali";

        Vector<Vector<String>> suggestions = pd.findDirectory(contacts, query);

        int i = 1;
        for (Vector<String> level : suggestions) {
            System.out.println("Suggestions for \"" + query.substring(0, i++) + "\": " + level);
        }
    }
}
