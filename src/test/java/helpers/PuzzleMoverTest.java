package helpers;

import org.junit.jupiter.api.Test;
import states.Puzzle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static utils.TestUtils.assertMatricesAreEqual;
import static utils.TestUtils.createDefaultPuzzle;

class PuzzleMoverTest {

    @Test
    void movePuzzleUp(){
        final int dimension = 3;
        final Puzzle puzzle = new Puzzle(dimension);
        final int[][] expectedMatrix = createDefaultPuzzle(dimension);
        expectedMatrix[1][2] = 9;
        expectedMatrix[2][2] = 6;
        assertMatricesAreEqual(expectedMatrix, PuzzleMover.movePuzzleUp(puzzle).getMatrix());
    }

    @Test
    void shouldFailToMovePuzzleUpIfOnTop() {
        assertThrows(PuzzleMover.PuzzleCannotBeMovedUpException.class, () -> {
            final int dimension = 3;
            final Puzzle puzzle = new Puzzle(dimension);
            PuzzleMover.movePuzzleUp(puzzle);
            PuzzleMover.movePuzzleUp(puzzle);
            PuzzleMover.movePuzzleUp(puzzle);
        });
    }

    @Test
    void shouldFailToMovePuzzleDownIfOnBottom(){
        assertThrows(PuzzleMover.PuzzleCannotBeMovedDownException.class, () -> {
            final int dimension = 3;
            final Puzzle puzzle = new Puzzle(dimension);
            PuzzleMover.movePuzzleDown(puzzle);
        });
    }

    @Test
    void movePuzzleDown() {
        final int dimension = 3;
        final Puzzle puzzle = new Puzzle(dimension);
        final Puzzle actualPuzzle = PuzzleMover.movePuzzleDown(
                PuzzleMover.movePuzzleUp(
                        PuzzleMover.movePuzzleUp(puzzle)));
        final int[][] expectedMatrix = createDefaultPuzzle(dimension);
        expectedMatrix[1][2] = 9;
        expectedMatrix[2][2] = 6;
        assertMatricesAreEqual(expectedMatrix, actualPuzzle.getMatrix());
    }

    @Test
    void movePuzzleRight() {
        final int dimension = 3;
        final Puzzle puzzle = new Puzzle(dimension);
        final Puzzle actualPuzzle = PuzzleMover.movePuzzleRight(PuzzleMover.movePuzzleLeft(PuzzleMover.movePuzzleLeft(puzzle)));
        final int[][] expectedMatrix = createDefaultPuzzle(dimension);
        expectedMatrix[2][1] = 9;
        expectedMatrix[2][2] = 8;
        assertMatricesAreEqual(expectedMatrix, actualPuzzle.getMatrix());

    }

    @Test
    void shouldFailToMovePuzzleRightIfOnRightSide() {
        assertThrows(PuzzleMover.PuzzleCannotBeMovedRightException.class, () -> {
            final int dimension = 3;
            final Puzzle puzzle = new Puzzle(dimension);
            PuzzleMover.movePuzzleRight(puzzle);
        });
    }

    @Test
    void movePuzzleLeft() {
        final int dimension = 3;
        final Puzzle puzzle = new Puzzle(dimension);
        final Puzzle actualPuzzle = PuzzleMover.movePuzzleLeft(puzzle);
        final int[][] expectedMatrix = createDefaultPuzzle(dimension);
        expectedMatrix[2][1] = 9;
        expectedMatrix[2][2] = 8;
        assertMatricesAreEqual(expectedMatrix, actualPuzzle.getMatrix());
    }

    @Test
    void shouldFailToMovePuzzleLeftIfOnLeftSide() {
        assertThrows(PuzzleMover.PuzzleCannotBeMovedLeftException.class, () -> {
            final int dimension = 3;
            final Puzzle puzzle = new Puzzle(dimension);
            PuzzleMover.movePuzzleLeft(PuzzleMover.movePuzzleLeft(PuzzleMover.movePuzzleLeft(puzzle)));
        });
    }

}