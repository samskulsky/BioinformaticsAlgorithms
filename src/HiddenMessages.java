public class HiddenMessages {

    public static int patternCount(String text, String pattern) {
        int count = 0;

        for (int i = 0; i < text.length() - pattern.length(); i++) {
            if (text.substring(i, i + pattern.length()).equals(pattern)) {
                count++;
            }
        }

        return count;
    }

}
