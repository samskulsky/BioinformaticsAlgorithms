public class DNAOperations {

    // Returns the reverse complement of a string
    public static String reverseComplement(String pattern) {
        return complement(reverse(pattern));
    }

    // Returns the reverse of a string
    public static String reverse(String pattern) {
        StringBuilder reverse = new StringBuilder();

        for (int i = 0; i < pattern.length(); i++) {
            reverse.insert(0, pattern.charAt(i));
        }

        return reverse.toString();
    }

    // Returns the complement of a string
    public static String complement(String pattern) {
        StringBuilder complement = new StringBuilder();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);

            if (c == 'A') {
                complement.append('T');
            } else if (c == 'T') {
                complement.append('A');
            } else if (c == 'C') {
                complement.append('G');
            } else if (c == 'G') {
                complement.append('C');
            }
        }

        return complement.toString();
    }

}
