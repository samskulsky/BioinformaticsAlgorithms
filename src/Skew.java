import java.util.ArrayList;
import java.util.List;

public class Skew {

    // Finds the positions in a genome where the skew diagram attains a minimum
    public static Integer[] minimumSkewPositions(String genome) {
        List<Integer> positions = new ArrayList<>();
        int minimum = minimumSkew(genome);
        int[] diagram = skewDiagram(genome);

        for (int i = 0; i < diagram.length; i++) {
            if (diagram[i] == minimum) {
                positions.add(i);
            }
        }

        return positions.toArray(new Integer[0]);
    }

    // Returns the array representing the skew diagram of the genome
    public static int[] skewDiagram(String genome) {
        int[] skew = new int[genome.length() + 1];

        for (int i = 1; i < genome.length() + 1; i++) {
            if (genome.charAt(i - 1) == 'G') {
                skew[i] = skew[i - 1] + 1;
            } else if (genome.charAt(i - 1) == 'C') {
                skew[i] = skew[i - 1] - 1;
            } else {
                skew[i] = skew[i - 1];
            }
        }

        return skew;
    }

    // Finds the minimum value in the skew diagram
    public static int minimumSkew(String genome) {
        int[] skew = skewDiagram(genome);
        int minimum = 0;

        for (int i = 0; i < skew.length; i++) {
            if (skew[i] < minimum) {
                minimum = skew[i];
            }
        }

        return minimum;
    }

}
