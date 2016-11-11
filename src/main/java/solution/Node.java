package solution;

/**
 * @author OZY on 2016.11.11.
 */
public class Node {

    public final Character value;
    public Node left;
    public Node right;

    public Node(Character value) {
        this.value = value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node node) {
        this.right = node;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
