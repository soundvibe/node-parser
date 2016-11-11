package solution;

/**
 * @author OZY on 2016.11.11.
 */
public class Tree {

    public final Node root;

    public Tree(Character head, Character tail) {
        this.root = new Node(head);
        this.root.setLeft(new Node(tail));
    }
}
