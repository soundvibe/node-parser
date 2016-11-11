package solution;

/**
 * @author OZY on 2016.11.11.
 */
public final class TreePrinter {

    private TreePrinter() {
        //no instance
    }

    public static String print(Tree tree) {
        final StringBuilder sb = new StringBuilder();
        printNode(tree.root, sb);
        return sb.toString();
    }

    private static void printNode(Node node, StringBuilder sb) {
        sb.append("(").append(node.value);

        if (node.hasLeft()) {
            printNode(node.left, sb);
        }

        if (node.hasRight()) {
            printNode(node.right, sb);
        }
        sb.append(")");
    }



}
