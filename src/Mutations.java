public class Mutations {

    // Returns the number of mismatches between two strings
    public static int hammingDistance(String p, String q) {
        int distance = 0;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) != q.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }

}
