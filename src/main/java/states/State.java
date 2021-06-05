package states;

import java.util.Set;

public abstract class State {

    int dimension;

    public abstract int getStateHeuristic();
    public abstract boolean isGoal();

    public abstract Set<State> getValidMoves();

    public void setDimension(final int dimension) {
        this.dimension = dimension;
    }

    public class StateHasNoDimensionsSet extends RuntimeException{
    }

    public int getDimension() {
        if (dimension == 0)
            throw new StateHasNoDimensionsSet();
        return dimension;
    }


}
