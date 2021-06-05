package helpers;

import states.Puzzle;

public class PuzzleMover {

    public static Puzzle movePuzzleUp(final Puzzle puzzle) {
        final Puzzle returnPuzzle = puzzle;
        for (int i = 0; i < puzzle.getMatrix().length; i++) {
            for (int j = 0; j < puzzle.getMatrix().length; j++) {
                if (isMovablePiece(puzzle, i, j)) {
                    if (canMoveUp(i)) {
                        returnPuzzle.getMatrix()[i][j] = returnPuzzle.getMatrix()[i - 1][j];
                        returnPuzzle.getMatrix()[i - 1][j] = (int) Math.pow(puzzle.getDimension(), 2);
                        return returnPuzzle;
                    }
                }
            }
        }
        return returnPuzzle;
    }

    public static Puzzle movePuzzleDown(final Puzzle puzzle) {
        final Puzzle returnPuzzle = puzzle;
        for (int i = 0; i < puzzle.getMatrix().length; i++) {
            for (int j = 0; j < puzzle.getMatrix().length; j++) {
                if (isMovablePiece(puzzle, i, j)) {
                    if (canMoveDown(i, puzzle.getDimension())) {
                        returnPuzzle.getMatrix()[i][j] = returnPuzzle.getMatrix()[i + 1][j];
                        returnPuzzle.getMatrix()[i + 1][j] = (int) Math.pow(puzzle.getDimension(), 2);
                        return returnPuzzle;
                    }
                }
            }
        }
        return returnPuzzle;
    }

    public static Puzzle movePuzzleLeft(final Puzzle puzzle) {
        final Puzzle returnPuzzle = puzzle;
        for (int i = 0; i < puzzle.getMatrix().length; i++) {
            for (int j = 0; j < puzzle.getMatrix().length; j++) {
                if (isMovablePiece(puzzle, i, j)) {
                    if (canMoveLeft(j)) {
                        returnPuzzle.getMatrix()[i][j] = returnPuzzle.getMatrix()[i][j - 1];
                        returnPuzzle.getMatrix()[i][j - 1] = (int) Math.pow(puzzle.getDimension(), 2);
                        return returnPuzzle;
                    }
                }
            }
        }
        return returnPuzzle;
    }

    public static Puzzle movePuzzleRight(final Puzzle puzzle) {
        final Puzzle returnPuzzle = puzzle;
        for (int i = 0; i < puzzle.getMatrix().length; i++) {
            for (int j = 0; j < puzzle.getMatrix().length; j++) {
                if (isMovablePiece(puzzle, i, j)) {
                    if (canMoveRight(j, puzzle.getDimension())) {
                        returnPuzzle.getMatrix()[i][j] = returnPuzzle.getMatrix()[i][j + 1];
                        returnPuzzle.getMatrix()[i][j + 1] = (int) Math.pow(puzzle.getDimension(), 2);
                        return puzzle;
                    }
                }
            }
        }
        return returnPuzzle;
    }

    private static boolean canMoveLeft(final int j) {
        return j != 0;
    }

    private static boolean canMoveUp(final int i) {
        return i != 0;
    }

    private static boolean canMoveDown(final int i, final int puzzleDimension) {
        return i != puzzleDimension - 1;
    }

    private static boolean canMoveRight(final int j, final int puzzleDimension) {
        return j != puzzleDimension - 1;
    }

    private static boolean isMovablePiece(final Puzzle puzzle, final int i, final int j) {
        return puzzle.getMatrix()[i][j] == Math.pow(puzzle.getDimension(), 2);
    }

    public static class PuzzleCannotBeMovedUpException extends RuntimeException {
    }

    public static class PuzzleCannotBeMovedDownException extends RuntimeException {
    }

    public static class PuzzleCannotBeMovedLeftException extends RuntimeException {
    }

    public static class PuzzleCannotBeMovedRightException extends RuntimeException {
    }
}
