package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUtils {

    public static void assertMatricesAreEqual(final int[][] expectedMatrix, final int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                assertEquals(expectedMatrix[i][j], matrix[i][j]);
            }
        }
    }

    public static int[][] createDefaultPuzzle(final int dimension) {
        final int[][] defaultPuzzle = new int[dimension][dimension];
        int filler = 1;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                defaultPuzzle[i][j] = filler;
                filler++;
            }
        }
        return defaultPuzzle;
    }
}
