package states;

import java.util.HashSet;
import java.util.Set;

public class Puzzle {

    int puzzleDimension;

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    int[][] matrix;

    public Puzzle(int puzzleDimension) {
        matrix = new int[puzzleDimension][puzzleDimension];
        this.puzzleDimension = puzzleDimension;
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
        matrix = new int[other.puzzleDimension][other.puzzleDimension];
        this.puzzleDimension = other.puzzleDimension;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = other.matrix[i][j];
            }
        }
    }

    public Puzzle move(Direction d) {
        Puzzle movedPuzzle = new Puzzle(this);

        switch (d) {
            case UP:
                for (int i = 0; i < movedPuzzle.puzzleDimension; i++) {
                    for (int j = 0; j < movedPuzzle.puzzleDimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(puzzleDimension, 2)) {
                            if (i != 0) {
                                movedPuzzle.matrix[i][j] = matrix[i - 1][j];
                                movedPuzzle.matrix[i - 1][j] = (int) Math.pow(puzzleDimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
                return null;
            case DOWN:
                for (int i = 0; i < puzzleDimension; i++) {
                    for (int j = 0; j < puzzleDimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(puzzleDimension, 2)) {
                            if (i != puzzleDimension - 1) {
                                movedPuzzle.matrix[i][j] = matrix[i + 1][j];
                                movedPuzzle.matrix[i + 1][j] = (int) Math.pow(puzzleDimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
                return null;
            case LEFT:
                for (int i = 0; i < puzzleDimension; i++) {
                    for (int j = 0; j < puzzleDimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(puzzleDimension, 2)) {
                            if (j != 0) {
                                movedPuzzle.matrix[i][j] = matrix[i][j - 1];
                                movedPuzzle.matrix[i][j - 1] = (int) Math.pow(puzzleDimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
                return null;
            case RIGHT:
                for (int i = 0; i < puzzleDimension; i++) {
                    for (int j = 0; j < puzzleDimension; j++) {
                        if (movedPuzzle.matrix[i][j] == Math.pow(puzzleDimension, 2)) {
                            if (j != puzzleDimension - 1) {
                                movedPuzzle.matrix[i][j] = matrix[i][j + 1];
                                movedPuzzle.matrix[i][j + 1] = (int) Math.pow(puzzleDimension, 2);
                                return movedPuzzle;
                            }
                        }
                    }
                }
                return null;
        }
        return null;
    }

    public boolean isGoal() {
        return this.getStateHeuristic() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == Math.pow(puzzleDimension, 2)) {
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

    public int getStateHeuristic() { // Heuristic means the difference between current and desired state
        int deltaX = 0;
        int deltaY = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int coordX = (matrix[i][j] - 1) / puzzleDimension;
                int coordY = (matrix[i][j] - 1) % puzzleDimension;

                deltaX += Math.abs(coordX - i);
                deltaY += Math.abs(coordY - j);
            }

        }
        return deltaX + deltaY;
    }

    public Set<Puzzle> getPossibleMoves() {
        Set<Puzzle> ret = new HashSet<>();

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

    public int getPuzzleDimension() {
        return puzzleDimension;
    }
}
