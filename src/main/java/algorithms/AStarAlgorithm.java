package algorithms;

import entity.Node;
import helpers.NodeComparator;
import helpers.SolubilityChecker;
import states.Puzzle;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class AStarAlgorithm implements Algorithm {

    private LinkedList<Node> fringe;
    private HashSet<Node> closed;
    private final Node startNode;
    private final NodeComparator nc = new NodeComparator();
    private static final Node NO_PARENT = null;

    public AStarAlgorithm(final Puzzle puzzle) {
        final Node startNode = new Node(NO_PARENT, puzzle);
        this.startNode = startNode;
    }

    public boolean isInClosedList(Node node) {
        for (Node node1 : closed) {
            if (node1.equals(node)) {
                return true;
            }
        }
        return false;
    }

    public Node solve() {
        fringe = new LinkedList<>();
        closed = new HashSet<>();

        fringe.add(startNode);

        if (!isNodeSolvable(startNode)) {
            return startNode;
        }

        while (!fringe.isEmpty()) {
            System.out.println("FRINGE: " + fringe.size());
            Node auxNode = fringe.remove(0);

            if (auxNode.isGoal()) {
                return auxNode;
            }

            Set<Node> possibleMoves = new HashSet<>();
            possibleMoves.addAll(auxNode.getValidChildNodes());

            closed.add(auxNode);
            System.out.println("CLOSED: " + closed.size());

            for (Node position : possibleMoves) {

                if (!isInClosedList(position)) {
                    position.setParent(auxNode);
                    int index = Collections.binarySearch(fringe, position, nc);
                    //System.out.println("---------------------");
                    if (index < 0) {
                        fringe.add((-index - 1), position);
                    } else {
                        fringe.add(index, position);
                    }

                }
            }
        }
        return null;
    }

    private boolean isNodeSolvable(final Node node) {
        return SolubilityChecker.isSolvable(node.getState());
    }
}
