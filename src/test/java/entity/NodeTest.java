package entity;

import helpers.NodeComparator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void singleNodeShouldHaveNoParent() {
        final Node node = new Node();
        assertNull(node.getParent());
    }

    @Test
    void nodeWithNoStateShouldThrowException() {
        assertThrows(Node.NodeHasNoState.class, () -> {
            final Node node = new Node();
            node.toString();
        });
    }

    @Test
    void nodeShouldBeAbleToReferenceParent() {
        final Node parent = new Node();
        final Node child = new Node();
        child.setParent(parent);
        assertEquals(parent, child.getParent());
    }

    @Test
    void constructorPassingParent() {
        final Node parent = new Node();
        final Node child = new Node(parent);
        assertEquals(parent, child.getParent());
    }

    @Test
    void rootNodeHasDepthZero() {
        final Node node = new Node();
        assertEquals(0, node.getDepth());
    }

    @Test
    void childNodeShouldHaveParentDepthPlusOne() {
        final Node parent = new Node();
        final Node child = new Node(parent);
        assertNotNull(child.getParent());
        assertEquals(1, child.getDepth());
    }

    @Test
    void threeLevelTreeShouldReturnCorrectDepth(){
        final Node grandparent = new Node();
        final Node parent = new Node (grandparent);
        final Node child = new Node (parent);
        assertEquals(2, child.getDepth());
    }

    @Test
    void nodeShouldHaveState() {
        assertThrows(Node.NodeHasNoState.class, () -> {
            final Node node = new Node();
            node.getState();
        });
    }

    @Test
    void rootNodeDoesNotHaveParent() {
        final Node node = new Node();
        assertNull(node.getParent());
    }

}