import algorithms.AStarAlgorithm;
import entity.Node;
import states.Puzzle;

public class Search {

    public static void main(String[] args) {

        Puzzle startPuzzle = new Puzzle(3);
        AStarAlgorithm s = new AStarAlgorithm(startPuzzle.getNode());
        Node solved = s.search();

    }
}
