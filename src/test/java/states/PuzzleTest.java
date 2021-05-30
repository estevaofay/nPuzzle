package states;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PuzzleTest {

    @Test
    void createDefaultPuzzle() {
        int[][] expectedMatrix = new int[3][3];
        expectedMatrix[0][0] = 1;
        expectedMatrix[0][1] = 2;
        expectedMatrix[0][2] = 3;
        expectedMatrix[1][0] = 4;
        expectedMatrix[1][1] = 5;
        expectedMatrix[1][2] = 6;
        expectedMatrix[2][0] = 7;
        expectedMatrix[2][1] = 8;
        expectedMatrix[2][2] = 9;

        assertMatricesAreEqual(expectedMatrix, new Puzzle(3).getMatrix());
    }

    private void assertMatricesAreEqual(final int[][] expectedMatrix, final int[][] actualMatrix) {
        for (int i = 0; i < expectedMatrix.length; i++) {
            for (int j = 0; j < expectedMatrix.length; j++) {
                assertEquals(expectedMatrix[i][j], actualMatrix[i][j]);
            }
        }
    }

    @Test
    void isGoal() {
        assertTrue(new Puzzle(3).isGoal());
    }

    @Test
    void isNotGoal() {
        final Puzzle puzzle = new Puzzle(3);
        assertFalse(puzzle.move(Puzzle.Direction.UP).isGoal());
    }

    @Test
    void moveUp() {
        int[][] expectedMatrix = new int[3][3];
        expectedMatrix[0][0] = 1;
        expectedMatrix[0][1] = 2;
        expectedMatrix[0][2] = 3;
        expectedMatrix[1][0] = 4;
        expectedMatrix[1][1] = 5;
        expectedMatrix[1][2] = 9;
        expectedMatrix[2][0] = 7;
        expectedMatrix[2][1] = 8;
        expectedMatrix[2][2] = 6;

        final Puzzle puzzle = new Puzzle(3);
        assertMatricesAreEqual(expectedMatrix, puzzle.move(Puzzle.Direction.UP).getMatrix());

    }

    @Test
    void moveDownFromDefaultState() {
        final Puzzle puzzle = new Puzzle(3);
        assertNull(puzzle.move(Puzzle.Direction.DOWN));
    }

    @Test
    void moveDown() {
        int[][] expectedMatrix = new int[3][3];
        expectedMatrix[0][0] = 1;
        expectedMatrix[0][1] = 2;
        expectedMatrix[0][2] = 3;
        expectedMatrix[1][0] = 4;
        expectedMatrix[1][1] = 5;
        expectedMatrix[1][2] = 9;
        expectedMatrix[2][0] = 7;
        expectedMatrix[2][1] = 8;
        expectedMatrix[2][2] = 6;

        final Puzzle puzzle = new Puzzle(3);
        final int[][] actualMatrix = puzzle.move(Puzzle.Direction.UP).move(Puzzle.Direction.UP).move(Puzzle.Direction.DOWN).getMatrix();

        assertMatricesAreEqual(expectedMatrix, actualMatrix);
    }
}