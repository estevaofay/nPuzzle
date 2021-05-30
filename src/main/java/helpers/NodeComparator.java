package helpers;

import entity.Node;

import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(final Node o1, final Node o2) {
        if(o1.f() < o2.f()) {
            return -1;
        } else if (o1.f() == o2.f()) {
            return 0;
        } else {
            return 1;
        }
    }
}
