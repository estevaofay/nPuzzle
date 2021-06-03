package entity;

import states.State;

import java.util.HashSet;
import java.util.Set;

public class Node {

    private State state;
    private Node parent;
    private int depth;

    public Node(final Node parent, final State state){
        this.state = state;
        this.parent = parent;
        this.depth = getDepth(parent);
    }

    public Node(final Node parent) {
        this.parent = parent;
    }

    public Node() {

    }

    private int getDepth(final Node parent) {
        if (isRootNode(parent)) {
            return 0;
        } else {
            return parent.depth + 1;
        }
    }

    private boolean isRootNode(final Node parent) {
        return parent == null;
    }

    public int getNodeQuality() {
        return depth + state.getStateHeuristic();
    }

    public Set<Node> getValidChildNodes() {
        final Set<Node> possibleChildNodes = new HashSet<>();
        for (final State puzzle : getValidStates()) {
            possibleChildNodes.add(new Node(this, puzzle));
        }
        return possibleChildNodes;
    }

    private Set<State> getValidStates() {
        return this.getState().getPossibleMoves();
    }

    public boolean isGoal(){
        return this.state.isGoal();
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
