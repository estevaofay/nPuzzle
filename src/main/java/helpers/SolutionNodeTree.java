package helpers;

import entity.Node;

import java.util.LinkedList;

public class SolutionNodeTree {

    public static void printSolutionTree(final Node node) {
        final LinkedList<Node> solutionTree = getSolutionTree(node);
        final String solutionTreeString = createSolutionTreeString(solutionTree);
        System.out.println(solutionTreeString);
    }

    private static String createSolutionTreeString(final LinkedList<Node> solutionTree) {
        final StringBuilder sb = new StringBuilder();
        for (final Node solutionNode : solutionTree) {
            sb.append(solutionNode.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    private static LinkedList<Node> getSolutionTree(Node node) {
        final LinkedList<Node> solutionTree = new LinkedList<>();
        while (isNotRootNode(node)){
            solutionTree.addFirst(node);
            node = node.getParent();
        }
        return solutionTree;
    }

    private static boolean isNotRootNode(final Node aux) {
        return aux.getParent() != null;
    }
}
