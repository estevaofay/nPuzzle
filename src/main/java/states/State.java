package states;

import java.util.Set;

public abstract class State {

    int dimension;

    public abstract int getStateHeuristic();
    public abstract boolean isGoal();

    public abstract Set<State> getPossibleMoves();

    public int getDimension() {
        return dimension;
    }
}
