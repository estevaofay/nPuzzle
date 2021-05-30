package helpers;

import states.Puzzle;

public class SolubilityChecker {

    public static boolean isSolvable(final Puzzle puzzle) {
        int puzzleDimension = puzzle.getPuzzleDimension();
        int[] vector = transformMatrixIntoVector(puzzle, puzzleDimension);
        int numberOfInversions = countNumberOfInversions(vector);
        return checkIfIsSolvableByPuzzleDimensions(puzzle, numberOfInversions);
    }

    private static boolean checkIfIsSolvableByPuzzleDimensions(final Puzzle puzzle, final int numberOfInversions) {
        final int puzzleDimension = puzzle.getPuzzleDimension();
        if (puzzleDimensionsAreOdd(puzzleDimension)) {
            return checkIfNumberOfInversionsIsEven(numberOfInversions);
        }
        if (puzzleDimensionsAreEven(puzzleDimension)) {
            return searchThroughMatrixAndCheckInversionRule(puzzle, numberOfInversions);
        }
        return false;
    }

    private static boolean searchThroughMatrixAndCheckInversionRule(final Puzzle puzzle, final int numberOfInversions) {
        final int puzzleDimension = puzzle.getPuzzleDimension();
        for (int i = 0; i < puzzleDimension; i++) {
            for (int j = 0; j < puzzleDimension; j++) {
                if (cellIsEqualToDimensionSquared(puzzle.getMatrix()[i][j] , puzzleDimension)) {
                    if (isEven(i) && isOdd(numberOfInversions) || isOdd(i) && isEven(numberOfInversions)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean puzzleDimensionsAreEven(int puzzleDimension) {
        return isEven(puzzleDimension);
    }

    private static boolean checkIfNumberOfInversionsIsEven(int numberOfInversions) {
        return numberOfInversionsIsEven(numberOfInversions);
    }

    private static boolean puzzleDimensionsAreOdd(final int puzzleDimension) {
        return isOdd(puzzleDimension);
    }

    private static boolean numberOfInversionsIsEven(int numberOfInversions) {
        return isEven(numberOfInversions);
    }

    private static boolean cellIsEqualToDimensionSquared(int cellValue, int puzzleDimension) {
        return cellValue == Math.pow(puzzleDimension, 2);
    }

    private static int[] transformMatrixIntoVector(Puzzle puzzle, int puzzleDimension) {
        int[] vector = new int[(int) Math.pow(puzzleDimension, 2)];
        int index = 0;
        for (int j = 0; j < puzzleDimension; j++) {
            for (int k = 0; k < puzzleDimension; k++) {
                vector[index] = puzzle.getMatrix()[j][k];
                index++;
            }
        }
        return vector;
    }

    private static int countNumberOfInversions(final int[] array) {
        int numberOfInversions = 0;
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i + 1; j < array.length ; j++) {
                if (array[i] > array[j]) {
                    numberOfInversions++;
                }
            }
        }
        return numberOfInversions;
    }

    private static boolean isEven(int puzzleDimension) {
        return puzzleDimension % 2 == 0;
    }

    private static boolean isOdd(int puzzleDimension) {
        return puzzleDimension % 2 != 0;
    }
}
