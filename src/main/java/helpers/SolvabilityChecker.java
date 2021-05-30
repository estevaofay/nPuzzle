package helpers;

import states.Puzzle;

public class SolvabilityChecker {

    public static boolean isSolvable(final Puzzle puzzle) {
        int puzzleDimension = puzzle.getPuzzleDimension();
        int[] arr = new int[(int) Math.pow(puzzleDimension, 2)];
        int s = 0;
        for (int j = 0; j < puzzleDimension; j++) {
            for (int k = 0; k < puzzleDimension; k++) {
                arr[s] = puzzle.getMatrix()[j][k];
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


        if (gridIsOdd(puzzleDimension)) {
            if (gridIsEven(inversion)) { // INVERSION IS EVEN
                return true;
            }

        } else if (gridIsEven(puzzleDimension)) {
            int i = 0;
            int j = 0;
            for (i = 0; i < puzzleDimension; i++) {
                for (j = 0; j < puzzleDimension; j++) {
                    if (puzzle.getMatrix()[i][j] == Math.pow(puzzleDimension, 2)) {
                        if (gridIsEven(i) && gridIsOdd(inversion) || gridIsOdd(i) && gridIsOdd(i) && gridIsEven(inversion)) {
                            return true;
                        }
                    }

                }

            }
        }
        return false;
    }

    private static boolean gridIsEven(int puzzleDimension) {
        return puzzleDimension % 2 == 0;
    }

    private static boolean gridIsOdd(int puzzleDimension) {
        return puzzleDimension % 2 != 0;
    }
}
