package greedyAlgorithm;

public class ReverseWordsInAGivenString {

    String reverseWords(String str) {
        String[] words = str.split("\\.");
        StringBuilder result = new StringBuilder();

        // Traverse words array in reverse order
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i != 0) {
                result.append(".");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ReverseWordsInAGivenString obj = new ReverseWordsInAGivenString();

        String input = "i.like.this.program.very.much";
        String output = obj.reverseWords(input);

        System.out.println("Reversed: " + output);  // Output: much.very.program.this.like.i
    }
}
