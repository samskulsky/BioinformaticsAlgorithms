import java.util.ArrayList;
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

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                positions.add(i);
            }
        }

        return positions.toArray(new Integer[0]);
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
