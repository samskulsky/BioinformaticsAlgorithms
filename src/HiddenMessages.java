import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HiddenMessages {

    // Finds the number of times a pattern appears in a string
    public static int patternCount(String text, String pattern) {
        int count = 0;

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                count++;
            }
        }

        return count;
    }

    // Finds patterns forming clumps in a string
    // A clump is a k-mer that appears at least t times in a window of size L
    public static String[] findClumps(String text, int k, int L, int t) {
        List<String> patterns = new ArrayList<>();

        int n = text.length();

        for (int i = 0; i < n - L; i++) {
            System.out.println(i);
            String window = text.substring(i, i + L);
            Map<String, Integer> freqMap = frequencyTable(window, k);

            for (String s : freqMap.keySet()) {
                if (freqMap.get(s) >= t) {
                    patterns.add(s);
                }
            }
        }

        patterns = removeDuplicates(patterns);
        return patterns.toArray(new String[0]);
    }

    // Removes duplicate elements from a list of Strings
    public static List<String> removeDuplicates(List<String> list) {
        List<String> newList = new ArrayList<>();

        for (String s : list) {
            if (!newList.contains(s)) {
                newList.add(s);
            }
        }

        return newList;
    }

    // Returns an array of starting positions of a pattern in a string
    public static Integer[] patternMatch(String text, String pattern) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                positions.add(i);
            }
        }

        return positions.toArray(new Integer[0]);
    }

    // Returns an array of starting positions of a pattern in a string
    // Each pattern must have d or fewer mismatches
    public static Integer[] approximatePatternMatch(String text, String pattern, int d) {
        List<Integer> positions = new ArrayList<>();

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (Mutations.hammingDistance(text.substring(i, i + pattern.length()), pattern) <= d) {
                positions.add(i);
            }
        }

        return positions.toArray(new Integer[0]);
    }

    // The number of occurences of pattern in text with at most d mismatches
    public static int approximatePatternCount(String text, String pattern, int d) {
        int count = 0;

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (Mutations.hammingDistance(text.substring(i, i + pattern.length()), pattern) <= d) {
                count++;
            }
        }

        return count;
    }

    // Generates the 1-neighborhood of a string
    public static String[] immediateNeighbors(String pattern) {
        List<String> neighborhood = new ArrayList<>();
        neighborhood.add(pattern);

        for (int i = 0; i < pattern.length(); i++) {
            char symbol = pattern.charAt(i);
            for (char x : new char[] { 'A', 'C', 'G', 'T' }) { // Modify this array based on your nucleotide alphabet
                if (x != symbol) {
                    String neighbor = pattern.substring(0, i) + x + pattern.substring(i + 1);
                    neighborhood.add(neighbor);
                }
            }
        }

        return neighborhood.toArray(new String[0]);
    }

    // Generates the d-neighborhood of a string
    public static String[] neighbors(String pattern, int d) {
        List<String> neighborhood = new ArrayList<>();
        neighborhood.add(pattern);

        for (int i = 1; i <= d; i++) { // Fix the off-by-one error here
            List<String> newNeighbors = new ArrayList<>();
            for (String pattern2 : neighborhood) {
                newNeighbors.addAll(Arrays.asList(immediateNeighbors(pattern2)));
            }
            neighborhood.clear();
            neighborhood.addAll(removeDuplicates(newNeighbors));
        }

        return neighborhood.toArray(new String[0]);
    }

    // Finds the most frequent k-mers in a string
    public static String[] frequentWords(String text, int k) {
        List<String> frequentPatterns = new ArrayList<>();

        Map<String, Integer> freqMap = frequencyTable(text, k);

        int max = maxMap(freqMap);

        for (String pattern : freqMap.keySet()) {
            if (freqMap.get(pattern) == max) {
                frequentPatterns.add(pattern);
            }
        }

        return frequentPatterns.toArray(new String[0]);
    }

    // Maps all k-mers in a string to the number of times they appear
    public static Map<String, Integer> frequencyTable(String text, int k) {
        Map<String, Integer> freqMap = new HashMap<>();

        int n = text.length();

        for (int i = 0; i < n - k; i++) {
            String pattern = text.substring(i, i + k);

            if (!freqMap.containsKey(pattern)) {
                freqMap.put(pattern, 1);
            } else {
                freqMap.put(pattern, freqMap.get(pattern) + 1);
            }
        }

        return freqMap;
    }

    // Returns the maximum value in a map
    public static int maxMap(Map<String, Integer> freqMap) {
        int maxValue = 0;
        boolean first = true;

        for (int value : freqMap.values()) {
            if (first || value > maxValue) {
                maxValue = value;
                first = false;
            }
        }

        return maxValue;
    }

}
