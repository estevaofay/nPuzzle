package states;

import entity.Node;
import helpers.PuzzleScrambler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Puzzle {

    int puzzleDimension;

    public enum Direction {//ENUM UP DOWN LEFT RIGHT
        UP, DOWN, LEFT, RIGHT
    }
    int[][] matrix;

    LinkedList<Puzzle> possibleMoves;
    private Node node = new Node(null, this);

    public int randomIndex() {
        return ((int) (Math.random() * puzzleDimension));
    }

    public Node getNode() {
        return this.node;
    }

    public Puzzle(int puzzleDimension) { //criar estado aleatório
        matrix = new int[puzzleDimension][puzzleDimension];
        int filler = 0;

        //FILL MATRIX IN ORDER
        this.puzzleDimension = puzzleDimension;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                matrix[i][j] = filler + 1;
                filler++;
            }
        }
        //SCRAMBLE MATRIX POINTS
//        scramblePuzzle();

        PuzzleScrambler.scramblePuzzle(this);

    }

    //CONSTRUCTOR THAT COPIES A MATRIX SO IT CAN CHANGE LATER
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

    //OBJECTIVE CHECKING
    public boolean isGoal() {
        return this.h() == 0;
    }

    public boolean isSolvable() {
        int n = this.puzzleDimension;
        int[] arr = new int[(int) Math.pow(n, 2)];
        int s = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                arr[s] = matrix[j][k];
                s++;
            }

        }

        int inversion = 0;

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i + 1; j < arr.length ; j++) {

                if (arr[i] > arr[j]) {
                    inversion++;
                }

            }

        }


        if (n % 2 != 0) { // GRID IS ODD
            if (inversion % 2 == 0) { // INVERSION IS EVEN
                return true;
            }

        } else if (n % 2 == 0) { //GRID IS EVEN
            int i = 0;
            int j = 0;
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (this.matrix[i][j] == Math.pow(n, 2)) {
                        if (i % 2 == 0 && inversion % 2 != 0 || i % 2 != 0 && i % 2 != 0 && inversion % 2 == 0) {
                            return true;
                        }
                    }

                }

            }
        }
        return false;
    }

    //CONVENIENT WAY TO PRINT STEPS
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == Math.pow(puzzleDimension, 2)) {
                    sb.append("*");
                } else {
                    sb.append(Integer.toString(matrix[i][j]));
                }

                sb.append("    ");
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    public int h() { //h() representa a diferença do estado atual ao desejado
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
}
