package entity;

import states.Puzzle;

import java.util.HashSet;
import java.util.Set;

public class Node {

    private final Puzzle state;
    private Node parent;
    private Integer depth; //g()

    public Node(final Node parent, final Puzzle state){
        this.state = state;
        this.parent = parent;
        this.depth = getNodeDepth(parent);
    }

    private int getNodeDepth(final Node parent) {
        if (parentIsRootNode(parent)) {
            return 0;
        } else {
            return parent.depth + 1;
        }
    }

    private boolean parentIsRootNode(final Node parent) {
        return parent == null;
    }

    public int getNodeQuality() {
        return depth + state.getStateHeuristic();
    }

    public Set<Node> getValidChildNodes() {
        Set<Node> possibleChildNodes = new HashSet<>();
        for (final Puzzle puzzle : getValidStates()) {
            possibleChildNodes.add(new Node(this, puzzle));
        }
        return possibleChildNodes;
    }

    private Set<Puzzle> getValidStates() {
        return this.getState().getPossibleMoves();
    }

    public boolean isGoal(){
        return this.state.isGoal();
    }

    public Puzzle getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
