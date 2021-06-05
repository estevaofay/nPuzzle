package states;

import entity.Direction;
import helpers.PuzzleMover;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Puzzle extends State {

    int[][] matrix;
    Set<State> validPuzzleMoves;

    public Puzzle() {}

    public Puzzle(int puzzleDimension) {
        this.dimension = puzzleDimension;
        matrix = new int[dimension][dimension];
        createDefaultPuzzle();
    }

    @Override
    public void setDimension(final int dimension) {
        this.dimension = dimension;
        matrix = new int[dimension][dimension];
        createDefaultPuzzle();
    }

    private void createDefaultPuzzle() {
        int filler = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = filler + 1;
                filler++;
            }
        }
    }

    public Puzzle(Puzzle other) {
        matrix = new int[other.dimension][other.dimension];
        this.dimension = other.dimension;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = other.matrix[i][j];
            }
        }
    }

    public Puzzle move(final Direction d) {
        Puzzle movedPuzzle = new Puzzle(this);
        switch (d) {
            case UP:
                PuzzleMover.movePuzzleUp(movedPuzzle);
                return null;
            case DOWN:
                PuzzleMover.movePuzzleDown(movedPuzzle);
                return null;
            case LEFT:
                PuzzleMover.movePuzzleLeft(movedPuzzle);
                return null;
            case RIGHT:
                PuzzleMover.movePuzzleRight(movedPuzzle);
                return null;
        }
        return null;
    }

    @Override
    public boolean isGoal() {
        return this.getStateHeuristic() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == Math.pow(dimension, 2)) {
                    sb.append("*");
                } else {
                    sb.append(matrix[i][j]);
                }
                sb.append("    ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int getStateHeuristic() { // Heuristic means the difference between current and desired state
        int deltaX = 0;
        int deltaY = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int coordX = (matrix[i][j] - 1) / dimension;
                int coordY = (matrix[i][j] - 1) % dimension;

                deltaX += Math.abs(coordX - i);
                deltaY += Math.abs(coordY - j);
            }

        }
        return deltaX + deltaY;
    }

    @Override
    public Set<State> getValidMoves() {
        Set<Puzzle> validPuzzleMoves1 = new HashSet<>();
        validPuzzleMoves1.add(PuzzleMover.movePuzzleUp(this));
        validPuzzleMoves1.add(PuzzleMover.movePuzzleDown(this));
        validPuzzleMoves1.add(PuzzleMover.movePuzzleLeft(this));
        validPuzzleMoves1.add(PuzzleMover.movePuzzleRight(this));

        return null;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return Arrays.equals(matrix, puzzle.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }
}
