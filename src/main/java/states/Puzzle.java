package states;

import java.util.HashSet;
import java.util.Set;

public class Puzzle extends State {


    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    int[][] matrix;

    public Puzzle(int puzzleDimension) {
        matrix = new int[dimension][dimension];
        this.dimension = puzzleDimension;
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
                for (int i = 0; i < movedPuzzle.dimension; i++) {
                    for (int j = 0; j < movedPuzzle.dimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(dimension, 2)) {
                            if (i != 0) {
                                movedPuzzle.matrix[i][j] = matrix[i - 1][j];
                                movedPuzzle.matrix[i - 1][j] = (int) Math.pow(dimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
                return null;
            case DOWN:
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(dimension, 2)) {
                            if (i != dimension - 1) {
                                movedPuzzle.matrix[i][j] = matrix[i + 1][j];
                                movedPuzzle.matrix[i + 1][j] = (int) Math.pow(dimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
                return null;
            case LEFT:
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(dimension, 2)) {
                            if (j != 0) {
                                movedPuzzle.matrix[i][j] = matrix[i][j - 1];
                                movedPuzzle.matrix[i][j - 1] = (int) Math.pow(dimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
                return null;
            case RIGHT:
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(dimension, 2)) {
                            if (j != dimension - 1) {
                                movedPuzzle.matrix[i][j] = matrix[i][j + 1];
                                movedPuzzle.matrix[i][j + 1] = (int) Math.pow(dimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
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
    public Set<State> getPossibleMoves() {
        Set<State> ret = new HashSet<>();

        Puzzle upMove = this.move(Direction.UP);
        Puzzle downMove = this.move(Direction.DOWN);
        Puzzle leftMove = this.move(Direction.LEFT);
        Puzzle rightMove = this.move(Direction.RIGHT);

        if (upMove != null) {
            ret.add(upMove);
        }
        if (downMove != null) {
            ret.add(downMove);
        }
        if (leftMove != null) {
            ret.add(leftMove);
        }
        if (rightMove != null) {
            ret.add(rightMove);
        }

        return ret;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getDimension() {
        return dimension;
    }
}
