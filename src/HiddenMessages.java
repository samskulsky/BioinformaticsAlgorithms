import java.util.HashMap;
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
