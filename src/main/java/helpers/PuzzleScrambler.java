package helpers;

import states.Puzzle;

public class PuzzleScrambler {

    public static final int FIFTY_THOUSAND_TIMES = 50000;

    public static Puzzle scramblePuzzle(final Puzzle puzzle) {
        shufflePuzzleNTimes(puzzle, FIFTY_THOUSAND_TIMES);
        return puzzle;
    }

    private static void shufflePuzzleNTimes(final Puzzle puzzle, final int n) {
        int direction;
        for (int i = 0; i < n; i++) {
            direction = pickRandomDirection();
            switch (direction) {
                case 1:
                    tryToMovePuzzleUp(puzzle);
                    break;
                case 2:
                    tryToMovePuzzleDown(puzzle);
                    break;
                case 3:
                    tryToMovePuzzleRight(puzzle);
                    break;
                case 4:
                    tryToMovePuzzleLeft(puzzle);
                    break;
            }
        }
    }

    private static void moveOriginalPuzzle(final Puzzle originalPuzzle, final Puzzle movedPuzzle) {
        for (int j = 0; j < originalPuzzle.getMatrix().length; j++) {
            for (int k = 0; k < originalPuzzle.getMatrix().length; k++) {
                originalPuzzle.getMatrix()[j][k] = movedPuzzle.getMatrix()[j][k];
            }
        }
    }

    private static void tryToMovePuzzleLeft(final Puzzle puzzle) {
        if(puzzleCanMoveLeft(puzzle)) {
            Puzzle movedPuzzle = puzzle.move(Puzzle.Direction.LEFT);
            moveOriginalPuzzle(puzzle, movedPuzzle);
        }
    }

    private static void tryToMovePuzzleRight(final Puzzle puzzle) {
        if(puzzleCanMoveRight(puzzle)) {
            Puzzle movedPuzzle = puzzle.move(Puzzle.Direction.RIGHT);
            moveOriginalPuzzle(puzzle, puzzle);
        }
    }

    private static void tryToMovePuzzleDown(final Puzzle puzzle) {
        if(puzzleCanMoveDown(puzzle)){
            Puzzle movedPuzzle = puzzle.move(Puzzle.Direction.DOWN);
            moveOriginalPuzzle(puzzle, movedPuzzle);
        }
    }

    private static void tryToMovePuzzleUp(final Puzzle puzzle) {
        if(puzzleCanMoveUp(puzzle)) {
            Puzzle movedPuzzle = puzzle.move(Puzzle.Direction.UP);
            moveOriginalPuzzle(puzzle, movedPuzzle);
        }
    }

    private static boolean puzzleCanMoveLeft(Puzzle puzzle) {
        return puzzle.move(Puzzle.Direction.LEFT) != null;
    }

    private static boolean puzzleCanMoveRight(final Puzzle puzzle) {
        return puzzle.move(Puzzle.Direction.RIGHT) != null;
    }

    private static boolean puzzleCanMoveDown(final Puzzle puzzle) {
        return puzzle.move(Puzzle.Direction.DOWN) != null;
    }

    private static boolean puzzleCanMoveUp(final Puzzle puzzle) {
        return puzzle.move(Puzzle.Direction.UP) != null;
    }

    private static int pickRandomDirection() {
        return (int) (Math.random() * 4) + 1;
    }
}
