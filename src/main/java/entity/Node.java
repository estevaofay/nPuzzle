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

    private Integer getNodeDepth(final Node parent) {
        if(parentIsRootNode(parent)) {
            return 0;
        } else {
            return parent.depth + 1;
        }
    }

    private boolean parentIsRootNode(final Node parent) {
        return parent == null;
    }

    public int getNodeQuality() {
        return depth + state.h();
    }

    @Override
    public boolean equals(Object o) {
        Node node = (Node) o;
        for (int i = 0; i < state.getMatrix().length; i++) {
            for (int j = 0; j < state.getMatrix().length; j++) {
                if (state.getMatrix()[i][j] != node.getState().getMatrix()[i][j]) {
                    return false;
                }
            }

        }
        return true;
    }

    public Set<Node> expand() {
        Set<Node> ret = new HashSet<>();
        Set<Puzzle> aux = new HashSet<>();
        aux.addAll(this.getState().getPossibleMoves());

        for (Puzzle puzzle : aux) {
            ret.add(new Node(this, puzzle));
        }
        return ret;
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
