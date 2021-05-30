import algorithms.AStarAlgorithm;
import entity.Node;
import helpers.PuzzleScrambler;
import helpers.SolutionNodeTree;
import states.Puzzle;

public class Search {

    public static void main(String[] args) {

        final Puzzle puzzle = new Puzzle(3);
        PuzzleScrambler.scramblePuzzle(puzzle);
        final AStarAlgorithm aStarAlgorithm = new AStarAlgorithm(puzzle);
        final Node solution = aStarAlgorithm.solve();
        SolutionNodeTree.printSolutionTree(solution);
    }
}
