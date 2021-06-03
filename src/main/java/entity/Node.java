package entity;

import states.State;

import java.util.HashSet;
import java.util.Set;

public class Node {

    private State state;
    private Node parent;
    private int depth;

    public Node() {}

    public Node(final Node parent) {
        this.parent = parent;
        this.depth = parent.getDepth() + 1;
    }

    public Node(final Node parent, final State state){
        this.state = state;
        this.parent = parent;
        this.depth = parent.getDepth() + 1;
    }

    public int getNodeQuality() {
        return depth + state.getStateHeuristic();
    }

    public Set<Node> getValidChildNodes() {
        final Set<Node> possibleChildNodes = new HashSet<>();
        for (final State puzzle : state.getValidMoves()) {
            possibleChildNodes.add(new Node(this, puzzle));
        }
        return possibleChildNodes;
    }

    public State getState() {
        if (state == null)
            throw new NodeHasNoState();
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(final Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        if (state == null)
            throw new NodeHasNoState();
        return state.toString();
    }

    public int getDepth() {
        if (parent != null)
            return parent.getDepth() + 1;
        return 0;
    }

    public class NodeHasNoState extends RuntimeException {
    }

}
